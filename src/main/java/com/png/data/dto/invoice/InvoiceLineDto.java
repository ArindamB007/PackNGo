package com.png.data.dto.invoice;

public class InvoiceLineDto {
    private Long idInvoiceLine;
    private Long invoiceId;
    private int sequenceNo;
    private String invoiceLineTypeCode;
    private String description;
    private float price;
    private Integer quantity;
    private float amount;
    private float amountWithTax;
    private InvoiceLineTaxDto invoiceLineTax;

    public Long getIdInvoiceLine() {
        return idInvoiceLine;
    }

    public void setIdInvoiceLine(Long idInvoiceLine) {
        this.idInvoiceLine = idInvoiceLine;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(int sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    public String getInvoiceLineTypeCode() {
        return invoiceLineTypeCode;
    }

    public void setInvoiceLineTypeCode(String invoiceLineTypeCode) {
        this.invoiceLineTypeCode = invoiceLineTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getAmountWithTax() {
        return amountWithTax;
    }

    public void setAmountWithTax(float amountWithTax) {
        this.amountWithTax = amountWithTax;
    }

    public InvoiceLineTaxDto getInvoiceLineTax() {
        return invoiceLineTax;
    }

    public void setInvoiceLineTax(InvoiceLineTaxDto invoiceLineTax) {
        this.invoiceLineTax = invoiceLineTax;
    }
}
