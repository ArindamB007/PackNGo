package com.png.data.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.png.data.entity.Facility;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.png.data.dto.property.PropertyDto;
import com.png.data.entity.Property;

@Mapper
public interface PropertyMapper {
	PropertyMapper INSTANCE = Mappers.getMapper(PropertyMapper.class);	
	ArrayList<PropertyDto> propertysToPropertyDtos(ArrayList<Property> properties);
	default HashMap<String,Boolean> facilitySetToFacilityMap(Set<Facility> facilitiesSet){
		HashMap<String,Boolean> facilitiesMap = new HashMap<>();
		facilitiesSet.forEach(facility-> {
			if (facility.getEnabledFlag()){
				facilitiesMap.put(facility.getName(),true);
			}
		});
		return facilitiesMap;
	}
	default PropertyDto propertyToPropertyDto(Property property){
		if ( property == null ) {
			return null;
		}

		PropertyDto propertyDto = new PropertyDto();

		propertyDto.setName( property.getName() );
		propertyDto.setDescription( property.getDescription() );
		propertyDto.setShortDesc( property.getShortDesc() );
		propertyDto.setIdProperty( property.getIdProperty() );
		propertyDto.setTagLine( property.getTagLine() );
		propertyDto.setImgPath( property.getImgPath() );
		propertyDto.setCreatedTimestamp( property.getCreatedTimestamp() );
		propertyDto.setUpdatedTimestamp( property.getUpdatedTimestamp() );
		propertyDto.setDeleteFlag( property.getDeleteFlag() );
		propertyDto.setEnabledFlag( property.getEnabledFlag() );

		HashMap<String,Boolean> facilitiesMap = new HashMap<>();
		property.getFacilities().forEach(facility-> {
			if (facility.getEnabledFlag()){
				facilitiesMap.put(facility.getName().toLowerCase(),true);
			}
		});
		propertyDto.setFacilities(facilitiesMap);
		return propertyDto;
	}

	default Property propertyDTOToProperty(PropertyDto propertyDto){
		Property property = new Property();

		property.setName( propertyDto.getName() );
		property.setDescription( propertyDto.getDescription() );
		property.setShortDesc( propertyDto.getShortDesc() );
		property.setIdProperty( propertyDto.getIdProperty() );
		property.setTagLine( propertyDto.getTagLine() );
		property.setImgPath( propertyDto.getImgPath() );
		property.setCreatedTimestamp( propertyDto.getCreatedTimestamp() );
		property.setUpdatedTimestamp( propertyDto.getUpdatedTimestamp() );
		property.setDeleteFlag( propertyDto.getDeleteFlag() );
		property.setEnabledFlag( propertyDto.getEnabledFlag() );
		Set<Facility> facilitySet = new HashSet<Facility>();
		propertyDto.getFacilities().forEach((k,v)->{
			Facility facility = new Facility();
			facility.setName(k);
			facilitySet.add(facility);
		});
		return property;
	}
}
