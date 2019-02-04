package com.png.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "razorpay_refund_response")
@PrimaryKeyJoinColumn(name = "id_razorpay_refund_response")
public class RazorpayRefundResponse extends PaymentResponse {
    @Column(name = "entity")
    private String entity;
    @Column(name = "amount")
    private Integer amount;
    @Column(name = "currency")
    private String currency;
    @Column(name = "created_at")
    private Timestamp created_at;
    @Column(name = "payment_id")
    private String payment_id;
    @Column(name = "response_id")
    private String id; //refund response id sent as id

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Long created_at) {
        this.created_at = new Timestamp(created_at * 1000);
    }

    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
