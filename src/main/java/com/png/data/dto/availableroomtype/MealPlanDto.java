package com.png.data.dto.availableroomtype;

import com.png.data.dto.BaseEntityDto;
import com.png.data.dto.item.ItemDto;

public class MealPlanDto extends BaseEntityDto{
	private Long idMealPlan;
	private String mealPlanCode;
	private String description;
	private ItemDto mealPlanItem;
	private ItemDto adultExtraBedItem;
	private ItemDto childExtraBedItem;
	
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
	public ItemDto getMealPlanItem() {
		return mealPlanItem;
	}
	public void setMealPlanItem(ItemDto mealPlanItem) {
		this.mealPlanItem = mealPlanItem;
	}
	public ItemDto getAdultExtraBedItem() {
		return adultExtraBedItem;
	}
	public void setAdultExtraBedItem(ItemDto adultExtraBedItem) {
		this.adultExtraBedItem = adultExtraBedItem;
	}
	public ItemDto getChildExtraBedItem() {
		return childExtraBedItem;
	}
	public void setChildExtraBedItem(ItemDto childExtraBedItem) {
		this.childExtraBedItem = childExtraBedItem;
	}
}
