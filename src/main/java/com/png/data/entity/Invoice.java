package com.png.data.entity;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "invoice")
public class Invoice extends BaseEntity{
    public enum InvoiceStatusCodes {TOTALED,
        PAID,PAYMENT_PENDING,PAID_PENDING_BANK};
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_invoice")
    private Long idInvoice;

    @Column(name = "invoice_no", nullable = false,unique = true)
    private String invoiceNo;

    @Column(name = "invoice_total")
    private BigDecimal invoiceTotal;

    @Column(name = "invoice_total_with_tax")
    private BigDecimal invoiceTotalWithTax;

    @Column(name = "amount_to_be_paid")
    private BigDecimal amountToBePaid;

    @Column(name = "amount_paid")
    private BigDecimal amountPaid;

    @Column (name= "amount_pending")
    private BigDecimal amountPending;

    @Column(name = "invoice_total_tax")
    private BigDecimal invoiceTotalTax;

    @Column(name = "invoice_status_code")
    private String invoiceStatusCode;

    @Column(name="traveller_first_name")
    @NotEmpty
    private String travellerFirstName;

    @Column(name="traveller_middle_name")
    private String travellerMiddleName;

    @Column(name="traveller_last_name")
    @NotEmpty
    private String travellerLastName;

    @Column(name="traveller_email")
    @Email
    @NotEmpty
    private String travellerEmail;

    @Column (name="traveller_mobile")
    @NotEmpty
    private String travellerMobile;

