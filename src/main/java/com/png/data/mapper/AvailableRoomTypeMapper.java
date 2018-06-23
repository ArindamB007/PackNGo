package com.png.data.mapper;

import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.png.data.dto.availableroomtype.AvailableRoomTypeDto;
import com.png.data.entity.AvailableRoomType;

@Mapper
public interface AvailableRoomTypeMapper {
	AvailableRoomTypeMapper INSTANCE = Mappers.getMapper(AvailableRoomTypeMapper.class);
	List<AvailableRoomTypeDto> AvailableRoomTypesToAvailableRoomTypeDtos(List<AvailableRoomType> availableRoomTypes);
	default AvailableRoomTypeDto AvailableRoomTypeToAvailableRoomTypeDto (AvailableRoomType availableRoomType){
		if ( availableRoomType == null ) {
			return null;
		}
		AvailableRoomTypeDto availableRoomTypeDto = new AvailableRoomTypeDto();
		availableRoomTypeDto.setIdRoomType(availableRoomType.getIdRoomType());
		availableRoomTypeDto.setTypeName(availableRoomType.getTypeName());
		availableRoomTypeDto.setMaxAdultOccupancy(availableRoomType.getMaxAdultOccupancy());
		availableRoomTypeDto.setMaxChildOccupancy(availableRoomType.getMaxChildOccupancy());
		availableRoomTypeDto.setDiscount(availableRoomType.getDiscount());
		availableRoomTypeDto.setDescription(availableRoomType.getDescription());
		availableRoomTypeDto.setAvailableCount(availableRoomType.getAvailableCount());
		availableRoomTypeDto.setBasePrice(availableRoomType.getBasePrice());
		availableRoomTypeDto.setRoomTypeImages(RoomTypeImageMapper.INSTANCE.RoomTypeImagesToRoomTypeImageDtos(
				availableRoomType.getRoomTypeImages()));
		availableRoomTypeDto.setFacilities(
				FacilitiesMapper.INSTANCE.FacilitiesToFacilitiesDto(
						availableRoomType.getFacilities()).getFacilitiesMap());
		availableRoomTypeDto.setMealPlans(MealPlanMapper.INSTANCE.MealPlansToMealPlanDtos(
				availableRoomType.getMealPlans()));
		return availableRoomTypeDto;
	}

}
