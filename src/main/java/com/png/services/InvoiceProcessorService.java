package com.png.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.png.data.dto.availableroomtype.AvailableRoomTypeDto;
import com.png.data.dto.availableroomtype.MealPlanDto;
import com.png.data.dto.bookingcart.BookingCartDto;
import com.png.data.dto.invoice.*;
import com.png.data.dto.item.ItemDto;
import com.png.data.entity.*;
import com.png.data.mapper.InvoiceMapper;
import com.png.data.mapper.MealPlanMapper;
import com.png.data.repository.*;
import com.png.exception.RoomJustSoldOutException;
import com.png.util.DateFormatter;
import com.png.util.StringGenerator;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class InvoiceProcessorService {

    private InvoiceDto invoiceDto;

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

    private List<ItemTax> applicableTaxes;

    public InvoiceDto createInvoice(BookingCartDto bookingCartDto){
        invoiceDto = new InvoiceDto();
        applicableTaxes = itemTaxRepository.findAll();
        invoiceDto.setInvoiceNo(createInvoiceNumber());
        invoiceDto.setInvoiceCheckInOutDetails(bookingCartDto.getCheckInOutDetails());
        invoiceDto.setInvoiceStatusCode(Invoice.InvoiceStatusCodes.TOTALED.name());
        invoiceDto.setUserContext(bookingCartDto.getUserContext());
        invoiceDto.setProperty(bookingCartDto.getSelectedProperty());
        invoiceDto.setInvoiceLines(getInvoiceLinesForMealPlans(bookingCartDto.getSelectedRoomTypes()));
        invoiceDto.setOccupancyInfo();
        invoiceDto.setAppliedTaxes(calculateInvoiceLevelTaxes());
        invoiceDto.setInvoiceTotalTax(calculateInvoiceTotalTax(invoiceDto.getAppliedTaxes()));;
        invoiceDto.setInvoiceTotalWithTax(calculateInvoiceTotalWithTax());
        invoiceDto.setInvoiceTotal(calculateInvoiceTotal());
        invoiceDto.setAmountPaid(BigDecimal.ZERO);
        invoiceDto.setAmountToBePaid(invoiceDto.getInvoiceTotalWithTax());
        invoiceDto.setAmountPending(invoiceDto.getAmountToBePaid());
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
                                    DateFormatter.getTimestampFromString(invoice.getInvoiceCheckInOutDetails()
                                            .getCheckInTimestamp()),
                                    DateFormatter.getTimestampFromString(invoice.getInvoiceCheckInOutDetails()
                                            .getCheckOutTimestamp()));
                    //check and throw error if the count is not as expected and do not take payment
                    System.out.println("Rooms to be booked Count:" + roomsToBeBooked.size());
                    if (roomsToBeBooked.size() < roomQtyRequired)
                        throw new RoomJustSoldOutException("1005",
                                "The rooms are already sold, please change search parameters and try again");
                    totalRoomsToBeBooked.addAll(roomsToBeBooked);
                }
            }
                    //capture payment
                    RazorpayResponse razorpayResponse =
                            captureRazorPayment(invoice.getPayment().getProviderPaymentId(),
                                    invoice.getPayment().getAmountPaid());
                    // incase of any error in razorpay response we need stop the booking
                    //razorpayResponseRepository.save(razorpayResponse);


                    // create new invoice
                    Invoice newInvoice = InvoiceMapper.INSTANCE.InvoiceDtoToInvoice(invoice);
                    //add property details
                    newInvoice.setProperty(propertyRepository.findByIdProperty(invoice.getProperty().getIdProperty()));
                    newInvoice.setUser(customUserDetailsService.getUserByUserContext(invoice.getUserContext()));
                    //add payment line
                    newInvoice.addInvoicePaymentLine(getInvoicePaymentLine(razorpayResponse));

                    //create new booking
                    Booking booking = new Booking();
                    booking.setCheckInTimestamp(DateFormatter.getTimestampFromString(
                            invoice.getInvoiceCheckInOutDetails().getCheckInTimestamp()));
                    booking.setCheckOutTimestamp(DateFormatter.getTimestampFromString(
                            invoice.getInvoiceCheckInOutDetails().getCheckOutTimestamp()));
                    booking.setRooms(totalRoomsToBeBooked);
                    booking.setUpdatedTimestamp(DateFormatter.getCurrentTime());
                    newInvoice.setBooking(booking);
                    newInvoice.setCreatedTimestamp(DateFormatter.getCurrentTime());

                    //save the invoice
                    newInvoice = invoiceRepository.save(newInvoice);
                    return InvoiceMapper.INSTANCE.InvoiceToInvoiceDto(newInvoice);
        } catch(Exception e) {
            e.printStackTrace();
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
        return null;
    }

    private InvoicePaymentLine getInvoicePaymentLine(PaymentResponse paymentResponse){
        //create new payment line
        InvoicePaymentLine invoicePaymentLine = new InvoicePaymentLine();
        invoicePaymentLine.setPaymentResponse(paymentResponse);
        invoicePaymentLine.setTransactionType(InvoicePaymentLine.TransactionTypes.PAYMENT.name());
        return invoicePaymentLine;
    }

    private RazorpayResponse captureRazorPayment(String razor_payment_id, String amountPaid){
        RazorpayResponse razorpayResponse = null;
        try {
            RazorpayClient razorpayClient = new RazorpayClient("rzp_test_9LJbdDeHn1EYIr",
                    "ZCdjpt0xMYnO3yuYtiAgQBLf");
            JSONObject options = new JSONObject();
            options.put("amount",new BigDecimal(amountPaid));

            Payment razorpayPayment= razorpayClient.Payments.capture(razor_payment_id,options);
            System.out.println(razorpayPayment.toString());
            System.out.println("Status: " + razorpayPayment.get("status").toString());

            ObjectMapper razorpayMapper = new ObjectMapper();
            razorpayMapper.configure(
                    DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            try{
                razorpayResponse = razorpayMapper.readValue(razorpayPayment.toString(),RazorpayResponse.class);
                razorpayResponse.setIdPaymentResponse(razor_payment_id);
                razorpayResponse.setPaymentProvider(PaymentResponse.PaymentProviders.RAZORPAY.name());
            } catch (Exception e){
                System.out.println(e.getMessage()); // put logs
            }

        } catch(Exception e){
            //log the exception
        }
        return razorpayResponse;
    }

    private List<InvoiceLineDto> getInvoiceLinesForMealPlans(List<AvailableRoomTypeDto> selectedRoomTypes){
        int groupSequenceNo = 1;
        List<InvoiceLineDto> invoiceLines = new ArrayList<>();
        for (AvailableRoomTypeDto selectedRoomType:selectedRoomTypes)
        {
            List<MealPlanDto> mealPlans = selectedRoomType.getMealPlans();
            for(MealPlanDto mealPlan:mealPlans){
                Integer mealPLanQuantity = mealPlan.getMealPlanItem().getQuantity();
                if (mealPLanQuantity>0){
                    InvoiceLineItemDto invoiceLine = getInvoiceLineForMealPlan(mealPlan);
                    ((InvoiceMealPlanLineDto) invoiceLine).setRoomTypeName(selectedRoomType.getTypeName());
                    ((InvoiceMealPlanLineDto) invoiceLine)
                            .setMaxAdults(selectedRoomType.getMaxAdultOccupancy() * mealPLanQuantity);
                    ((InvoiceMealPlanLineDto) invoiceLine)
                            .setMaxChilds(selectedRoomType.getMaxChildOccupancy() * mealPLanQuantity);
                    invoiceLine.setSequenceNo(getInvoiceLineSequence(invoiceLines));
                    invoiceLine.setGroupSequenceNo(groupSequenceNo);
                    List<InvoiceLineTaxDto> invoiceLineTaxesForMealPlan = invoiceLine.getInvoiceLineTaxes();
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

    private BigDecimal calculateInvoiceTotal(){
        BigDecimal invoiceTotal = BigDecimal.ZERO;
        for (InvoiceLineDto invoiceLine: this.invoiceDto.getInvoiceLines()){
            invoiceTotal = invoiceTotal.add(invoiceLine.getAmount());
        }
        return invoiceTotal;
    }

    private BigDecimal calculateInvoiceTotalWithTax(){
        BigDecimal invoiceTotalWithTax = BigDecimal.ZERO;
        for (InvoiceLineDto invoiceLine: this.invoiceDto.getInvoiceLines()){
            invoiceTotalWithTax = invoiceTotalWithTax.add(invoiceLine.getAmountWithTax());
        }
        return invoiceTotalWithTax;
    }

    private BigDecimal calculateInvoiceTotalTax(List<InvoiceTaxDto> appliedTaxes){
        BigDecimal totalInvoiceTax = BigDecimal.ZERO;
        for (InvoiceTaxDto appliedTax: appliedTaxes){
            totalInvoiceTax = totalInvoiceTax.add(appliedTax.getItemTaxAmount());
        }
        return totalInvoiceTax;
    }

    private List<InvoiceTaxDto> calculateInvoiceLevelTaxes(){
        List <InvoiceTaxDto> appliedTaxes = new ArrayList<>();
        for (InvoiceLineDto invoiceLine: this.invoiceDto.getInvoiceLines()){
            for(InvoiceLineTaxDto invoiceLineTax: invoiceLine.getInvoiceLineTaxes()){
                InvoiceTaxDto currentInvoiceTax = appliedTaxes.stream()
                        .filter(appliedTax -> invoiceLineTax.getItemTaxCode().equals(appliedTax.getItemTaxCode()))
                        .findAny()
                        .orElse(null);
                if (currentInvoiceTax == null){ //new tax code
                    InvoiceTaxDto appliedTax = new InvoiceTaxDto();
                    appliedTax.setIdInvoiceTax(invoiceLineTax.getIdInvoiceLineTax());
                    appliedTax.setItemTaxCode(invoiceLineTax.getItemTaxCode());
                    appliedTax.setItemTaxDescription(invoiceLineTax.getItemTaxDescription());
                    appliedTax.setItemTaxPercent(invoiceLineTax.getItemTaxPercent());
                    appliedTax.setItemTaxAmount(invoiceLineTax.getItemTaxAmount());
                    appliedTaxes.add(appliedTax); // add the new tax code
                }
                else //existing tax code just set the new amount
                {
                    int existingIndex = appliedTaxes.indexOf(currentInvoiceTax);
                    currentInvoiceTax.setItemTaxAmount(currentInvoiceTax.getItemTaxAmount()
                            .add(invoiceLineTax.getItemTaxAmount()));
                    appliedTaxes.set(existingIndex,currentInvoiceTax);
                }
            }
        }
        return appliedTaxes;
    }

    private InvoiceLineItemDto getInvoiceLineForMealPlan(MealPlanDto mealPlan){
        InvoiceMealPlanLineDto invoiceLine = new InvoiceMealPlanLineDto();
        invoiceLine.setInvoiceLineTypeCode(InvoiceLine.InvoiceLineTypeCodes.MEALPLAN.name());
        invoiceLine.setDescription(mealPlan.getMealPlanItem().getDescription());
        List<String> includes = new ArrayList<>();
        includes.add(mealPlan.getDescription());
        invoiceLine.setIncludes(includes);
        invoiceLine.setPrice(mealPlan.getMealPlanItem().getItemPrice().getBasePrice());
        invoiceLine.setQuantity(mealPlan.getMealPlanItem().getQuantity());
        invoiceLine.setNoOfNights(mealPlan.getMealPlanItem().getNoOfNights());
        invoiceLine.setAmount(invoiceLine.getPrice().multiply(
                new BigDecimal(invoiceLine.getQuantity())));
        if (invoiceLine.getNoOfNights()!=null && invoiceLine.getNoOfNights()>1)
            invoiceLine.setAmount(invoiceLine.getAmount().multiply(
                    new BigDecimal((invoiceLine.getNoOfNights()))));
        invoiceLine.setInvoiceLineTaxes(getInvoiceLineTaxesForMealPlan(mealPlan));
        invoiceLine.calculateAmountWithTax();
        return invoiceLine;
    }

    private InvoiceLineItemDto getInvoiceLineForItem(ItemDto item){
        if (item.getQuantity() == null || item.getQuantity() == 0)
            return null;
        InvoiceLineItemDto invoiceLine = new InvoiceLineItemDto();
        if (item.getItemType().getItemTypeCode().equals(ItemType.ItemTypeCodes.EXTRABEDADULT.name()) ||
                (item.getItemType().getItemTypeCode().equals(ItemType.ItemTypeCodes.EXTRABEDCHILD.name())))
        invoiceLine.setInvoiceLineTypeCode(InvoiceLine.InvoiceLineTypeCodes.EXTRA_PERSON.name());
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
        return invoiceLine;
    }

    private List<InvoiceLineTaxDto> getInvoiceLineTaxesForMealPlan(MealPlanDto mealPlan){
        List<InvoiceLineTaxDto> invoiceLineTaxes = new ArrayList<>();
        applicableTaxes.forEach(applicableTax->{
            InvoiceLineTaxDto invoiceLineTax = new InvoiceLineTaxDto();
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
    private Integer getInvoiceLineSequence(List<InvoiceLineDto> invoiceLines){
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
