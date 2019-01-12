package com.png.data.mapper;

import com.png.data.dto.invoice.InvoiceLineTaxDto;
import com.png.data.dto.invoice.InvoiceTaxDto;
import com.png.data.entity.InvoiceLineTax;
import com.png.data.entity.InvoiceTax;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface InvoiceLineTaxMapper {
    InvoiceLineTaxMapper INSTANCE = Mappers.getMapper(InvoiceLineTaxMapper.class);

    List<InvoiceLineTaxDto> InvoiceLineTaxesToInvoiceLineTaxDtos(List<InvoiceLineTax> invoiceLineTaxes);
    List<InvoiceLineTax> InvoiceLineTaxDtosToInvoiceLineTaxes(List<InvoiceLineTaxDto> invoiceLineTaxDtos);

    default InvoiceLineTax InvoiceLineTaxDtoToInvoiceLineTax(InvoiceLineTaxDto invoiceLineTaxDto) {
        if (invoiceLineTaxDto == null) {
            return null;
        }
        InvoiceLineTax invoiceLineTax = new InvoiceLineTax();
        invoiceLineTax.setIdInvoiceLineTax(invoiceLineTaxDto.getIdInvoiceLineTax());
        invoiceLineTax.setItemTaxAmount(invoiceLineTaxDto.getItemTaxAmount());
        invoiceLineTax.setItemTaxCode(invoiceLineTaxDto.getItemTaxCode());
        invoiceLineTax.setItemTaxDescription(invoiceLineTaxDto.getItemTaxDescription());
        invoiceLineTax.setItemTaxPercent(invoiceLineTaxDto.getItemTaxPercent());
        return invoiceLineTax;
    }
    default InvoiceLineTaxDto InvoiceLineTaxToInvoiceLineTaxDto(InvoiceLineTax invoiceLineTax) {
        if (invoiceLineTax == null) {
            return null;
        }
        InvoiceLineTaxDto invoiceLineTaxDto = new InvoiceLineTaxDto();
        invoiceLineTaxDto.setIdInvoiceLineTax(invoiceLineTax.getIdInvoiceLineTax());
        invoiceLineTaxDto.setItemTaxAmount(invoiceLineTax.getItemTaxAmount());
        invoiceLineTaxDto.setItemTaxCode(invoiceLineTax.getItemTaxCode());
        invoiceLineTaxDto.setItemTaxDescription(invoiceLineTax.getItemTaxDescription());
        invoiceLineTaxDto.setItemTaxPercent(invoiceLineTax.getItemTaxPercent());
        return invoiceLineTaxDto;
    }

}
