package com.png.data.dto.availableroomtype;

import com.png.data.dto.BaseEntityDto;
import com.png.data.dto.item.ItemDto;

public class MealPlanDto extends BaseEntityDto{
	private Long idMealPlan;
	private String mealPlanCode;
	private String description;
	private ItemDto item;
	public Long getIdMealPlan() {
		return idMealPlan;
	}
	public void setIdMealPlan(Long idMealPlan) {
		this.idMealPlan = idMealPlan;
	}
	public String getMealPlanCode() {
		return mealPlanCode;
	}
	public void setMealPlanCode(String mealPlanCode) {
		this.mealPlanCode = mealPlanCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ItemDto getItem() {
		return item;
	}
	public void setItem(ItemDto item) {
		this.item = item;
	}
	
}
