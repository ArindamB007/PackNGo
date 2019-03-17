package com.png.data.repository;

import com.png.data.entity.Room;
import org.springframework.data.domain.Sort;

import java.sql.Timestamp;
import java.util.List;

public interface RoomRepositoryCustom {
    List<Room> getAvailableRoomsForProperty(Timestamp checkInDate, Timestamp checkOutDate, Long idProperty,
                                            Sort.Direction sortOrder);
}
