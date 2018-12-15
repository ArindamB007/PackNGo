package com.png.data.mapper;

import com.png.data.dto.item.ItemDto;
import com.png.data.dto.user.TravellerDto;
import com.png.data.entity.Item;
import com.png.data.entity.Traveller;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TravellerMapper {
    TravellerMapper INSTANCE = Mappers.getMapper(TravellerMapper.class);
    List<TravellerDto> TravellersToTravellerDtos(List<Traveller> travellers);
    List<Traveller> TravellerDtosToTravellers(List<TravellerDto> travellers);
}
