package com.png.data.repository;

import com.png.data.entity.AvailableRoomType;
import com.png.data.entity.Facility;

import java.sql.Timestamp;
import java.util.List;

public interface RoomTypeRepositoryCustom {
    List<AvailableRoomType> getAvailableRoomTypeWithCount(Timestamp checkInTimestamp, Timestamp checkOutTimestamp,
                                                          Long idProperty);
    List<Facility>getRoomTypeFacilitiesByIdRoomType(Long idRoomType);
}
