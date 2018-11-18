package com.png.data.dto.invoice;

import java.util.List;

public class InvoiceMealPlanLineDto extends InvoiceLineItemDto {
    private List<String> includes;

    public List<String> getIncludes() {
        return includes;
    }

    public void setIncludes(List<String> includes) {
        this.includes = includes;
    }
}
