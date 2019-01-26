package com.png.data.entity;


import javax.persistence.*;

@Entity
@Table(name = "meal_plan_include")
public class MealPlanInclude {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_meal_plan_include")
    private Long idMealPlanInclude;

    @Column (name = "include_string")
    private String includeString;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private MealPlan mealPlan;

    public Long getIdMealPlanInclude() {
        return idMealPlanInclude;
    }

    public void setIdMealPlanInclude(Long idMealPlanInclude) {
        this.idMealPlanInclude = idMealPlanInclude;
    }

    public MealPlan getMealPlan() {
        return mealPlan;
    }

    public void setMealPlan(MealPlan mealPlan) {
        this.mealPlan = mealPlan;
    }

    public String getIncludeString() {
        return includeString;
    }

    public void setIncludeString(String includeString) {
        this.includeString = includeString;
    }
}
