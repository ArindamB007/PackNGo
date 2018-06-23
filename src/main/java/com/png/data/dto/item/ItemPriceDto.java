package com.png.data.dto.item;

import java.math.BigDecimal;

import com.png.data.dto.BaseEntityDto;

public class ItemPriceDto extends BaseEntityDto{
	private Long idItemPrice;
	private BigDecimal basePrice;
	
	public Long getIdItemPrice() {
		return idItemPrice;
	}
	public void setIdItemPrice(Long idItemPrice) {
		this.idItemPrice = idItemPrice;
	}
	public BigDecimal getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}
	
}
