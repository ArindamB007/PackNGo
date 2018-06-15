package com.png.data.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="item_price")
public class ItemPrice extends BaseEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_item_price")
    private Long idItemPrice;
	
	@Column (name = "base_price")
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
