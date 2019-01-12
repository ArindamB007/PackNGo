package com.png.data.dto.invoice;


public class InvoicePaymentLineDto {

    private Long idInvoicePaymentLine;

    private int sequenceNo;

    private String transactionType;

    private String transactionId;

    private int amountPaid;

    private String transactionTimeStamp;

    private String transactionMode;

    public Long getIdInvoicePaymentLine() {
        return idInvoicePaymentLine;
    }

    public void setIdInvoicePaymentLine(Long idInvoicePaymentLine) {
        this.idInvoicePaymentLine = idInvoicePaymentLine;
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

    public int getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(int amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getTransactionTimeStamp() {
        return transactionTimeStamp;
    }

    public void setTransactionTimeStamp(String transactionTimeStamp) {
        this.transactionTimeStamp = transactionTimeStamp;
    }

    public String getTransactionMode() {
        return transactionMode;
    }

    public void setTransactionMode(String transactionMode) {
        this.transactionMode = transactionMode;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
