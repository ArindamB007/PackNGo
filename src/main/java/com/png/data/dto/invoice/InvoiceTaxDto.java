package com.png.data.dto.invoice;

import java.math.BigDecimal;

public class InvoiceTaxDto {
    private Long idInvoiceTax;
    private Long invoiceId;
    private String itemTaxCode;
    private String itemTaxDescription;
    private String itemTaxPercent;
    private BigDecimal itemTaxAmount;

    public Long getIdInvoiceTax() {
        return idInvoiceTax;
    }

    public void setIdInvoiceTax(Long idInvoiceTax) {
        this.idInvoiceTax = idInvoiceTax;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getItemTaxCode() {
        return itemTaxCode;
    }

    public void setItemTaxCode(String itemTaxCode) {
        this.itemTaxCode = itemTaxCode;
    }

    public String getItemTaxDescription() {
        return itemTaxDescription;
    }

    public void setItemTaxDescription(String itemTaxDescription) {
        this.itemTaxDescription = itemTaxDescription;
    }

    public String getItemTaxPercent() {
        return itemTaxPercent;
    }

    public void setItemTaxPercent(String itemTaxPercent) {
        this.itemTaxPercent = itemTaxPercent;
    }

    public BigDecimal getItemTaxAmount() {
        return itemTaxAmount;
    }

    public void setItemTaxAmount(BigDecimal itemTaxAmount) {
        this.itemTaxAmount = itemTaxAmount;
    }
}
