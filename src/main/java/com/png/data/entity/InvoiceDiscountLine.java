package com.png.data.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "invoice_discount_line")
public class InvoiceDiscountLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_invoice_discount_line")
    private Long idInvoiceDiscountLine;
    @Column(name = "sequence_no")
    private int sequenceNo;
    @Column(name = "description")
    private String description;
    @Column(name = "coupon_code")
    private String couponCode;
    @Column(name = "coupon_type")
    private String couponType;
    @Column(name = "amount")
    private BigDecimal amount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

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

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }
}
