package com.png.services;

import com.png.data.dto.property.PropertyDto;
import com.png.data.entity.Property;
import com.png.data.entity.Room;
import com.png.data.mapper.PropertyMapper;
import com.png.data.repository.PropertyRepository;
import com.png.data.repository.RoomRepository;
import com.png.util.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service

public class PropertyService {
	@Autowired
	private PropertyRepository propertyRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Transactional(readOnly = true)
	public ArrayList<PropertyDto> getAllProperties(){

		ArrayList<Property> properties = propertyRepository.findAllByEnabledFlagTrue();
		return
				PropertyMapper.INSTANCE.propertysToPropertyDtos(properties);
	}

	public List<Room> getAvailableRoomsForProperty(String checkInDate, String checkOutDate, Long idProperty) {
		List<Room> rooms = null;
		try {
			rooms = roomRepository.getAvailableRoomsForProperty(
					DateFormatter.getTimestampFromString(checkInDate),
					DateFormatter.getTimestampFromString(checkOutDate),
					idProperty,
					Sort.Direction.ASC);
		} catch (Exception e) {
			e.printStackTrace();
			//todo add logging
		}
		return rooms;
	}
	

}
