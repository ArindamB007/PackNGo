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
	List<Item> ItemDtosToItem(List<ItemDto> itemDtos);
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
	default Item ItemDtoToItem(ItemDto itemDto) {
		if (itemDto == null) {
			return null;
		}
		Item item = new Item();
		item.setIdItem(itemDto.getIdItem());
		item.setItemType(ItemTypeMapper.INSTANCE.ItemTypeDtoToItemType(itemDto.getItemType()));
		item.setItemPrice(ItemPriceMapper.INSTANCE.ItemPriceDtoToItemPrice(itemDto.getItemPrice()));
		item.setDescription(itemDto.getDescription());
		return item;
	}
}
