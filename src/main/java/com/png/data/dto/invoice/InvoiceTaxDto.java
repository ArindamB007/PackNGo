package com.png.data.dto.invoice;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.png.util.CurrecySerializer;

import java.math.BigDecimal;

public class InvoiceTaxDto {
    private Long idInvoiceTax;
    private String itemTaxCode;
    private String itemTaxDescription;
    private String itemTaxPercent;
    @JsonSerialize(using = CurrecySerializer.class)
    private BigDecimal itemTaxAmount;

    public Long getIdInvoiceTax() {
        return idInvoiceTax;
    }

    public void setIdInvoiceTax(Long idInvoiceTax) {
        this.idInvoiceTax = idInvoiceTax;
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
