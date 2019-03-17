package com.png.data.mapper;

import com.png.data.dto.room.RoomDto;
import com.png.data.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoomMapper {
    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    List<RoomDto> RoomsToRoomDtos(List<Room> availableRooms);

    default RoomDto RoomToRoomDto(Room availableRoom) {
        if (availableRoom == null)
            return null;
        RoomDto roomDto = new RoomDto();
        roomDto.setIdRoom(availableRoom.getIdRoom());
        roomDto.setRoomNo(availableRoom.getRoomNo());
        roomDto.setTypeName(availableRoom.getRoomType().getTypeName());
        return roomDto;
    }
}