    @OneToMany (mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceTax> appliedTaxes;

    @OneToMany (mappedBy = "invoice",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceLine> invoiceLines;

    @OneToMany (mappedBy = "invoice",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoicePaymentLine> invoicePaymentLines;

    @OneToOne (mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
    private Booking booking;

    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    @PrimaryKeyJoinColumn
    private Property property;

    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    @PrimaryKeyJoinColumn
    private User user;

    public Long getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(Long idInvoice) {
        this.idInvoice = idInvoice;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public BigDecimal getInvoiceTotal() {
        return invoiceTotal;
    }

    public void setInvoiceTotal(BigDecimal invoiceTotal) {
        this.invoiceTotal = invoiceTotal;
    }

    public BigDecimal getInvoiceTotalWithTax() {
        return invoiceTotalWithTax;
    }

    public void setInvoiceTotalWithTax(BigDecimal invoiceTotalWithTax) {
        this.invoiceTotalWithTax = invoiceTotalWithTax;
    }

    public BigDecimal getInvoiceTotalTax() {
        return invoiceTotalTax;
    }

    public void setInvoiceTotalTax(BigDecimal invoiceTotalTax) {
        this.invoiceTotalTax = invoiceTotalTax;
    }


    public String getInvoiceStatusCode() {
        return invoiceStatusCode;
    }

    public void setInvoiceStatusCode(String invoiceStatusCode) {
        this.invoiceStatusCode = invoiceStatusCode;
    }

    public List<InvoiceTax> getAppliedTaxes() {
        return appliedTaxes;
    }

    public void setAppliedTaxes(List<InvoiceTax> appliedTaxes) {
        this.appliedTaxes = appliedTaxes;
    }

    public List<InvoiceLine> getInvoiceLines() {
        return invoiceLines;
    }

    public void setInvoiceLines(List<InvoiceLine> invoiceLines) {
        this.invoiceLines = invoiceLines;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTravellerFirstName() {
        return travellerFirstName;
    }

    public void setTravellerFirstName(String travellerFirstName) {
        this.travellerFirstName = travellerFirstName;
    }

    public String getTravellerMiddleName() {
        return travellerMiddleName;
    }

    public void setTravellerMiddleName(String travellerMiddleName) {
        this.travellerMiddleName = travellerMiddleName;
    }

    public String getTravellerLastName() {
        return travellerLastName;
    }

    public void setTravellerLastName(String travellerLastName) {
        this.travellerLastName = travellerLastName;
    }

    public String getTravellerEmail() {
        return travellerEmail;
    }

    public void setTravellerEmail(String travellerEmail) {
        this.travellerEmail = travellerEmail;
    }

    public String getTravellerMobile() {
        return travellerMobile;
    }

    public void setTravellerMobile(String travellerMobile) {
        this.travellerMobile = travellerMobile;
    }

    public List<InvoicePaymentLine> getInvoicePaymentLines() {
        return invoicePaymentLines;
    }

    public void setInvoicePaymentLines(List<InvoicePaymentLine> invoicePaymentLines) {
        this.invoicePaymentLines = invoicePaymentLines;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        if (booking ==null){
            if (this.booking != null)
                this.booking.setInvoice(null);
        }
        else{
            booking.setInvoice(this);
        }
        this.booking = booking;
    }

    public BigDecimal getAmountToBePaid() {
        return amountToBePaid;
    }

    public void setAmountToBePaid(BigDecimal amountToBePaid) {
        this.amountToBePaid = amountToBePaid;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    public BigDecimal getAmountPending() {
        return amountPending;
    }

    public void setAmountPending(BigDecimal amountPending) {
        this.amountPending = amountPending;
    }

    public void addInvoiceTax(InvoiceTax invoiceTax){
        if (this.appliedTaxes == null)
            this.appliedTaxes = new ArrayList<>();
        this.appliedTaxes.add(invoiceTax);
        if (invoiceTax != null)
            invoiceTax.setInvoice(this);
    }

    public void removeInvoiceTax(InvoiceTax invoiceTax){
        this.appliedTaxes.remove(invoiceTax);
        if (invoiceTax != null)
            invoiceTax.setInvoice(null);
    }

    public void addInvoicePaymentLine(InvoicePaymentLine invoicePaymentLine){
        if (invoicePaymentLine == null)
            return;
        if (this.invoicePaymentLines == null){
            this.invoicePaymentLines = new ArrayList<>();
        }
        invoicePaymentLine.setSequenceNo(invoicePaymentLines.size()+1); //set the sequence number
        processTotals(invoicePaymentLine);
        updateInvoiceStatus();
        this.invoicePaymentLines.add(invoicePaymentLine);
        invoicePaymentLine.setInvoice(this);
    }

    public void removeInvoicePaymentLine(InvoicePaymentLine invoicePaymentLine){
        this.invoicePaymentLines.remove(invoicePaymentLine);
        if (invoicePaymentLine != null)
            invoicePaymentLine.setInvoice(null);

    }

    public void addInvoiceLine(InvoiceLine invoiceLine){
        if (invoiceLines == null)
            invoiceLines = new ArrayList<>();
        invoiceLines.add(invoiceLine);
        if (invoiceLine != null)
            invoiceLine.setInvoice(this);
    }

    public void removeInvoiceLine(InvoiceLine invoiceLine){
        invoiceLines.remove(invoiceLine);
        if (invoiceLine != null)
            invoiceLine.setInvoice(null);
    }

    private void processTotals(InvoicePaymentLine invoicePaymentLine){
        //payment block
        if (invoicePaymentLine.getTransactionType().equals(InvoicePaymentLine.TransactionTypes.PAYMENT.name())){
            //razorpay block
            PaymentResponse paymentResponse = invoicePaymentLine.getPaymentResponse();
            if (paymentResponse.getPaymentProvider().equals(
                    PaymentResponse.PaymentProviders.RAZORPAY.name())){
                BigDecimal amountPaid = new BigDecimal(((RazorpayResponse) paymentResponse).getAmount())
                        .divide(BigDecimal.valueOf(100)); //Divide by hundred to get value in rupees
                this.amountPaid = this.amountPaid
                        .add(amountPaid);
                this.amountPending = this.amountToBePaid
                        .subtract(amountPaid);
            }

        }
    }

    private void updateInvoiceStatus(){
        if (this.amountPending.equals(BigDecimal.ZERO))
            this.invoiceStatusCode = InvoiceStatusCodes.PAID.name();
        else if (this.amountPending.compareTo(BigDecimal.ZERO)>0)
            this.invoiceStatusCode = InvoiceStatusCodes.PAYMENT_PENDING.name();
    }

}
