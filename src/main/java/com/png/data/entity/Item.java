package com.png.data.entity;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

@Entity
@Table(name="item")
public class Item extends BaseEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_item")
    private Long idItem;
	
	@ManyToOne (fetch = FetchType.LAZY, optional = false)
    @PrimaryKeyJoinColumn
    private ItemType itemType;
	
	@OneToOne
	@JoinColumn (name = "item_price_id")
    private ItemPrice itemPrice;

    @ManyToMany(mappedBy = "item", cascade = CascadeType.PERSIST)
	/*@JoinTable(name = "items_item_taxes", joinColumns = @JoinColumn(name = "item_id"),
			inverseJoinColumns = @JoinColumn(name = "item_tax_id"))*/
    private List<ItemTax> appliedTaxes;
	
	@Column (name="description", nullable = false, unique = true)
    @NotEmpty
    private String description;

	public Long getIdItem() {
		return idItem;
	}

	public void setIdItem(Long idItem) {
		this.idItem = idItem;
	}

	public ItemType getItemType() {
		return itemType;
	}

	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}

	public ItemPrice getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(ItemPrice itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    public List<ItemTax> getAppliedTaxes() {
        return appliedTaxes;
    }

    public void setAppliedTaxes(List<ItemTax> appliedTaxes) {
        this.appliedTaxes = appliedTaxes;
    }
}
