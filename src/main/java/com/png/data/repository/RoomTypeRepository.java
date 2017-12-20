package com.png.data.repository;

import com.png.data.entity.RoomType;
import org.springframework.data.repository.CrudRepository;

public interface RoomTypeRepository extends CrudRepository<RoomType,Integer>,
        RoomTypeRepositoryCustom {
}
