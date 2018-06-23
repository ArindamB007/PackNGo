package com.png.data.dto.item;

import com.png.data.dto.BaseEntityDto;

public class ItemTypeDto extends BaseEntityDto {
	private Long idItemType;
	private String itemTypeCode;
	private String description;
	public Long getIdItemType() {
		return idItemType;
	}
	public void setIdItemType(Long idItemType) {
		this.idItemType = idItemType;
	}
	public String getItemTypeCode() {
		return itemTypeCode;
	}
	public void setItemTypeCode(String itemTypeCode) {
		this.itemTypeCode = itemTypeCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
