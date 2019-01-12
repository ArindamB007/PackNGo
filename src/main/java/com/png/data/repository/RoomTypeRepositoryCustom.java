package com.png.data.repository;

import com.png.data.entity.AvailableRoomType;
import com.png.data.entity.Facility;
import com.png.data.entity.Room;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

public interface RoomTypeRepositoryCustom {
    List<AvailableRoomType> getAvailableRoomTypeWithCount(Timestamp checkInTimestamp, Timestamp checkOutTimestamp,
                                                          Long idProperty);
    List<Facility>getRoomTypeFacilitiesByIdRoomType(Long idRoomType);
    List<Room> getRoomsToBeBooked(Long idProperty, String roomTypeName, Integer roomQuantity,
                                         Timestamp checkInTimestamp, Timestamp checkOutTimestamp);

}
