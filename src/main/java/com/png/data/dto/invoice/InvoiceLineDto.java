package com.png.data.dto.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class InvoiceLineDto {
    private Long idInvoiceLine;
    private Long invoiceId;
    private int sequenceNo;
    private int groupSequenceNo;
    private String invoiceLineTypeCode;
    private String description;
    private BigDecimal amount;
    private BigDecimal amountWithTax;
    private List<InvoiceLineTaxDto> invoiceLineTaxes;

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmountWithTax() {
        return amountWithTax;
    }

    public void setAmountWithTax(BigDecimal amountWithTax) {
        this.amountWithTax = amountWithTax;
    }

    public List<InvoiceLineTaxDto> getInvoiceLineTaxes() {
        return invoiceLineTaxes;
    }

    public void setInvoiceLineTaxes(List<InvoiceLineTaxDto> invoiceLineTaxes) {
        this.invoiceLineTaxes = invoiceLineTaxes;
    }
    public void calculateAmountWithTax(){
        this.amountWithTax = BigDecimal.ZERO;
        List<InvoiceLineTaxDto> invLineTaxes = new ArrayList<>();
        if (this.invoiceLineTaxes.size()>0){
            this.invoiceLineTaxes.forEach(invoiceLineTax->{
                InvoiceLineTaxDto invLineTax = new InvoiceLineTaxDto();
                invLineTax.setIdInvoiceLineTax(invoiceLineTax.getIdInvoiceLineTax());
                invLineTax.setInvoiceLineId(invoiceLineTax.getInvoiceLineId());
                invLineTax.setItemTaxPercent(invoiceLineTax.getItemTaxPercent());
                invLineTax.setItemTaxDescription(invoiceLineTax.getItemTaxDescription());
                invLineTax.setItemTaxCode(invoiceLineTax.getItemTaxCode());
                invLineTax.setItemTaxAmount(this.amount.multiply(new BigDecimal(invoiceLineTax.getItemTaxPercent()))
                        .divide(new BigDecimal(100)));
                this.amountWithTax = this.amountWithTax.add(invLineTax.getItemTaxAmount());
                invLineTaxes.add(invLineTax);
            });
            this.invoiceLineTaxes = invLineTaxes;
            this.amountWithTax = this.amountWithTax.add(this.amount);
        }
    }

    public int getGroupSequenceNo() {
        return groupSequenceNo;
    }

    public void setGroupSequenceNo(int groupSequenceNo) {
        this.groupSequenceNo = groupSequenceNo;
    }
}
