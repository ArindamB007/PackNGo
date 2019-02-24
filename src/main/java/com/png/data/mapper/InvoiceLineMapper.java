package com.png.data.mapper;

import com.png.data.dto.invoice.InvoiceLineDto;
import com.png.data.dto.invoice.InvoiceLineItemDto;
import com.png.data.dto.invoice.InvoiceMealPlanLineDto;
import com.png.data.entity.InvoiceLine;
import com.png.data.entity.InvoiceLineItem;
import com.png.data.entity.InvoiceLineTax;
import com.png.data.entity.InvoiceMealPlanLine;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface InvoiceLineMapper {
    InvoiceLineMapper INSTANCE = Mappers.getMapper(InvoiceLineMapper.class);
    List<InvoiceLineDto> InvoiceLinesToInvoiceLineDtos(List<InvoiceLine> invoiceLines);
    List<InvoiceLine> InvoiceLineDtosToInvoiceLines(List<InvoiceLineDto> invoiceLines);
    default InvoiceLine InvoiceLineDtoToInvoiceLine(InvoiceLineDto invoiceLineDto){
        if (invoiceLineDto == null) {
            return null;
        }
        InvoiceLine invoiceLine;
        if (invoiceLineDto.getInvoiceLineTypeCode().equals(InvoiceLine.InvoiceLineTypeCodes.EXTRA_PERSON.name())) {
            InvoiceLineItem invoiceLineItem = new InvoiceLineItem();
            invoiceLineItem.setIdInvoiceLine(invoiceLineDto.getIdInvoiceLine());
            invoiceLineItem.setInvoiceLineTypeCode(invoiceLineDto.getInvoiceLineTypeCode());
            invoiceLineItem.setInvoiceLineStatusCode(invoiceLineDto.getInvoiceLineTypeCode());
            invoiceLineItem.setSequenceNo(invoiceLineDto.getSequenceNo());
            invoiceLineItem.setGroupSequenceNo(invoiceLineDto.getGroupSequenceNo());
            invoiceLineItem.setDescription(invoiceLineDto.getDescription());
            invoiceLineItem.setAmount(invoiceLineDto.getAmount());
            invoiceLineItem.setDiscountAmount(invoiceLineDto.getDiscountAmount());
            invoiceLineItem.setTaxableAmount(invoiceLineDto.getTaxableAmount());
            invoiceLineItem.setAmountWithTax(invoiceLineDto.getAmountWithTax());
            invoiceLineItem.setCancelCharge(invoiceLineDto.getCancelCharge());
            invoiceLineItem.setCancelChargeWithTax(invoiceLineDto.getCancelChargeWithTax());
            invoiceLineItem.setPrice(((InvoiceLineItemDto)invoiceLineDto).getPrice());
            invoiceLineItem.setQuantity(((InvoiceLineItemDto)invoiceLineDto).getQuantity());
            invoiceLineItem.setItemType(((InvoiceLineItemDto) invoiceLineDto).getItemType());
            invoiceLineItem.setNoOfNights(((InvoiceLineItemDto)invoiceLineDto).getNoOfNights());
            invoiceLineItem.setItemType(((InvoiceLineItemDto)invoiceLineDto).getItemType());
            List<InvoiceLineTax> invoiceLineTaxes = InvoiceLineTaxMapper.INSTANCE
                    .InvoiceLineTaxDtosToInvoiceLineTaxes(invoiceLineDto.getInvoiceLineTaxes());
            invoiceLineTaxes.forEach(invoiceLineItem::addInvoiceLineTax);
            invoiceLine = invoiceLineItem;
        }
        else if (invoiceLineDto.getInvoiceLineTypeCode().equals(InvoiceLine.InvoiceLineTypeCodes.MEALPLAN.name())){
            InvoiceMealPlanLine invoiceMealPlanLine = new InvoiceMealPlanLine();
            invoiceMealPlanLine.setIdInvoiceLine(invoiceLineDto.getIdInvoiceLine());
            invoiceMealPlanLine.setInvoiceLineTypeCode(invoiceLineDto.getInvoiceLineTypeCode());
            invoiceMealPlanLine.setInvoiceLineStatusCode(invoiceLineDto.getInvoiceLineStatusCode());
            invoiceMealPlanLine.setSequenceNo(invoiceLineDto.getSequenceNo());
            invoiceMealPlanLine.setGroupSequenceNo(invoiceLineDto.getGroupSequenceNo());
            invoiceMealPlanLine.setDescription(invoiceLineDto.getDescription());
            invoiceMealPlanLine.setAmount(invoiceLineDto.getAmount());
            invoiceMealPlanLine.setDiscountAmount(invoiceLineDto.getDiscountAmount());
            invoiceMealPlanLine.setTaxableAmount(invoiceLineDto.getTaxableAmount());
            invoiceMealPlanLine.setAmountWithTax(invoiceLineDto.getAmountWithTax());
            invoiceMealPlanLine.setCancelCharge(invoiceLineDto.getCancelCharge());
            invoiceMealPlanLine.setCancelChargeWithTax(invoiceLineDto.getCancelChargeWithTax());
            invoiceMealPlanLine.setPrice(((InvoiceMealPlanLineDto) invoiceLineDto).getPrice());
            invoiceMealPlanLine.setQuantity(((InvoiceMealPlanLineDto)invoiceLineDto).getQuantity());
            invoiceMealPlanLine.setItemType(((InvoiceMealPlanLineDto) invoiceLineDto).getItemType());
            invoiceMealPlanLine.setNoOfNights(((InvoiceMealPlanLineDto)invoiceLineDto).getNoOfNights());
            invoiceMealPlanLine.setMaxAdults(((InvoiceMealPlanLineDto)invoiceLineDto).getMaxAdults());
            invoiceMealPlanLine.setMaxChilds(((InvoiceMealPlanLineDto)invoiceLineDto).getMaxChilds());
            invoiceMealPlanLine.setRoomTypeName(((InvoiceMealPlanLineDto)invoiceLineDto).getRoomTypeName());
            List<InvoiceLineTax> invoiceLineTaxes = InvoiceLineTaxMapper.INSTANCE
                    .InvoiceLineTaxDtosToInvoiceLineTaxes(invoiceLineDto.getInvoiceLineTaxes());
            invoiceLineTaxes.forEach(invoiceMealPlanLine::addInvoiceLineTax);
            invoiceLine = invoiceMealPlanLine;
        } else {
            InvoiceLineItem invoiceLineItem = new InvoiceLineItem();
            invoiceLineItem.setIdInvoiceLine(invoiceLineDto.getIdInvoiceLine());
            invoiceLineItem.setInvoiceLineTypeCode(invoiceLineDto.getInvoiceLineTypeCode());
            invoiceLineItem.setInvoiceLineStatusCode(invoiceLineDto.getInvoiceLineStatusCode());
            invoiceLineItem.setSequenceNo(invoiceLineDto.getSequenceNo());
            invoiceLineItem.setGroupSequenceNo(invoiceLineDto.getGroupSequenceNo());
            invoiceLineItem.setDescription(invoiceLineDto.getDescription());
            invoiceLineItem.setAmount(invoiceLineDto.getAmount());
            invoiceLineItem.setTaxableAmount(invoiceLineDto.getTaxableAmount());
            invoiceLineItem.setAmountWithTax(invoiceLineDto.getAmountWithTax());
            invoiceLineItem.setDiscountAmount(invoiceLineDto.getDiscountAmount());
            invoiceLineItem.setCancelCharge(invoiceLineDto.getCancelCharge());
            invoiceLineItem.setCancelChargeWithTax(invoiceLineDto.getCancelChargeWithTax());
            List<InvoiceLineTax> invoiceLineTaxes = InvoiceLineTaxMapper.INSTANCE
                    .InvoiceLineTaxDtosToInvoiceLineTaxes(invoiceLineDto.getInvoiceLineTaxes());
            invoiceLineTaxes.forEach(invoiceLineItem::addInvoiceLineTax);
            invoiceLine = invoiceLineItem;
        }
        return invoiceLine;
    }

    default InvoiceLineDto InvoiceLineToInvoiceLineDto(InvoiceLine invoiceLine){
        if (invoiceLine == null) {
            return null;
        }
        InvoiceLineDto invoiceLineDto;
        if (invoiceLine.getInvoiceLineTypeCode().equals(InvoiceLine.InvoiceLineTypeCodes.EXTRA_PERSON.name())) {
            InvoiceLineItemDto invoiceLineItemDto = new InvoiceLineItemDto();
            invoiceLineItemDto.setIdInvoiceLine(invoiceLine.getIdInvoiceLine());
            invoiceLineItemDto.setInvoiceLineTypeCode(invoiceLine.getInvoiceLineTypeCode());
            invoiceLineItemDto.setInvoiceLineStatusCode(invoiceLine.getInvoiceLineStatusCode());
            invoiceLineItemDto.setSequenceNo(invoiceLine.getSequenceNo());
            invoiceLineItemDto.setGroupSequenceNo(invoiceLine.getGroupSequenceNo());
            invoiceLineItemDto.setDescription(invoiceLine.getDescription());
            invoiceLineItemDto.setAmount(invoiceLine.getAmount());
            invoiceLineItemDto.setDiscountAmount(invoiceLine.getDiscountAmount());
            invoiceLineItemDto.setTaxableAmount(invoiceLine.getTaxableAmount());
            invoiceLineItemDto.setAmountWithTax(invoiceLine.getAmountWithTax());
            invoiceLineItemDto.setCancelCharge(((InvoiceLineItem) invoiceLine).getCancelCharge());
            invoiceLineItemDto.setCancelChargeWithTax(((InvoiceLineItem) invoiceLine).getCancelChargeWithTax());
            invoiceLineItemDto.setInvoiceLineTaxes(InvoiceLineTaxMapper
                    .INSTANCE.InvoiceLineTaxesToInvoiceLineTaxDtos(invoiceLine.getInvoiceLineTaxes()));
            invoiceLineItemDto.setPrice(((InvoiceLineItem)invoiceLine).getPrice());
            invoiceLineItemDto.setQuantity(((InvoiceLineItem)invoiceLine).getQuantity());
            invoiceLineItemDto.setNoOfNights(((InvoiceLineItem)invoiceLine).getNoOfNights());
            invoiceLineItemDto.setItemType(((InvoiceLineItem)invoiceLine).getItemType());
            invoiceLineDto = invoiceLineItemDto;
        }
        else if (invoiceLine.getInvoiceLineTypeCode().equals(InvoiceLine.InvoiceLineTypeCodes.MEALPLAN.name())){
            InvoiceMealPlanLineDto invoiceMealPlanLineDto = new InvoiceMealPlanLineDto();
            invoiceMealPlanLineDto.setIdInvoiceLine(invoiceLine.getIdInvoiceLine());
            invoiceMealPlanLineDto.setInvoiceLineTypeCode(invoiceLine.getInvoiceLineTypeCode());
            invoiceMealPlanLineDto.setInvoiceLineStatusCode(invoiceLine.getInvoiceLineStatusCode());
            invoiceMealPlanLineDto.setSequenceNo(invoiceLine.getSequenceNo());
            invoiceMealPlanLineDto.setGroupSequenceNo(invoiceLine.getGroupSequenceNo());
            invoiceMealPlanLineDto.setDescription(invoiceLine.getDescription());
            invoiceMealPlanLineDto.setAmount(invoiceLine.getAmount());
            invoiceMealPlanLineDto.setTaxableAmount(invoiceLine.getTaxableAmount());
            invoiceMealPlanLineDto.setAmountWithTax(invoiceLine.getAmountWithTax());
            invoiceMealPlanLineDto.setDiscountAmount(invoiceLine.getDiscountAmount());
            invoiceMealPlanLineDto.setCancelCharge(((InvoiceMealPlanLine) invoiceLine).getCancelCharge());
            invoiceMealPlanLineDto.setCancelChargeWithTax(((InvoiceMealPlanLine) invoiceLine).getCancelChargeWithTax());
            invoiceMealPlanLineDto.setInvoiceLineTaxes(InvoiceLineTaxMapper
                    .INSTANCE.InvoiceLineTaxesToInvoiceLineTaxDtos(invoiceLine.getInvoiceLineTaxes()));
            invoiceMealPlanLineDto.setPrice(((InvoiceMealPlanLine)invoiceLine).getPrice());
            invoiceMealPlanLineDto.setQuantity(((InvoiceMealPlanLine)invoiceLine).getQuantity());
            invoiceMealPlanLineDto.setNoOfNights(((InvoiceMealPlanLine)invoiceLine).getNoOfNights());
            invoiceMealPlanLineDto.setMaxAdults(((InvoiceMealPlanLine)invoiceLine).getMaxAdults());
            invoiceMealPlanLineDto.setMaxChilds(((InvoiceMealPlanLine)invoiceLine).getMaxChilds());
            invoiceMealPlanLineDto.setRoomTypeName(((InvoiceMealPlanLine)invoiceLine).getRoomTypeName());
            invoiceMealPlanLineDto.setItemType(((InvoiceMealPlanLine) invoiceLine).getItemType());
            invoiceLineDto = invoiceMealPlanLineDto ;
        } else {
            invoiceLineDto = new InvoiceLineDto();
            invoiceLineDto.setIdInvoiceLine(invoiceLine.getIdInvoiceLine());
            invoiceLineDto.setInvoiceLineTypeCode(invoiceLine.getInvoiceLineTypeCode());
            invoiceLineDto.setInvoiceLineStatusCode(invoiceLine.getInvoiceLineStatusCode());
            invoiceLineDto.setSequenceNo(invoiceLine.getSequenceNo());
            invoiceLineDto.setGroupSequenceNo(invoiceLine.getGroupSequenceNo());
            invoiceLineDto.setDescription(invoiceLine.getDescription());
            invoiceLineDto.setAmount(invoiceLine.getAmount());
            invoiceLineDto.setTaxableAmount(invoiceLine.getTaxableAmount());
            invoiceLineDto.setAmountWithTax(invoiceLine.getAmountWithTax());
            invoiceLineDto.setDiscountAmount(invoiceLine.getDiscountAmount());
            invoiceLineDto.setInvoiceLineTaxes(InvoiceLineTaxMapper
                    .INSTANCE.InvoiceLineTaxesToInvoiceLineTaxDtos(invoiceLine.getInvoiceLineTaxes()));
        }
        return invoiceLineDto;
    }
}
