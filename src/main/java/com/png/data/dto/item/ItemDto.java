package com.png.data.dto.item;

import com.png.data.dto.BaseEntityDto;

public class ItemDto extends BaseEntityDto {
	private Long idItem;
	private String description;
	private ItemTypeDto itemType;
	private ItemPriceDto itemPrice;
	private Integer quantity;
	private Integer noOfNights;

	public ItemDto(){
	    super();
	    this.quantity = 0;
    }

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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getNoOfNights() {
		return noOfNights;
	}

	public void setNoOfNights(Integer noOfNights) {
		this.noOfNights = noOfNights;
	}
}
