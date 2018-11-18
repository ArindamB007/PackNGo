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
}