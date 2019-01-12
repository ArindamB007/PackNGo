package com.png.data.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "invoice_tax")
public class InvoiceTax {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_invoice_tax")
    private Long idInvoiceTax;
    @Column(name="item_tax_code")
    private String itemTaxCode;
    @Column(name="item_tax_description")
    private String itemTaxDescription;
    @Column(name="item_tax_percent")
    private String itemTaxPercent;
    @Column(name="item_tax_amount")
    private BigDecimal itemTaxAmount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InvoiceTax )) return false;
        return idInvoiceTax != null && idInvoiceTax.equals(((InvoiceTax) o).idInvoiceTax);
    }
    @Override
    public int hashCode() {
        return 100;
    }

    public Long getIdInvoiceTax() {
        return idInvoiceTax;
    }

    public void setIdInvoiceTax(Long idInvoiceTax) {
        this.idInvoiceTax = idInvoiceTax;
    }

    public String getItemTaxCode() {
        return itemTaxCode;
    }

    public void setItemTaxCode(String itemTaxCode) {
        this.itemTaxCode = itemTaxCode;
    }

    public String getItemTaxDescription() {
        return itemTaxDescription;
    }

    public void setItemTaxDescription(String itemTaxDescription) {
        this.itemTaxDescription = itemTaxDescription;
    }

    public String getItemTaxPercent() {
        return itemTaxPercent;
    }

    public void setItemTaxPercent(String itemTaxPercent) {
        this.itemTaxPercent = itemTaxPercent;
    }

    public BigDecimal getItemTaxAmount() {
        return itemTaxAmount;
    }

    public void setItemTaxAmount(BigDecimal itemTaxAmount) {
        this.itemTaxAmount = itemTaxAmount;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

}
