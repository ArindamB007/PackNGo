package com.png.data.mapper;

import java.util.ArrayList;
import java.util.List;

import com.png.data.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.png.data.dto.availableroomtype.MealPlanDto;
import com.png.data.entity.MealPlan;
import com.png.data.entity.ItemType;
import org.springframework.beans.factory.annotation.Autowired;

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
		mealPlan.getItems().forEach(item->{
			switch(ItemType.ItemTypeCodes.valueOf(item.getItemType().getItemTypeCode())) {
			case MEALPLANITEM:
				mealPlanDto.setMealPlanItem(ItemMapper.INSTANCE.ItemToItemDto(item));
				break;
			case EXTRABEDADULT:
				mealPlanDto.setAdultExtraBedItem(ItemMapper.INSTANCE.ItemToItemDto(item));
				break;
			case EXTRABEDCHILD:
				mealPlanDto.setChildExtraBedItem(ItemMapper.INSTANCE.ItemToItemDto(item));
				break;
			}
		});
		mealPlanDto.setCreatedTimestamp(mealPlan.getCreatedTimestamp());
		mealPlanDto.setUpdatedTimestamp(mealPlan.getUpdatedTimestamp());
		mealPlanDto.setDeletedFlag(mealPlan.getDeletedFlag());
		mealPlanDto.setEnabledFlag(mealPlan.getEnabledFlag());
		return mealPlanDto;
	}
	default MealPlan MealPlanDtoToMealPlan(MealPlanDto mealPlanDto) {
		if (mealPlanDto == null) {
			return null;
		}
		MealPlan mealPlan = new MealPlan();
		mealPlan.setIdMealPlan(mealPlanDto.getIdMealPlan());
		mealPlan.setMealPlanCode(mealPlanDto.getMealPlanCode());
		mealPlan.setDescription(mealPlanDto.getDescription());
		List<Item> items = new ArrayList<>();
		Item item = ItemMapper.INSTANCE.ItemDtoToItem(mealPlanDto.getMealPlanItem());
		items.add(item);
		item = ItemMapper.INSTANCE.ItemDtoToItem(mealPlanDto.getAdultExtraBedItem());
		items.add(item);
		item = ItemMapper.INSTANCE.ItemDtoToItem(mealPlanDto.getChildExtraBedItem());
		items.add(item);
		mealPlan.setItems(items);
		mealPlan.setCreatedTimestamp(mealPlanDto.getCreatedTimestamp());
		mealPlan.setUpdatedTimestamp(mealPlanDto.getUpdatedTimestamp());
		mealPlan.setDeletedFlag(mealPlanDto.getDeletedFlag());
		mealPlan.setEnabledFlag(mealPlanDto.getEnabledFlag());
		return mealPlan;
	}
}
