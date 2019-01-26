package com.png.data.dto.invoice;

import java.util.List;

public class InvoiceMealPlanLineDto extends InvoiceLineItemDto {
    private List<String> includes;
    private String roomTypeName;
    private Integer maxAdults;
    private Integer maxChilds;

    public List<String> getIncludes() {
        return includes;
    }

    public void setIncludes(List<String> includes) {
        this.includes = includes;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public Integer getMaxAdults() {
        return maxAdults;
    }

    public void setMaxAdults(Integer maxAdults) {
        this.maxAdults = maxAdults;
    }

    public Integer getMaxChilds() {
        return maxChilds;
    }

    public void setMaxChilds(Integer maxChilds) {
        this.maxChilds = maxChilds;
    }
}
