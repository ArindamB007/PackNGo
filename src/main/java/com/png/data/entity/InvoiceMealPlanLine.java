package com.png.data.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "invoice_meal_plan_line")
@PrimaryKeyJoinColumn (name = "invoice_meal_plan_line_id")
public class InvoiceMealPlanLine extends InvoiceLineItem {
    @OneToMany(mappedBy = "invoiceMealPlanLineId")
    private List<MealPlanInclude> mealPlanIncludes;
    @Column (name = "room_type_name")
    private String roomTypeName;

    private Integer maxAdults;
    private Integer maxChilds;

    public List<MealPlanInclude> getMealPlanIncludes() {
        return mealPlanIncludes;
    }

    public void setMealPlanIncludes(List<MealPlanInclude> mealPlanIncludes) {
        this.mealPlanIncludes = mealPlanIncludes;
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
