package com.png.data.repository;

import com.png.data.entity.AvailableRoomType;
import com.png.data.entity.Facility;

import java.util.List;

public interface RoomTypeRepositoryCustom {
    List<AvailableRoomType> getAvailableRoomTypeWithCount();
    List<Facility>getRoomTypeFacilitiesByIdRoomType(Integer idRoomType);
}
