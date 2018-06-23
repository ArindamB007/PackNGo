package com.png.data.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.png.data.dto.item.ItemTypeDto;
import com.png.data.entity.ItemType;

@Mapper
public interface ItemTypeMapper {
	ItemTypeMapper INSTANCE = Mappers.getMapper(ItemTypeMapper.class);
	List<ItemTypeDto> ItemTypesToItemTypeDtos(List<ItemType> itemTypes);
	default ItemTypeDto ItemTypeToItemTypeDto(ItemType itemType) {
		if (itemType == null) {
			return null;
		}
		ItemTypeDto itemTypeDto = new ItemTypeDto();
		itemTypeDto.setIdItemType(itemType.getIdItemType());
		itemTypeDto.setItemTypeCode(itemType.getItemTypeCode());
		itemTypeDto.setDescription(itemType.getDescription());
		return itemTypeDto;
	}
	
}
