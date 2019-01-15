package com.png.data.mapper;

import com.png.data.dto.checkinoutdetails.CheckInOutDetailsDto;
import com.png.data.dto.checkinoutdetails.InvoiceCheckInOutDetailsDto;
import com.png.data.dto.invoice.InvoiceDto;
import com.png.data.dto.user.UserContext;
import com.png.data.entity.Invoice;
import com.png.data.entity.InvoiceLine;
import com.png.data.entity.InvoiceTax;
import com.png.util.DateFormatter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface InvoiceMapper {
    InvoiceMapper INSTANCE = Mappers.getMapper(InvoiceMapper.class);

    List<InvoiceDto> InvoicesToInvoiceDtos(List<Invoice> invoices);
    default Invoice InvoiceDtoToInvoice(InvoiceDto invoiceDto) {
        if (invoiceDto == null) {
            return null;
        }
        Invoice invoice = new Invoice();
        invoice.setIdInvoice(invoiceDto.getIdInvoice());
        invoice.setInvoiceNo(invoiceDto.getInvoiceNo());
        invoice.setInvoiceTotal(invoiceDto.getInvoiceTotal());
        invoice.setInvoiceTotalWithTax(invoiceDto.getInvoiceTotalWithTax());
        invoice.setInvoiceTotalTax(invoiceDto.getInvoiceTotalTax());
        invoice.setAmountPaid(invoiceDto.getAmountPaid());
        invoice.setAmountPending(invoiceDto.getAmountPending());
        invoice.setAmountToBePaid(invoiceDto.getAmountToBePaid());
        invoice.setInvoiceStatusCode(invoiceDto.getInvoiceStatusCode());
        invoice.setTravellerEmail(invoiceDto.getTravellerEmail());
        invoice.setTravellerFirstName(invoiceDto.getTravellerFirstName());
        invoice.setTravellerLastName(invoiceDto.getTravellerLastName());
        invoice.setTravellerMiddleName(invoiceDto.getTravellerMiddleName());
        invoice.setTravellerMobile(invoiceDto.getTravellerMobile());
        List<InvoiceTax> appliedTaxes = InvoiceTaxMapper.INSTANCE.InvoiceTaxDtosToInvoiceTaxes(
                invoiceDto.getAppliedTaxes());
        appliedTaxes.forEach(invoice::addInvoiceTax);
        List<InvoiceLine> invoiceLines = InvoiceLineMapper.INSTANCE.InvoiceLineDtosToInvoiceLines(
                invoiceDto.getInvoiceLines());
        invoiceLines.forEach(invoice::addInvoiceLine);
        invoice.setProperty(null); // will be set after payment is added
        invoice.setUser(null); // will be set after payment line is added
        invoice.setInvoicePaymentLines(null); //payment yet to be done
        invoice.setBooking(null); // Booking yet to be done
        return invoice;
    }

    default InvoiceDto InvoiceToInvoiceDto(Invoice invoice) {
        UserContext userContext = new UserContext();
        if (invoice == null) {
            return null;
        }
        Timestamp checkIn = invoice.getBooking().getCheckInTimestamp();
        Timestamp checkOut = invoice.getBooking().getCheckOutTimestamp();
        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setIdInvoice(invoice.getIdInvoice());
        invoiceDto.setInvoiceNo(invoice.getInvoiceNo());
        invoiceDto.setCheckInTimestamp(DateFormatter.getDateStringFromTimestamp(checkIn));
        invoiceDto.setCheckOutTimestamp(DateFormatter.getDateStringFromTimestamp(checkOut));
        invoiceDto.setNights(DateFormatter.getNights(checkOut, checkIn));
        invoiceDto.setInvoiceTotal(invoice.getInvoiceTotal());
        invoiceDto.setInvoiceTotalWithTax(invoice.getInvoiceTotalWithTax());
        invoiceDto.setInvoiceTotalTax(invoice.getInvoiceTotalTax());
        invoiceDto.setAmountPending(invoice.getAmountPending());
        invoiceDto.setAmountToBePaid(invoice.getAmountToBePaid());
        invoiceDto.setAmountPaid(invoice.getAmountPaid());
        invoiceDto.setInvoiceStatusCode(invoice.getInvoiceStatusCode());
        invoiceDto.setTravellerEmail(invoice.getTravellerEmail());
        invoiceDto.setTravellerFirstName(invoice.getTravellerFirstName());
        invoiceDto.setTravellerLastName(invoice.getTravellerLastName());
        invoiceDto.setTravellerMiddleName(invoice.getTravellerMiddleName());
        invoiceDto.setTravellerMobile(invoice.getTravellerMobile());
        invoiceDto.setInvoiceDate(DateFormatter.getDateStringFromTimestamp(invoice.getCreatedTimestamp()));
        invoiceDto.setAppliedTaxes(InvoiceTaxMapper.INSTANCE
                .InvoiceTaxesToInvoiceTaxDtos(invoice.getAppliedTaxes()));
        invoiceDto.setProperty(PropertyMapper.INSTANCE
                .propertyToPropertyDto(invoice.getProperty()));
        invoiceDto.setInvoiceLines(InvoiceLineMapper.INSTANCE
                .InvoiceLinesToInvoiceLineDtos(invoice.getInvoiceLines()));
        invoiceDto.setInvoicePaymentLines(InvoicePaymentLineMapper.INSTANCE
                .InvoicePaymentLineToInvoicePaymentLineDtos(invoice.getInvoicePaymentLines()));
        invoiceDto.setInvoiceOccupancyInfo();
        userContext.setUserDetails(invoice.getUser());
        invoiceDto.setUserContext(userContext);
        return invoiceDto;
    }
}
