package com.png.data.repository;

import com.png.data.entity.RoomTypeImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomTypeImageRepository extends JpaRepository<RoomTypeImage,Long> {
    List<RoomTypeImage> findByRoomTypeId(Integer roomTypeId);
}
