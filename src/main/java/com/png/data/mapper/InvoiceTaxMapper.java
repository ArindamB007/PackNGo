package com.png.data.mapper;

import com.png.data.dto.invoice.InvoicePaymentLineDto;
import com.png.data.dto.invoice.InvoiceTaxDto;
import com.png.data.entity.InvoicePaymentLine;
import com.png.data.entity.InvoiceTax;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface InvoiceTaxMapper {

    InvoiceTaxMapper INSTANCE = Mappers.getMapper(InvoiceTaxMapper.class);
    List<InvoiceTaxDto> InvoiceTaxesToInvoiceTaxDtos(List<InvoiceTax> invoiceTaxes);
    List<InvoiceTax> InvoiceTaxDtosToInvoiceTaxes(List<InvoiceTaxDto> invoiceTaxDtos);

    default InvoiceTax InvoiceTaxDtoToInvoiceTax(InvoiceTaxDto invoiceTaxDto) {
        if (invoiceTaxDto == null) {
            return null;
        }
        InvoiceTax invoiceTax = new InvoiceTax();
        invoiceTax.setIdInvoiceTax(invoiceTaxDto.getIdInvoiceTax());
        invoiceTax.setItemTaxAmount(invoiceTaxDto.getItemTaxAmount());
        invoiceTax.setItemTaxCode(invoiceTaxDto.getItemTaxCode());
        invoiceTax.setItemTaxDescription(invoiceTaxDto.getItemTaxDescription());
        invoiceTax.setItemTaxPercent(invoiceTaxDto.getItemTaxPercent());
        return invoiceTax;
    }
    default InvoiceTaxDto InvoiceTaxToInvoiceTaxDto(InvoiceTax invoiceTax) {
        if (invoiceTax == null) {
            return null;
        }
        InvoiceTaxDto invoiceTaxDto = new InvoiceTaxDto();
        invoiceTaxDto.setIdInvoiceTax(invoiceTax.getIdInvoiceTax());
        invoiceTaxDto.setItemTaxAmount(invoiceTax.getItemTaxAmount());
        invoiceTaxDto.setItemTaxCode(invoiceTax.getItemTaxCode());
        invoiceTaxDto.setItemTaxDescription(invoiceTax.getItemTaxDescription());
        invoiceTaxDto.setItemTaxPercent(invoiceTax.getItemTaxPercent());
        return invoiceTaxDto;
    }
}
