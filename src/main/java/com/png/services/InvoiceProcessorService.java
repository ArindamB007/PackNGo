package com.png.services;

import com.png.data.dto.availableroomtype.AvailableRoomTypeDto;
import com.png.data.dto.availableroomtype.MealPlanDto;
import com.png.data.dto.bookingcart.BookingCartDto;
import com.png.data.dto.invoice.*;
import com.png.data.dto.item.ItemDto;
import com.png.data.entity.Invoice;
import com.png.data.entity.InvoiceLine;
import com.png.data.entity.ItemTax;
import com.png.data.entity.ItemType;
import com.png.data.repository.ItemTaxRepository;
import com.png.util.StringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceProcessorService {

    private InvoiceDto invoiceDto;

    @Autowired
    private ItemTaxRepository itemTaxRepository;

    private List<ItemTax> applicableTaxes;

    public InvoiceDto createInvoice(BookingCartDto bookingCartDto){
        invoiceDto = new InvoiceDto();
        applicableTaxes = itemTaxRepository.findAll();
        invoiceDto.setInvoiceNo(createInvoiceNumber());
        invoiceDto.setInvoiceStatusCode(Invoice.InvoiceStatusCodes.TOTALED.name());
        invoiceDto.setUserContext(bookingCartDto.getUserContext());
        invoiceDto.setProperty(bookingCartDto.getSelectedProperty());
        invoiceDto.setInvoiceLines(getInvoiceLinesForMealPlans(bookingCartDto.getSelectedRoomTypes()));
        invoiceDto.setAppliedTaxes(calculateInvoiceLevelTaxes());
        invoiceDto.setInvoiceTotalTax(calculateInvoiceTotalTax(invoiceDto.getAppliedTaxes()));;
        invoiceDto.setInvoiceTotalWithTax(calculateInvoiceTotalWithTax());
        invoiceDto.setInvoiceTotal(calculateInvoiceTotal());
        return invoiceDto;
    }

    private List<InvoiceLineItemDto> getInvoiceLinesForMealPlans(List<AvailableRoomTypeDto> selectedRoomTypes){
        int groupSequenceNo = 1;
        List<InvoiceLineItemDto> invoiceLines = new ArrayList<>();
        for (AvailableRoomTypeDto selectedRoomType:selectedRoomTypes)
        {
            List<MealPlanDto> mealPlans = selectedRoomType.getMealPlans();
            for(MealPlanDto mealPlan:mealPlans){
                if (mealPlan.getMealPlanItem().getQuantity()>0){
                    InvoiceLineItemDto invoiceLine = getInvoiceLineForMealPlan(mealPlan);
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
        for (InvoiceLineItemDto invoiceLine: this.invoiceDto.getInvoiceLines()){
            for(InvoiceLineTaxDto invoiceLineTax: invoiceLine.getInvoiceLineTaxes()){
                InvoiceTaxDto currentInvoiceTax = appliedTaxes.stream()
                        .filter(appliedTax -> invoiceLineTax.getItemTaxCode().equals(appliedTax.getItemTaxCode()))
                        .findAny()
                        .orElse(null);
                if (currentInvoiceTax == null){ //new tax code
                    InvoiceTaxDto appliedTax = new InvoiceTaxDto();
                    appliedTax.setInvoiceId(invoiceLine.getInvoiceId());
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
        invoiceLine.setInvoiceLineTypeCode(InvoiceLine.InvoiceLineTypeCodes.ITEM.name());
        invoiceLine.setDescription(mealPlan.getMealPlanItem().getDescription());
        List<String> includes = new ArrayList<>();
        includes.add(mealPlan.getDescription());
        invoiceLine.setIncludes(includes);
        invoiceLine.setPrice(mealPlan.getMealPlanItem().getItemPrice().getBasePrice());
        invoiceLine.setQuantity(mealPlan.getMealPlanItem().getQuantity());
        invoiceLine.setAmount(invoiceLine.getPrice().multiply(
                new BigDecimal(invoiceLine.getQuantity())));
        invoiceLine.setInvoiceLineTaxes(getInvoiceLineTaxesForMealPlan(mealPlan));
        invoiceLine.calculateAmountWithTax();
        return invoiceLine;
    }

    private InvoiceLineItemDto getInvoiceLineForItem(ItemDto item){
        if (item.getQuantity() == null || item.getQuantity() == 0)
            return null;
        InvoiceLineItemDto invoiceLine = new InvoiceLineItemDto();
        invoiceLine.setInvoiceLineTypeCode(InvoiceLine.InvoiceLineTypeCodes.ITEM.name());
        invoiceLine.setDescription(item.getDescription());
        invoiceLine.setPrice(item.getItemPrice().getBasePrice());
        invoiceLine.setQuantity(item.getQuantity());
        invoiceLine.setAmount(invoiceLine.getPrice().multiply(
                new BigDecimal(invoiceLine.getQuantity())));
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
    private Integer getInvoiceLineSequence(List<InvoiceLineItemDto> invoiceLines){
        return invoiceLines.size() +1;
    }

    public static String createInvoiceNumber() {
        int invNoLength = 8;
        return StringGenerator.randomAlphaNumeric(invNoLength);
    }
}
