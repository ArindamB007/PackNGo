package com.png.data.dto.item;

import com.png.data.dto.BaseEntityDto;

public class ItemDto extends BaseEntityDto {
	private Long idItem;
	private String description;
	private ItemTypeDto itemType;
	private ItemPriceDto itemPrice;
	
	public Long getIdItem() {
		return idItem;
	}
	public void setIdItem(Long idItem) {
		this.idItem = idItem;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ItemTypeDto getItemType() {
		return itemType;
	}
	public void setItemType(ItemTypeDto itemType) {
		this.itemType = itemType;
	}
	public ItemPriceDto getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(ItemPriceDto itemPrice) {
		this.itemPrice = itemPrice;
	}

}
