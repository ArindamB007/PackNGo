package com.png.data.dto.invoice;

import java.math.BigDecimal;

public class InvoiceLineItemDto extends InvoiceLineDto{
    private BigDecimal price;
    private Integer quantity;

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

}
