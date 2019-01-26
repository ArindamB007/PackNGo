package com.png.data.entity;

import com.png.data.dto.invoice.InvoiceLineTaxDto;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "invoice_line")
@Inheritance(strategy =InheritanceType.JOINED)
public class InvoiceLine extends BaseEntity{
    public enum InvoiceLineTypeCodes {MEALPLAN,EXTRA_PERSON,
        ITEM, CANCEL_LINE, LINE_DISCOUNT, TXN_DISCOUNT, ADJUSTMENT, SUBTOTAL, TOTAL
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_invoice_line")
    private Long idInvoiceLine;
    @Column(name = "sequence_no")
    private int sequenceNo;
    @Column(name = "group_sequence_no")
    private int groupSequenceNo;
    //stores the invoice line types
    @Column(name = "invoice_line_type_code")
    private String invoiceLineTypeCode;
    @Column(name = "description")
    private String description;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "amount_with_tax")
    private BigDecimal amountWithTax;
    @OneToMany (mappedBy = "invoiceLine", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceLineTax> invoiceLineTaxes;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InvoiceLine )) return false;
        return idInvoiceLine != null && idInvoiceLine.equals(((InvoiceLine) o).idInvoiceLine);
    }
    @Override
    public int hashCode() {
        return 101;
    }

    public Long getIdInvoiceLine() {
        return idInvoiceLine;
    }

    public void setIdInvoiceLine(Long idInvoiceLine) {
        this.idInvoiceLine = idInvoiceLine;
    }

    public int getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(int sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    public int getGroupSequenceNo() {
        return groupSequenceNo;
    }

    public void setGroupSequenceNo(int groupSequenceNo) {
        this.groupSequenceNo = groupSequenceNo;
    }

    public String getInvoiceLineTypeCode() {
        return invoiceLineTypeCode;
    }

    public void setInvoiceLineTypeCode(String invoiceLineTypeCode) {
        this.invoiceLineTypeCode = invoiceLineTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmountWithTax() {
        return amountWithTax;
    }

    public void setAmountWithTax(BigDecimal amountWithTax) {
        this.amountWithTax = amountWithTax;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public void addInvoiceLineTax(InvoiceLineTax invoiceLineTax){
        if (invoiceLineTax == null)
            return;
        if (this.invoiceLineTaxes == null)
            this.invoiceLineTaxes = new ArrayList<>();
        this.invoiceLineTaxes.add(invoiceLineTax);
        invoiceLineTax.setInvoiceLine(this);
    }

    public void removeInvoiceLineTax(InvoiceLineTax invoiceLineTax){
        this.invoiceLineTaxes.remove(invoiceLineTax);
        if (invoiceLineTax!=null)
            invoiceLineTax.setInvoiceLine(null);
    }

    public List<InvoiceLineTax> getInvoiceLineTaxes() {
        return invoiceLineTaxes;
    }

    public void setInvoiceLineTaxes(List<InvoiceLineTax> invoiceLineTaxes) {
        this.invoiceLineTaxes = invoiceLineTaxes;
    }

    public void calculateAmountWithTax() {
        this.amountWithTax = BigDecimal.ZERO;
        List<InvoiceLineTax> invLineTaxes = new ArrayList<>();
        if (this.invoiceLineTaxes.size() > 0) {
            this.invoiceLineTaxes.forEach(invoiceLineTax -> {
                InvoiceLineTax invLineTax = new InvoiceLineTax();
                invLineTax.setIdInvoiceLineTax(invoiceLineTax.getIdInvoiceLineTax());
                invLineTax.setItemTaxPercent(invoiceLineTax.getItemTaxPercent());
                invLineTax.setItemTaxDescription(invoiceLineTax.getItemTaxDescription());
                invLineTax.setItemTaxCode(invoiceLineTax.getItemTaxCode());
                invLineTax.setItemTaxAmount(this.amount.multiply(new BigDecimal(invoiceLineTax.getItemTaxPercent()))
                        .divide(new BigDecimal(100)));
                this.amountWithTax = this.amountWithTax.add(invLineTax.getItemTaxAmount());
                invLineTaxes.add(invLineTax);
            });
            this.invoiceLineTaxes = invLineTaxes;
            this.amountWithTax = this.amountWithTax.add(this.amount);
        }
    }
}
