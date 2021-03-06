package com.png.data.entity;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Set;

@Entity
@Table(name="item_type")
public class ItemType extends BaseEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_item_type")
    private Long idItemType;
	
	/*public enum ItemTypeCodes {REGULARITEM , MEALPLANITEM,
		EXTRABEDADULT, EXTRABEDCHILD, TRANSPORTITEM,
		FOCITEM, COUPONNITEM}*/
	
	public enum ItemTypeCodes {MEALPLANITEM,
        EXTRABEDADULT, EXTRABEDCHILD, CANCELLATION_ITEM
    }
	
	@Column (name="item_type_code", nullable = false)
    @NotEmpty
    private String itemTypeCode;
	
	@Column (name="description", nullable = false)
    @NotEmpty
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
