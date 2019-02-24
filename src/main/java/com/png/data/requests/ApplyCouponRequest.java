package com.png.data.requests;

import com.png.data.dto.invoice.InvoiceDto;

public class ApplyCouponRequest {
    private InvoiceDto invoice;
    private String couponCode;

    public InvoiceDto getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceDto invoice) {
        this.invoice = invoice;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }
}
