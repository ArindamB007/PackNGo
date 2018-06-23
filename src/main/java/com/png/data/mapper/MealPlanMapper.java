package com.png.data.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.png.data.dto.availableroomtype.MealPlanDto;
import com.png.data.entity.MealPlan;

@Mapper
public interface MealPlanMapper {
	MealPlanMapper INSTANCE = Mappers.getMapper(MealPlanMapper.class);
	List<MealPlanDto> MealPlansToMealPlanDtos(List<MealPlan> mealPlans);
	default MealPlanDto MealPlanToMealPlanDto(MealPlan mealPlan) {
		if (mealPlan == null) {
			return null;
		}
	MealPlanDto mealPlanDto = new MealPlanDto();
	mealPlanDto.setIdMealPlan(mealPlan.getIdMealPlan());
	mealPlanDto.setMealPlanCode(mealPlan.getMealPlanCode());
	mealPlanDto.setDescription(mealPlan.getDescription());
	mealPlanDto.setItem(ItemMapper.INSTANCE.ItemToItemDto(mealPlan.getItem()));
	mealPlanDto.setCreatedTimestamp(mealPlan.getCreatedTimestamp());
	mealPlanDto.setUpdatedTimestamp(mealPlan.getUpdatedTimestamp());
	mealPlanDto.setDeletedFlag(mealPlan.getDeletedFlag());
	mealPlanDto.setEnabledFlag(mealPlan.getEnabledFlag());
	return mealPlanDto;
	}

}
