package com.png.data.entity;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.math.BigDecimal;
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

    @Column(name = "invoice_total_tax")
    private BigDecimal invoiceTotalTax;

    @Column(name = "invoice_status_code")
    private String invoiceStatusCode;

    @OneToMany (mappedBy = "invoiceId")
    private List<InvoiceTax> appliedTaxes;

    @OneToMany (mappedBy = "invoiceId")
    private List<InvoiceLine> invoiceLines;

    @Column(name="traveller_first_name")
    private String travellerFirstName;

    @Column(name="traveller_middle_name")
    private String travellerMiddleName;

    @Column(name="traveller_last_name")
    private String travellerLastName;

    @Column(name="traveller_email")
    @Email
    @NotEmpty
    private String travellerEmail;

    @Column (name="traveller_mobile")
    @NotEmpty
    private String travellerMobile;

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
}
