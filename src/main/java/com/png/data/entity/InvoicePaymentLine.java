package com.png.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "invoice_payment_line")
public class InvoicePaymentLine extends BaseEntity {
    public enum TransactionTypes {PAYMENT, REFUND}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_invoice_payment_line")
    private Long idInvoicePaymentLine;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "id_payment_response")
    private PaymentResponse paymentResponse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @Column(name = "sequence_no")
    private int sequenceNo;

    //signifies if its payment or refund
    @Column(name = "transaction_type")
    private String transactionType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InvoicePaymentLine )) return false;
        return idInvoicePaymentLine != null && idInvoicePaymentLine
                .equals(((InvoicePaymentLine) o).idInvoicePaymentLine);
    }
    @Override
    public int hashCode() {
        return 102;
    }

    public Long getIdInvoicePaymentLine() {
        return idInvoicePaymentLine;
    }

    public void setIdInvoicePaymentLine(Long idInvoicePaymentLine) {
        this.idInvoicePaymentLine = idInvoicePaymentLine;
    }

    public PaymentResponse getPaymentResponse() {
        return paymentResponse;
    }

    public void setPaymentResponse(PaymentResponse paymentResponse) {
        this.paymentResponse = paymentResponse;
    }

    public int getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(int sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
