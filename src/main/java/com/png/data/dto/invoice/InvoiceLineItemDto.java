package com.png.data.dto.invoice;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.png.util.CurrecySerializer;

import java.math.BigDecimal;

public class InvoiceLineItemDto extends InvoiceLineDto{
    @JsonSerialize(using = CurrecySerializer.class)
    private BigDecimal price;
    private Integer quantity;
    private Integer noOfNights;
    private String itemType;

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
}
