package com.png.data.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "invoice_meal_plan_line")
@PrimaryKeyJoinColumn (name = "invoice_meal_plan_line_id")
public class InvoiceMealPlanLine extends InvoiceLineItem {
    @Column (name = "room_type_name")
    private String roomTypeName;
    @Column(name = "max_adults")
    private Integer maxAdults;
    @Column(name = "max_childs")
    private Integer maxChilds;

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
