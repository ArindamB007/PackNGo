package com.png.data.repository;

import com.png.data.entity.AvailableRoomType;

import java.util.List;

public interface RoomTypeRepositoryCustom {
    List<AvailableRoomType> getAvaiableRoomTypeWithCount();
}
