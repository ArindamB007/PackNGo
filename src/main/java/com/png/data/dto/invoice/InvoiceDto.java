package com.png.data.dto.invoice;

import com.png.data.dto.availableroomtype.AvailableRoomTypeDto;
import com.png.data.dto.availableroomtype.MealPlanDto;
import com.png.data.dto.bookingcart.BookingCartDto;
import com.png.data.dto.item.ItemDto;
import com.png.data.dto.property.PropertyDto;
import com.png.data.dto.user.UserContext;
import com.png.data.entity.*;
import com.png.data.repository.ItemTaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

public class InvoiceDto {
    private Long idInvoice;
    private String invoiceDate;
    private String invoiceNo;
    private BigDecimal invoiceTotal;
    private BigDecimal invoiceTotalWithTax;
    private BigDecimal invoiceTotalTax;
    private List<InvoiceTaxDto> appliedTaxes;
    private String invoiceStatusCode;
    private List<InvoiceLineItemDto> invoiceLines;
    private PropertyDto property;
    private UserContext userContext;

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

    public BigDecimal getInvoiceTotal() {
        return invoiceTotal;
    }

    public void setInvoiceTotal(BigDecimal invoiceTotal) {
        this.invoiceTotal = invoiceTotal;
    }

    public BigDecimal getInvoiceTotalWithTax() {
        return invoiceTotalWithTax;
    }

    public void setInvoiceTotalWithTax(BigDecimal invoiceTotalWithTax) {
        this.invoiceTotalWithTax = invoiceTotalWithTax;
    }

    public String getInvoiceStatusCode() {
        return invoiceStatusCode;
    }

    public void setInvoiceStatusCode(String invoiceStatusCode) {
        this.invoiceStatusCode = invoiceStatusCode;
    }

    public BigDecimal getInvoiceTotalTax() {
        return invoiceTotalTax;
    }

    public void setInvoiceTotalTax(BigDecimal invoiceTotalTax) {
        this.invoiceTotalTax = invoiceTotalTax;
    }

    public List<InvoiceTaxDto> getAppliedTaxes() {
        return appliedTaxes;
    }

    public void setAppliedTaxes(List<InvoiceTaxDto> appliedTaxes) {
        this.appliedTaxes = appliedTaxes;
    }

    public List<InvoiceLineItemDto> getInvoiceLines() {
        return invoiceLines;
    }

    public void setInvoiceLines(List<InvoiceLineItemDto> invoiceLines) {
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
