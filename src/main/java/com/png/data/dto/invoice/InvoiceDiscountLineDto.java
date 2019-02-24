package com.png.data.dto.invoice;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.png.util.CurrecySerializer;

import java.math.BigDecimal;

public class InvoiceDiscountLineDto {
    private Long idInvoiceDiscountLine;
    private int sequenceNo;
    private String description;
    private String couponCode;
    private String couponType;
    @JsonSerialize(using = CurrecySerializer.class)
    private BigDecimal amount;

    public Long getIdInvoiceDiscountLine() {
        return idInvoiceDiscountLine;
    }

    public void setIdInvoiceDiscountLine(Long idInvoiceDiscountLine) {
        this.idInvoiceDiscountLine = idInvoiceDiscountLine;
    }

    public int getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(int sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }
}
