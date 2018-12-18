package com.png.data.entity;

import javax.persistence.*;

@Entity
@Inheritance(strategy =InheritanceType.JOINED)
@Table(name="payment_response")
public class PaymentResponse {
    public enum PaymentProviders {RAZORPAY, GOOGLEPAY, CASH}
    @Id
    @Column(name = "id_payment_response")
    private String idPaymentResponse;

    @Column(name = "payment_provider")
    private String paymentProvider;

    public String getIdPaymentResponse() {
        return idPaymentResponse;
    }

    public void setIdPaymentResponse(String idPaymentResponse) {
        this.idPaymentResponse = idPaymentResponse;
    }

    public String getPaymentProvider() {
        return paymentProvider;
    }

    public void setPaymentProvider(String paymentProvider) {
        this.paymentProvider = paymentProvider;
    }
}