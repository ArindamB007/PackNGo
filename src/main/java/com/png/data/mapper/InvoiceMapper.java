package com.png.data.mapper;

import com.png.data.dto.checkinoutdetails.CheckInOutDetailsDto;
import com.png.data.dto.checkinoutdetails.InvoiceCheckInOutDetailsDto;
import com.png.data.dto.invoice.InvoiceDto;
import com.png.data.dto.user.UserContext;
import com.png.data.entity.Invoice;
import com.png.data.entity.InvoiceDiscountLine;
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
        try {
            invoice.setIdInvoice(invoiceDto.getIdInvoice());
            invoice.setInvoiceNo(invoiceDto.getInvoiceNo());
            invoice.setInvoiceTotal(invoiceDto.getInvoiceTotal());
            invoice.setInvoiceTotalWithTax(invoiceDto.getInvoiceTotalWithTax());
            invoice.setInvoiceTotalTax(invoiceDto.getInvoiceTotalTax());
            invoice.setAmountPaid(invoiceDto.getAmountPaid());
            invoice.setInvoiceDiscount(invoiceDto.getInvoiceDiscount());
            invoice.setAmountPending(invoiceDto.getAmountPending());
            invoice.setAmountRefunded(invoiceDto.getAmountRefunded());
            invoice.setAmountToBeRefunded(invoiceDto.getAmountToBeRefunded());
            invoice.setInvoiceTotalRefund(invoiceDto.getInvoiceTotalRefund());
            invoice.setInvoiceTotalWithTaxRefund(invoiceDto.getInvoiceTotalWithTaxRefund());
            invoice.setInvoiceStatusCode(invoiceDto.getInvoiceStatusCode());
            invoice.setTravellerEmail(invoiceDto.getTravellerEmail());
            invoice.setTravellerFirstName(invoiceDto.getTravellerFirstName());
            invoice.setTravellerLastName(invoiceDto.getTravellerLastName());
            invoice.setTravellerMiddleName(invoiceDto.getTravellerMiddleName());
            invoice.setTravellerMobile(invoiceDto.getTravellerMobile());
            invoice.setCheckInTimestamp(DateFormatter.getTimestampFromString(invoiceDto.getCheckInTimestamp()));
            invoice.setCheckOutTimestamp(DateFormatter.getTimestampFromString(invoiceDto.getCheckOutTimestamp()));
            invoice.setAllow_cancel_flag(invoiceDto.getAllowCancelFlag());
            List<InvoiceTax> appliedTaxes = InvoiceTaxMapper.INSTANCE.InvoiceTaxDtosToInvoiceTaxes(
                    invoiceDto.getAppliedTaxes());
            if (appliedTaxes != null && appliedTaxes.size() > 0)
                appliedTaxes.forEach(invoice::addInvoiceTax);
            List<InvoiceLine> invoiceLines = InvoiceLineMapper.INSTANCE.InvoiceLineDtosToInvoiceLines(
                    invoiceDto.getInvoiceLines());
            if (invoiceLines != null && invoiceLines.size() > 0)
                invoiceLines.forEach(invoice::addInvoiceLine);
            List<InvoiceDiscountLine> invoiceDiscountLines = InvoiceDiscountLineMapper.INSTANCE
                    .InvoiceDiscountLineDtosToInvoiceDiscountLines(invoiceDto.getInvoiceDiscountLines());
            if (invoiceDiscountLines != null && invoiceDiscountLines.size() > 0)
                invoiceDiscountLines.forEach(invoice::addInvoiceDiscountLine);
            invoice.setProperty(null); // will be set after payment is added
            invoice.setUser(null); // will be set after payment line is added
            invoice.setInvoicePaymentLines(null); //payment yet to be done
            invoice.setBookings(null); // Booking yet to be done
        } catch (Exception e) {
            e.printStackTrace(); // todo add logging
        }
        return invoice;
    }

    default InvoiceDto InvoiceToInvoiceDto(Invoice invoice) {
        UserContext userContext = new UserContext();
        UserContext cancelledByUserContext = new UserContext();
        if (invoice == null) {
            return null;
        }
        Timestamp checkIn = invoice.getCheckInTimestamp();
        Timestamp checkOut = invoice.getCheckOutTimestamp();
        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setIdInvoice(invoice.getIdInvoice());
        invoiceDto.setInvoiceNo(invoice.getInvoiceNo());
        invoiceDto.setCheckInTimestamp(DateFormatter.getDateStringFromTimestamp(checkIn));
        invoiceDto.setCheckOutTimestamp(DateFormatter.getDateStringFromTimestamp(checkOut));
        invoiceDto.setAllowCancelFlag(invoice.getAllow_cancel_flag());
        invoiceDto.setNights(DateFormatter.getNights(checkOut, checkIn));
        invoiceDto.setInvoiceTotal(invoice.getInvoiceTotal());
        invoiceDto.setInvoiceTotalWithTax(invoice.getInvoiceTotalWithTax());
        invoiceDto.setInvoiceTotalTax(invoice.getInvoiceTotalTax());
        invoiceDto.setInvoiceDiscount(invoice.getInvoiceDiscount());
        invoiceDto.setAmountRefunded(invoice.getAmountRefunded());
        invoiceDto.setAmountToBeRefunded(invoice.getAmountToBeRefunded());
        invoiceDto.setInvoiceTotalRefund(invoice.getInvoiceTotalRefund());
        invoiceDto.setInvoiceTotalWithTaxRefund(invoice.getInvoiceTotalWithTaxRefund());
        invoiceDto.setAmountPending(invoice.getAmountPending());
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
        invoiceDto.setInvoiceDiscountLines(InvoiceDiscountLineMapper.INSTANCE
                .InvoiceDiscountLinesToInvoiceDiscountLineDtos(invoice.getInvoiceDiscountLines()));
        invoiceDto.setInvoiceOccupancyInfo();
        userContext.setUserDetails(invoice.getUser());
        invoiceDto.setUserContext(userContext);
        cancelledByUserContext.setUserDetails(invoice.getCancelledByUser());
        invoiceDto.setCancelledByUser(cancelledByUserContext);
        return invoiceDto;
    }
}
