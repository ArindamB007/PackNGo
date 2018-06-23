package com.png.data.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.png.data.dto.item.ItemDto;
import com.png.data.entity.Item;

@Mapper
public interface ItemMapper {
	ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);
	List<ItemDto> ItemsToItemDtos(List<Item> items);
	default ItemDto ItemToItemDto(Item item) {
		if (item == null) {
			return null;
		}
		ItemDto itemDto = new ItemDto();
		itemDto.setIdItem(item.getIdItem());
		itemDto.setItemType(ItemTypeMapper.INSTANCE.ItemTypeToItemTypeDto(item.getItemType()));
		itemDto.setItemPrice(ItemPriceMapper.INSTANCE.ItemPriceToItemPriceDto(item.getItemPrice()));
		itemDto.setDescription(item.getDescription());
		return itemDto;
	}
}
