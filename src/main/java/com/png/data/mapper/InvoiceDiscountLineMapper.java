package com.png.data.mapper;

import com.png.data.dto.invoice.InvoiceDiscountLineDto;
import com.png.data.entity.InvoiceDiscountLine;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface InvoiceDiscountLineMapper {
    InvoiceDiscountLineMapper INSTANCE = Mappers.getMapper(InvoiceDiscountLineMapper.class);

    List<InvoiceDiscountLineDto> InvoiceDiscountLinesToInvoiceDiscountLineDtos
            (List<InvoiceDiscountLine> invoiceDiscountLines);

    List<InvoiceDiscountLine> InvoiceDiscountLineDtosToInvoiceDiscountLines
            (List<InvoiceDiscountLineDto> invoiceLines);

    default InvoiceDiscountLineDto InvoiceDiscountLineToInvoiceDiscountLineDto
            (InvoiceDiscountLine invoiceDiscountLine) {
        if (invoiceDiscountLine == null)
            return null;
        InvoiceDiscountLineDto invoiceDiscountLineDto = new InvoiceDiscountLineDto();
        invoiceDiscountLineDto.setIdInvoiceDiscountLine(invoiceDiscountLine.getIdInvoiceDiscountLine());
        invoiceDiscountLineDto.setSequenceNo(invoiceDiscountLine.getSequenceNo());
        invoiceDiscountLineDto.setCouponCode(invoiceDiscountLine.getCouponCode());
        invoiceDiscountLineDto.setCouponType(invoiceDiscountLine.getCouponType());
        invoiceDiscountLineDto.setDescription(invoiceDiscountLine.getDescription());
        invoiceDiscountLineDto.setAmount(invoiceDiscountLine.getAmount());
        return invoiceDiscountLineDto;
    }

    default InvoiceDiscountLine InvoiceDiscountLineDtoToInvoiceDiscountLine
            (InvoiceDiscountLineDto invoiceDiscountLineDto) {
        if (invoiceDiscountLineDto == null)
            return null;
        InvoiceDiscountLine invoiceDiscountLine = new InvoiceDiscountLine();
        invoiceDiscountLine.setIdInvoiceDiscountLine(invoiceDiscountLineDto.getIdInvoiceDiscountLine());
        invoiceDiscountLine.setSequenceNo(invoiceDiscountLineDto.getSequenceNo());
        invoiceDiscountLine.setCouponCode(invoiceDiscountLineDto.getCouponCode());
        invoiceDiscountLine.setCouponType(invoiceDiscountLineDto.getCouponType());
        invoiceDiscountLine.setDescription(invoiceDiscountLineDto.getDescription());
        invoiceDiscountLine.setAmount(invoiceDiscountLineDto.getAmount());
        return invoiceDiscountLine;
    }
}
