package com.png.data.dto.invoice;

public class InvoiceLineTaxDto {
    public Long getIdInvoiceLineTax() {
        return idInvoiceLineTax;
    }

    public void setIdInvoiceLineTax(Long idInvoiceLineTax) {
        this.idInvoiceLineTax = idInvoiceLineTax;
    }

    public Long getInvoiceLineId() {
        return invoiceLineId;
    }

    public void setInvoiceLineId(Long invoiceLineId) {
        this.invoiceLineId = invoiceLineId;
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

    public String getItemTaxAmount() {
        return itemTaxAmount;
    }

    public void setItemTaxAmount(String itemTaxAmount) {
        this.itemTaxAmount = itemTaxAmount;
    }

    private Long idInvoiceLineTax;
    private Long invoiceLineId;
    private String itemTaxCode;
    private String itemTaxDescription;
    private String itemTaxPercent;
    private String itemTaxAmount;
}
