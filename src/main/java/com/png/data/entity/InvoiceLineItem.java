package com.png.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "invoice_line_item")
@PrimaryKeyJoinColumn(name="id_invoice_line_item")
public class InvoiceLineItem extends InvoiceLine {
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "quantity")
    private Integer quantity;
    @Column (name = "no_of_nights")
    private Integer noOfNights;
    @Column(name = "item_type")
    private String itemType;
    @Column(name = "cancel_charge")
    private BigDecimal cancelCharge;
    @Column(name = "cancel_charge_with_tax")
    private BigDecimal cancelChargeWithTax;

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

    public Integer getNoOfNights() {
        return noOfNights;
    }

    public void setNoOfNights(Integer noOfNights) {
        this.noOfNights = noOfNights;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public InvoiceLineItem() {
        super();
        cancelCharge =
                cancelChargeWithTax = BigDecimal.ZERO;
    }

    public BigDecimal getCancelCharge() {
        return cancelCharge;
    }

    public void setCancelCharge(BigDecimal cancelCharge) {
        this.cancelCharge = cancelCharge;
    }

    public BigDecimal getCancelChargeWithTax() {
        return cancelChargeWithTax;
    }

    public void setCancelChargeWithTax(BigDecimal cancelChargeWithTax) {
        this.cancelChargeWithTax = cancelChargeWithTax;
    }

    public void calculateAmountWithTaxForCancellation() {
        //this.amountWithTax = BigDecimal.ZERO;
        this.cancelChargeWithTax = BigDecimal.ZERO;
        //List<InvoiceLineTax> invLineTaxes = new ArrayList<>();
        if (super.getInvoiceLineTaxes().size() > 0) {
            super.getInvoiceLineTaxes().forEach(invoiceLineTax -> {
               /* InvoiceLineTax invLineTax = new InvoiceLineTax();
                invLineTax.setIdInvoiceLineTax(invoiceLineTax.getIdInvoiceLineTax());
                invLineTax.setItemTaxPercent(invoiceLineTax.getItemTaxPercent());
                invLineTax.setItemTaxDescription(invoiceLineTax.getItemTaxDescription());
                invLineTax.setItemTaxCode(invoiceLineTax.getItemTaxCode());
                if (getInvoiceLineTypeCode().equals(InvoiceLineTypeCodes.ITEM_REFUND.name())){
                    invoiceLineTax.setItemTaxAmount(this.cancelCharge.multiply(new BigDecimal(invoiceLineTax.getItemTaxPercent()))
                            .divide(new BigDecimal(100)));*/
                this.cancelChargeWithTax = this.cancelChargeWithTax.add(invoiceLineTax.getItemTaxAmount());
                //invLineTaxes.add(invLineTax);
            });
            //this.invoiceLineTaxes = invLineTaxes;
            if (this.cancelCharge != null)
                this.cancelChargeWithTax = this.cancelChargeWithTax.add(this.cancelCharge);
        }
    }
}
