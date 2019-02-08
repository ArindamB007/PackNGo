package com.png.data.requests;

public class InvoiceCancellationRequest {
    private Number idInvoice;
    private Number idCancelledByUser;

    public Number getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(Number idInvoice) {
        this.idInvoice = idInvoice;
    }

    public Number getIdCancelledByUser() {
        return idCancelledByUser;
    }

    public void setIdCancelledByUser(Number idCancelledByUser) {
        this.idCancelledByUser = idCancelledByUser;
    }
}
