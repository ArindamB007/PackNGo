package com.png.data.mapper;

import com.png.data.dto.roomtypeimage.RoomTypeImageDto;
import com.png.data.entity.AvailableRoomType;
import com.png.data.entity.RoomTypeImage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Mapper
public interface RoomTypeImageMapper {
    RoomTypeImageMapper INSTANCE = Mappers.getMapper(RoomTypeImageMapper.class);
    default HashMap<String, RoomTypeImageDto> RoomTypeImagesToRoomTypeImageDtos(Set<RoomTypeImage> roomTypeImages){
        if (roomTypeImages == null)
            return null;
        HashMap<String, RoomTypeImageDto> roomTypeImagesMap = new HashMap<>();
        roomTypeImages.forEach(roomTypeImage -> {
            if (roomTypeImage.getEnabledFlag()){
                RoomTypeImageDto roomTypeImageDto = RoomTypeImageMapper.INSTANCE
                        .RoomTypeImageToRoomTypeImageDto(roomTypeImage);
                roomTypeImagesMap.put(roomTypeImageDto.getName(),roomTypeImageDto);

            }
        });
        return roomTypeImagesMap;
    }
    default RoomTypeImageDto RoomTypeImageToRoomTypeImageDto(RoomTypeImage roomTypeImage){
        if (roomTypeImage == null)
            return null;
        RoomTypeImageDto roomTypeImageDto = new RoomTypeImageDto();
        roomTypeImageDto.setName(roomTypeImage.getName());
        roomTypeImageDto.setImgPath(roomTypeImage.getImgPath());
        roomTypeImageDto.setPicture(roomTypeImage.getPicture());
        roomTypeImageDto.setShortDesc(roomTypeImage.getShortDesc());
        return roomTypeImageDto;
    }
}
