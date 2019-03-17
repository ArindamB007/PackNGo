package com.png.services;

import com.png.data.dto.availableroomtype.AvailableRoomTypeDto;
import com.png.data.dto.availableroomtype.MealPlanDto;
import com.png.data.dto.bookingcart.BookingCartDto;
import com.png.data.dto.invoice.*;
import com.png.data.dto.item.ItemDto;
import com.png.data.entity.*;
import com.png.data.mapper.InvoiceMapper;
import com.png.data.repository.*;
import com.png.exception.ApiBusinessException;
import com.png.exception.RoomJustSoldOutException;
import com.png.payments.RazorPay;
import com.png.util.DateFormatter;
import com.png.util.StringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceProcessorService {

    private Invoice invoice;

    @Autowired
    private ItemTaxRepository itemTaxRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Autowired
    private RazorPay razorPay;

    private List<ItemTax> applicableTaxes;

    public InvoiceDto createInvoice(BookingCartDto bookingCartDto){
        InvoiceDto invoiceDto = null;
        try {
            invoice = new Invoice();
            String checkIn = bookingCartDto.getCheckInOutDetails().getCheckInTimestamp();
            String checkOut = bookingCartDto.getCheckInOutDetails().getCheckOutTimestamp();
            applicableTaxes = itemTaxRepository.findAllByItemTaxPercentEquals("0");
            invoice.setCheckInTimestamp(DateFormatter.getTimestampFromString(checkIn));
            invoice.setCheckOutTimestamp(DateFormatter.getTimestampFromString(checkOut));
            invoice.setInvoiceStatusCode(Invoice.InvoiceStatusCodes.TOTALED.name());
            invoice.setProperty(propertyRepository.findByIdProperty(bookingCartDto.getSelectedProperty().getIdProperty()));
            invoice.setInvoiceLines(getInvoiceLinesForMealPlans(bookingCartDto.getSelectedRoomTypes()));
            invoice.calculateInvoiceLevelTaxes();
            invoice.calculateInvoiceTotalTax();
            invoice.calculateInvoiceTotalWithTax();
            invoice.calculateInvoiceTotal();
            invoice.setAmountPaid(BigDecimal.ZERO);
            invoice.setAmountPending(invoice.getInvoiceTotalWithTax());

            invoiceDto = InvoiceMapper.INSTANCE.InvoiceToInvoiceDto(invoice);

            invoiceDto.setInvoiceNo(createInvoiceNumber());
            invoiceDto.setNights(DateFormatter.getNights(DateFormatter.getTimestampFromString(checkOut),
                    DateFormatter.getTimestampFromString(checkIn)));
            invoiceDto.setUserContext(bookingCartDto.getUserContext());
            invoiceDto.setInvoiceOccupancyInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return invoiceDto;
    }

    @Transactional
    public synchronized InvoiceDto processInvoice(InvoiceDto invoice){
        try {
            List<Room> totalRoomsToBeBooked = new ArrayList<>();
            for (InvoiceLineDto invoiceLine : invoice.getInvoiceLines()) {
                if (invoiceLine.getInvoiceLineTypeCode().equals(InvoiceLine.InvoiceLineTypeCodes.MEALPLAN.name())) {
                    Integer roomQtyRequired = ((InvoiceMealPlanLineDto) invoiceLine).getQuantity();
                    List<Room> roomsToBeBooked =
                            roomTypeRepository.getRoomsToBeBooked(
                                    invoice.getProperty().getIdProperty(),
                                    ((InvoiceMealPlanLineDto) invoiceLine).getRoomTypeName(),
                                    roomQtyRequired,
                                    DateFormatter.getTimestampFromString(invoice.getCheckInTimestamp()),
                                    DateFormatter.getTimestampFromString(invoice.getCheckOutTimestamp()));
                    //check and throw error if the count is not as expected and do not take payment
                    System.out.println("Rooms to be booked Count:" + roomsToBeBooked.size());
                    if (roomsToBeBooked.size() < roomQtyRequired)
                        throw new RoomJustSoldOutException("1005",
                                "The rooms are already sold, please change search parameters and try again");
                    totalRoomsToBeBooked.addAll(roomsToBeBooked);
                }
            }
            //capture payment
            RazorpayPaymentResponse razorpayPaymentResponse =
                    razorPay.captureRazorPayPayment(invoice.getPayment().getProviderPaymentId(),
                            invoice.getPayment().getAmountPaid());
            // incase of any error in razorpay response we need stop the booking
            //razorpayResponseRepository.save(razorpayPaymentResponse);


            // create new invoice
            Invoice newInvoice = InvoiceMapper.INSTANCE.InvoiceDtoToInvoice(invoice);
            //add property details
            newInvoice.setProperty(propertyRepository.findByIdProperty(invoice.getProperty().getIdProperty()));
            newInvoice.setUser(customUserDetailsService.getUserByUserContext(invoice.getUserContext()));
            //add payment line
            newInvoice.addInvoicePaymentLine(razorpayPaymentResponse);

            //create new bookings for each room
            List<Booking> newBookings = new ArrayList<>();
            for (Room roomToBeBooked : totalRoomsToBeBooked) {
                Booking booking = new Booking();
                booking.setRoom(roomToBeBooked);
                booking.setCheckInTimestamp(DateFormatter.getTimestampFromString(invoice.getCheckInTimestamp()));
                booking.setCheckOutTimestamp(DateFormatter.getTimestampFromString(invoice.getCheckOutTimestamp()));
                booking.setUpdatedTimestamp(DateFormatter.getCurrentTime());
                booking.setBookingTypeCode(Booking.BookingTypeCodes.ONLINE.name());
                booking.setBookedByUser(customUserDetailsService.getUserByUserContext(invoice.getUserContext()));
                newBookings.add(booking);
            }
            for (Booking newBooking : newBookings) {
                newInvoice.addBooking(newBooking);
            }
            newInvoice.setCreatedTimestamp(DateFormatter.getCurrentTime());

            //save the invoice
            newInvoice = invoiceRepository.save(newInvoice);
            return InvoiceMapper.INSTANCE.InvoiceToInvoiceDto(newInvoice);
        } catch(Exception e) {
            e.printStackTrace();
            //todo add logging
            throw new ApiBusinessException("1000", "An unexpected situation is creating hindrance to further progress. " +
                    "Support team needs to intervene!");
        }
        //roomTypeRepository.getRoomsToBeBooked()
        //Book the room
        //if error do not capture payment, customer will be auto refunded
        // send email
        // return error response
        //else
        //get payment
        //create an invoice from dto
        //add payment lines
        // update invoice status
        // save invoice
        // convert to DTO
        // send response

        //process Payment
        //send email
    }


    private List<InvoiceLine> getInvoiceLinesForMealPlans(List<AvailableRoomTypeDto> selectedRoomTypes) {
        int groupSequenceNo = 1;
        List<InvoiceLine> invoiceLines = new ArrayList<>();
        for (AvailableRoomTypeDto selectedRoomType:selectedRoomTypes)
        {
            List<MealPlanDto> mealPlans = selectedRoomType.getMealPlans();
            for(MealPlanDto mealPlan:mealPlans){
                Integer mealPLanQuantity = mealPlan.getMealPlanItem().getQuantity();
                if (mealPLanQuantity>0){
                    InvoiceLineItem invoiceLine = getInvoiceLineForMealPlan(mealPlan);
                    ((InvoiceMealPlanLine) invoiceLine).setRoomTypeName(selectedRoomType.getTypeName());
                    ((InvoiceMealPlanLine) invoiceLine)
                            .setMaxAdults(selectedRoomType.getMaxAdultOccupancy() * mealPLanQuantity);
                    ((InvoiceMealPlanLine) invoiceLine)
                            .setMaxChilds(selectedRoomType.getMaxChildOccupancy() * mealPLanQuantity);
                    invoiceLine.setSequenceNo(getInvoiceLineSequence(invoiceLines));
                    invoiceLine.setGroupSequenceNo(groupSequenceNo);
                    List<InvoiceLineTax> invoiceLineTaxesForMealPlan = invoiceLine.getInvoiceLineTaxes();
                    invoiceLines.add(invoiceLine);
                    invoiceLine = getInvoiceLineForItem(mealPlan.getAdultExtraBedItem());
                    if (invoiceLine != null) {
                        invoiceLine.setSequenceNo(getInvoiceLineSequence(invoiceLines));
                        invoiceLine.setGroupSequenceNo(groupSequenceNo);
                        invoiceLine.setInvoiceLineTaxes(new ArrayList<>(invoiceLineTaxesForMealPlan));
                        invoiceLine.calculateAmountWithTax();
                        invoiceLines.add(invoiceLine);
                    }
                    invoiceLine = getInvoiceLineForItem(mealPlan.getChildExtraBedItem());
                    if (invoiceLine != null) {
                        invoiceLine.setSequenceNo(getInvoiceLineSequence(invoiceLines));
                        invoiceLine.setGroupSequenceNo(groupSequenceNo);
                        invoiceLine.setInvoiceLineTaxes(new ArrayList<>(invoiceLineTaxesForMealPlan));
                        invoiceLine.calculateAmountWithTax();
                        invoiceLines.add(invoiceLine);
                    }
                    groupSequenceNo +=1; // increment sequence number for grouping each room type
                }
            }
        }

        return invoiceLines;
    }


    private InvoiceLineItem getInvoiceLineForMealPlan(MealPlanDto mealPlan) {
        InvoiceMealPlanLine invoiceLine = new InvoiceMealPlanLine();
        invoiceLine.setInvoiceLineTypeCode(InvoiceLine.InvoiceLineTypeCodes.MEALPLAN.name());
        invoiceLine.setInvoiceLineStatusCode(InvoiceLine.InvoiceLineStatusCodes.SALE.name());
        invoiceLine.setDescription(mealPlan.getMealPlanItem().getDescription());
        invoiceLine.setItemType(mealPlan.getMealPlanItem().getItemType().getItemTypeCode());
        invoiceLine.setPrice(mealPlan.getMealPlanItem().getItemPrice().getBasePrice());
        invoiceLine.setQuantity(mealPlan.getMealPlanItem().getQuantity());
        invoiceLine.setNoOfNights(mealPlan.getMealPlanItem().getNoOfNights());
        invoiceLine.setAmount(invoiceLine.getPrice().multiply(
                new BigDecimal(invoiceLine.getQuantity())));
        if (invoiceLine.getNoOfNights()!=null && invoiceLine.getNoOfNights()>1)
            invoiceLine.setAmount(invoiceLine.getAmount().multiply(
                    new BigDecimal((invoiceLine.getNoOfNights()))));
        invoiceLine.setTaxableAmount(invoiceLine.getAmount().subtract(invoiceLine.getDiscountAmount()));
        invoiceLine.setInvoiceLineTaxes(getInvoiceLineTaxesForMealPlan(mealPlan));
        invoiceLine.calculateAmountWithTax();
        return invoiceLine;
    }

    private InvoiceLineItem getInvoiceLineForItem(ItemDto item) {
        if (item.getQuantity() == null || item.getQuantity() == 0)
            return null;
        InvoiceLineItem invoiceLine = new InvoiceLineItem();
        if (item.getItemType().getItemTypeCode().equals(ItemType.ItemTypeCodes.EXTRABEDADULT.name()) ||
                (item.getItemType().getItemTypeCode().equals(ItemType.ItemTypeCodes.EXTRABEDCHILD.name())))
            invoiceLine.setInvoiceLineTypeCode(InvoiceLine.InvoiceLineTypeCodes.EXTRA_PERSON.name());
        invoiceLine.setInvoiceLineStatusCode(InvoiceLine.InvoiceLineStatusCodes.SALE.name());
        invoiceLine.setDescription(item.getDescription());
        invoiceLine.setPrice(item.getItemPrice().getBasePrice());
        invoiceLine.setQuantity(item.getQuantity());
        invoiceLine.setNoOfNights(item.getNoOfNights());
        invoiceLine.setItemType(item.getItemType().getItemTypeCode());
        invoiceLine.setAmount(invoiceLine.getPrice().multiply(
                new BigDecimal(invoiceLine.getQuantity())));
        if (invoiceLine.getNoOfNights()!=null && invoiceLine.getNoOfNights()>1)
            invoiceLine.setAmount(invoiceLine.getAmount().multiply(
                    new BigDecimal((invoiceLine.getNoOfNights()))));
        invoiceLine.setTaxableAmount(invoiceLine.getAmount().subtract(invoiceLine.getDiscountAmount()));
        return invoiceLine;
    }

    private List<InvoiceLineTax> getInvoiceLineTaxesForMealPlan(MealPlanDto mealPlan) {
        List<InvoiceLineTax> invoiceLineTaxes = new ArrayList<>();
        applicableTaxes.forEach(applicableTax->{
            InvoiceLineTax invoiceLineTax = new InvoiceLineTax();
            //calculating total price of an item for the room, extra bed etc
            BigDecimal totalPrice = mealPlan.getMealPlanItem().getItemPrice().getBasePrice();
            if (mealPlan.getAdultExtraBedItem().getQuantity()>0)
                totalPrice = totalPrice.add(mealPlan.getAdultExtraBedItem().getItemPrice().getBasePrice()) ;
            if (mealPlan.getChildExtraBedItem().getQuantity()>0)
                totalPrice = totalPrice.add(mealPlan.getAdultExtraBedItem().getItemPrice().getBasePrice());
            invoiceLineTax.setItemTaxCode(applicableTax.getItemTaxCode());
            invoiceLineTax.setItemTaxDescription(applicableTax.getItemTaxDescription());
            if (mealPlan.getMealPlanItem().getItemType().getItemTypeCode().equals(ItemType.ItemTypeCodes.MEALPLANITEM.name()) ||
                    mealPlan.getMealPlanItem().getItemType().getItemTypeCode().equals(ItemType.ItemTypeCodes.EXTRABEDADULT.name()) ||
                    mealPlan.getMealPlanItem().getItemType().getItemTypeCode().equals(ItemType.ItemTypeCodes.EXTRABEDCHILD.name())) {
                // tax hardcode for SGST and CGST
                if (totalPrice.compareTo(new BigDecimal(0)) >= 0 && totalPrice.compareTo(new BigDecimal(1500)) < 0 )
                    invoiceLineTax.setItemTaxPercent("0");
                else if (totalPrice.compareTo(new BigDecimal(1500)) >= 0 && totalPrice.compareTo(new BigDecimal(2500)) < 0 )
                    invoiceLineTax.setItemTaxPercent("6");
                else if (totalPrice.compareTo(new BigDecimal(2500)) >= 0 && totalPrice.compareTo(new BigDecimal(7500)) < 0 )
                    invoiceLineTax.setItemTaxPercent("9");
            }
            else{
                invoiceLineTax.setItemTaxPercent("2.5");
            }
            /*invoiceLineTax.setItemTaxAmount((mealPlan.getMealPlanItem().getItemPrice().getBasePrice().
                    multiply(new BigDecimal(invoiceLineTax.getItemTaxPercent())).
                    multiply(new BigDecimal(mealPlan.getMealPlanItem().getQuantity()))));*/
            invoiceLineTaxes.add(invoiceLineTax);
        });
        return invoiceLineTaxes;
    }

    private Integer getInvoiceLineSequence(List<InvoiceLine> invoiceLines) {
        return invoiceLines.size() +1;
    }

    private Integer getInvoicePaymentLineSequence(List<InvoicePaymentLine> invoicePaymentLines){
        return invoicePaymentLines.size() +1;
    }

    public static String createInvoiceNumber() {
        int invNoLength = 8;
        return StringGenerator.randomAlphaNumeric(invNoLength);
    }
}
