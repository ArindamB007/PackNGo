package com.png.data.entity;

import com.png.data.dto.invoice.InvoiceLineTaxDto;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "invoice_line")
@Inheritance(strategy =InheritanceType.JOINED)
public class InvoiceLine extends BaseEntity{
    public enum InvoiceLineTypeCodes {MEALPLAN,EXTRA_PERSON,
        ITEM
    }

    public enum InvoiceLineStatusCodes {SALE, REFUND}
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
    @Column(name = "taxable_amount")
    private BigDecimal taxableAmount;
    @Column(name = "amount_with_tax")
    private BigDecimal amountWithTax;
    @Column(name = "discount_amount")
    private BigDecimal discountAmount;
    @Column(name = "invoice_line_status_code")
    private String invoiceLineStatusCode;
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

    public InvoiceLine() {
        amount =
                amountWithTax =
                        discountAmount =
                                taxableAmount =
                                        BigDecimal.ZERO;
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
                if (getInvoiceLineStatusCode().equals(InvoiceLineStatusCodes.SALE.name())) {
                    this.taxableAmount = this.amount.subtract(this.discountAmount);
                    invLineTax.setItemTaxAmount(this.taxableAmount
                            .multiply(new BigDecimal(invoiceLineTax.getItemTaxPercent()))
                            .divide(new BigDecimal(100)));
                    this.amountWithTax = this.amountWithTax.add(invLineTax.getItemTaxAmount());
                }
                invLineTaxes.add(invLineTax);
            });
            this.invoiceLineTaxes = invLineTaxes;
            this.amountWithTax = this.amountWithTax.add(this.taxableAmount);
        }
    }

    public String getInvoiceLineStatusCode() {
        return invoiceLineStatusCode;
    }

    public void setInvoiceLineStatusCode(String invoiceLineStatusCode) {
        this.invoiceLineStatusCode = invoiceLineStatusCode;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getTaxableAmount() {
        return taxableAmount;
    }

    public void setTaxableAmount(BigDecimal taxableAmount) {
        this.taxableAmount = taxableAmount;
    }

    public void applyDiscountCoupon(DiscountCoupon discountCoupon) {
        this.discountAmount = this.amount
                .multiply(BigDecimal.valueOf(discountCoupon.getDiscountPercent()))
                .divide(BigDecimal.valueOf(100));
    }

    public void removeDiscountCoupon(DiscountCoupon discountCoupon) {
        this.discountAmount = this.discountAmount.subtract(this.amount
                .multiply(BigDecimal.valueOf(discountCoupon.getDiscountPercent()))
                .divide(BigDecimal.valueOf(100)));
    }

}
