package com.png.data.dto.invoice;

import com.png.data.dto.BaseEntityDto;
import com.png.data.dto.bookingcart.BookingCartDto;
import com.png.data.dto.property.PropertyDto;
import com.png.data.dto.user.UserContext;
import com.png.data.entity.Invoice;
import com.png.data.entity.Property;

public class InvoiceDto {
    private Long idInvoice;
    private String invoiceDate;
    private String invoiceNo;
    private Float invoiceTotal;
    private Float invoiceTotalWithTax;
    private String invoiceStatusCode;
    private InvoiceLineDto[] invoiceLines;
    private PropertyDto property;
    private UserContext userContext;

    public InvoiceDto(BookingCartDto bookingCart) {
        this.setInvoiceStatusCode(Invoice.InvoiceStatusCodes.NEW.toString());
        this.setUserContext(bookingCart.getUserContext());
        this.setProperty(bookingCart.getSelectedProperty());

    }

    public Long getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(Long idInvoice) {
        this.idInvoice = idInvoice;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public Float getInvoiceTotal() {
        return invoiceTotal;
    }

    public void setInvoiceTotal(Float invoiceTotal) {
        this.invoiceTotal = invoiceTotal;
    }

    public Float getInvoiceTotalWithTax() {
        return invoiceTotalWithTax;
    }

    public void setInvoiceTotalWithTax(Float invoiceTotalWithTax) {
        this.invoiceTotalWithTax = invoiceTotalWithTax;
    }

    public String getInvoiceStatusCode() {
        return invoiceStatusCode;
    }

    public void setInvoiceStatusCode(String invoiceStatusCode) {
        this.invoiceStatusCode = invoiceStatusCode;
    }

    public InvoiceLineDto[] getInvoiceLines() {
        return invoiceLines;
    }

    public void setInvoiceLines(InvoiceLineDto[] invoiceLines) {
        this.invoiceLines = invoiceLines;
    }

    public PropertyDto getProperty() {
        return property;
    }

    public void setProperty(PropertyDto property) {
        this.property = property;
    }

    public UserContext getUserContext() {
        return userContext;
    }

    public void setUserContext(UserContext userContext) {
        this.userContext = userContext;
    }

}
