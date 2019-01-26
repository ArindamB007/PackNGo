package com.png.data.dto.invoice;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "invoiceLineTypeCode", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = InvoiceLineItemDto.class, name = "ITEM"),
        @JsonSubTypes.Type(value = InvoiceLineItemDto.class, name = "EXTRA_PERSON"),
        @JsonSubTypes.Type(value = InvoiceMealPlanLineDto.class, name = "MEALPLAN")
})
public class InvoiceLineDto {
    private Long idInvoiceLine;
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

    public int getGroupSequenceNo() {
        return groupSequenceNo;
    }

    public void setGroupSequenceNo(int groupSequenceNo) {
        this.groupSequenceNo = groupSequenceNo;
    }
}
