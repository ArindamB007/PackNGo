package com.png.data.entity;

import com.png.data.dto.invoice.InvoiceLineTaxDto;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "invoice_line")
public class InvoiceLine extends BaseEntity{
    public enum InvoiceLineTypeCodes {MEALPLAN,
    ITEM, TAX,LINE_DISCOUNT,TXN_DISCOUNT,ADJUSTMENT,SUBTOTAL,TOTAL};
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_invoice_line")
    private Long idInvoiceLine;
    @Column(name = "invoice_id")
    private Long invoiceId;
    @Column(name = "sequence_no")
    private int sequenceNo;
    @Column(name = "group_sequence_no")
    private int groupSequenceNo;
    @Column(name = "invoice_line_type_code")
    private String invoiceLineTypeCode;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "amount_with_tax")
    private BigDecimal amountWithTax;
//    @Column(name = "id_invoice_line")
//    private List<InvoiceLineTaxDto> invoiceLineTaxes;

    public Long getIdInvoiceLine() {
        return idInvoiceLine;
    }

    public void setIdInvoiceLine(Long idInvoiceLine) {
        this.idInvoiceLine = idInvoiceLine;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
}
