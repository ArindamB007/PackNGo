package com.png.data.mapper;

import java.util.HashMap;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.png.data.dto.facility.FacilitiesDto;
import com.png.data.entity.Facility;

@Mapper
public interface FacilitiesMapper {
	FacilitiesMapper INSTANCE = Mappers.getMapper(FacilitiesMapper.class);
	default FacilitiesDto FacilitiesToFacilitiesDto(Set<Facility> facilities){
		if (facilities == null){
			return null;
		}
		FacilitiesDto facilitiesDto = new FacilitiesDto();
		HashMap<String,Boolean> facilitiesMap = new HashMap<>();
		facilities.forEach(facility-> {
			if (facility.getEnabledFlag()){
				facilitiesMap.put(facility.getName().toLowerCase(),true);
			}
		});
		facilitiesDto.setFacilitiesMap(facilitiesMap);
		return facilitiesDto;
	}
}
