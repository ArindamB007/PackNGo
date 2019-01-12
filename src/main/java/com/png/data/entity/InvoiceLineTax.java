package com.png.data.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "invoice_line_tax")
public class InvoiceLineTax extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_invoice_line_tax")
    private Long idInvoiceLineTax;
    @Column(name = "invoice_line_id")
    private Long invoiceLineId;
    @Column(name = "item_tax_code")
    private String itemTaxCode;
    @Column(name = "item_tax_description")
    private String itemTaxDescription;
    @Column(name = "item_tax_percent")
    private String itemTaxPercent;
    @Column(name = "item_tax_amount")
    private BigDecimal itemTaxAmount;
    @ManyToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private InvoiceLine invoiceLine;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InvoiceLineTax )) return false;
        return idInvoiceLineTax != null && idInvoiceLineTax.equals(((InvoiceLineTax) o).idInvoiceLineTax);
    }
    @Override
    public int hashCode() {
        return 103;
    }

    public Long getIdInvoiceLineTax() {
        return idInvoiceLineTax;
    }

    public void setIdInvoiceLineTax(Long idInvoiceLineTax) {
        this.idInvoiceLineTax = idInvoiceLineTax;
    }

    public Long getInvoiceLineId() {
        return invoiceLineId;
    }

    public void setInvoiceLineId(Long invoiceLineId) {
        this.invoiceLineId = invoiceLineId;
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

    public InvoiceLine getInvoiceLine() {
        return invoiceLine;
    }

    public void setInvoiceLine(InvoiceLine invoiceLine) {
        this.invoiceLine = invoiceLine;
    }
}
