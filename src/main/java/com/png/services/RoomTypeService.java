package com.png.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.png.data.dto.availableroomtype.AvailableRoomTypeDto;
import com.png.data.entity.AvailableRoomType;
import com.png.data.mapper.AvailableRoomTypeMapper;
import com.png.data.repository.RoomTypeRepository;

@Service
@Transactional
public class RoomTypeService {
	@Autowired
	private RoomTypeRepository roomTypeRepository;
	public List<AvailableRoomTypeDto> getAvailableRoomTypes(Timestamp checkInTimestamp, Timestamp checkOutTimestamp){
		List<AvailableRoomType> availableRoomTypeList =
				roomTypeRepository.getAvailableRoomTypeWithCount(checkInTimestamp,checkOutTimestamp);
		System.out.println(availableRoomTypeList.toString());

		List<AvailableRoomTypeDto> availableRoomTypeDtos =
				AvailableRoomTypeMapper.INSTANCE.AvailableRoomTypesToAvailableRoomTypeDtos(availableRoomTypeList);
		return availableRoomTypeDtos;
	}
}
