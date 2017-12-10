package com.png.services;

import com.png.data.dto.property.PropertyDto;
import com.png.data.entity.Property;
import com.png.data.mapper.PropertyMapper;
import com.png.data.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional(readOnly = true)
public class PropertyService {
	@Autowired
	private PropertyRepository propertyRepository;
	
	public ArrayList<PropertyDto> getAllProperties(){
		ArrayList<Property> properties = propertyRepository.findAllByEnabledFlagTrue();
		ArrayList<PropertyDto> propertyDtos =
				PropertyMapper.INSTANCE.propertysToPropertyDtos(properties);
		return propertyDtos;
	}
	

}
