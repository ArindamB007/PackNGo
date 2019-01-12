package com.png.data.entity;


import javax.persistence.*;

@Entity
@Table(name = "meal_plan_include")
public class MealPlanInclude {
    @Id
    @Column (name = "invoice_meal_plan_line_id")
    private Long invoiceMealPlanLineId;

    @Column (name = "include_string")
    private String includeString;

   public Long getInvoiceMealPlanLineId() {
        return invoiceMealPlanLineId;
    }

    public void setInvoiceMealPlanLineId(Long invoiceMealPlanLineId) {
        this.invoiceMealPlanLineId = invoiceMealPlanLineId;
    }

    public String getIncludeString() {
        return includeString;
    }

    public void setIncludeString(String includeString) {
        this.includeString = includeString;
    }
}
