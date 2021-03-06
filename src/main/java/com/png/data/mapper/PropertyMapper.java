package com.png.data.mapper;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.png.data.dto.property.PropertyDto;
import com.png.data.entity.Facility;
import com.png.data.entity.Property;
import com.png.data.entity.PropertyImage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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
		propertyDto.setLocation(property.getLocation());
		propertyDto.setShortDesc( property.getShortDesc() );
		propertyDto.setIdProperty( property.getIdProperty() );
		propertyDto.setTagLine( property.getTagLine() );
		propertyDto.setCreatedTimestamp( property.getCreatedTimestamp() );
		propertyDto.setUpdatedTimestamp( property.getUpdatedTimestamp() );
		propertyDto.setDeleteFlag( property.getDeletedFlag() );
		propertyDto.setEnabledFlag( property.getEnabledFlag() );
		propertyDto.setCancellationRules(CancellationRuleMapper.INSTANCE
				.cancellationRulesToCancellationRuleDtos(property.getCancellationRules()));

		HashMap<String,Boolean> facilitiesMap = new HashMap<>();
		property.getFacilities().forEach(facility-> {
			if (facility.getEnabledFlag()){
				facilitiesMap.put(facility.getName().toLowerCase(),true);
			}
		});
		propertyDto.setFacilities(facilitiesMap);

		HashMap<String,Object> imagesMap = new HashMap<>();
		property.getPropertyImages().forEach(image-> {
			if (image.getEnabledFlag()){
				imagesMap.put(image.getName().toLowerCase(),image);
			}
		});
		propertyDto.setImages(imagesMap);

		return propertyDto;
	}

/*	default Property propertyDTOToProperty(PropertyDto propertyDto) {
		Property property = new Property();

		property.setName( propertyDto.getName() );
		property.setDescription( propertyDto.getDescription() );
		property.setLocation(propertyDto.getLocation());
		property.setShortDesc( propertyDto.getShortDesc() );
		property.setIdProperty( propertyDto.getIdProperty() );
		property.setTagLine( propertyDto.getTagLine() );
		property.setCreatedTimestamp( propertyDto.getCreatedTimestamp() );
		property.setUpdatedTimestamp( propertyDto.getUpdatedTimestamp() );
		property.setDeletedFlag( propertyDto.getDeleteFlag() );
		property.setEnabledFlag( propertyDto.getEnabledFlag() );

		Set<Facility> facilitySet = new HashSet<Facility>();
		propertyDto.getFacilities().forEach((k,v)->{
			Facility facility = new Facility();
			facility.setName(k);
			facility.set
			facilitySet.add(facility);
		});
		property.setFacilities(facilitySet);
		Set<PropertyImage> imagesSet = new HashSet<PropertyImage>();
		ObjectMapper propertyImageMapper= new ObjectMapper();
        propertyDto.getImages().forEach((k, v) -> {
            try {
                PropertyImage image = propertyImageMapper.readValue(v.toString(), PropertyImage.class);
                //image.setName set the image
                imagesSet.add(image);
            } catch(Exception e){

            }
        });
        property.setPropertyImages(imagesSet);
		return property;
	}*/
}
