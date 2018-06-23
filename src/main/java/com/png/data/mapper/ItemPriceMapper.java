package com.png.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.png.data.dto.item.ItemPriceDto;
import com.png.data.entity.ItemPrice;

@Mapper
public interface ItemPriceMapper {
	ItemPriceMapper INSTANCE = Mappers.getMapper(ItemPriceMapper.class);
	default ItemPriceDto ItemPriceToItemPriceDto(ItemPrice itemPrice) {
		if (itemPrice == null) {
			return null;
		}
		ItemPriceDto itemPriceDto = new ItemPriceDto();
		itemPriceDto.setIdItemPrice(itemPrice.getIdItemPrice());
		itemPriceDto.setBasePrice(itemPrice.getBasePrice());
		return itemPriceDto;
	}

}
