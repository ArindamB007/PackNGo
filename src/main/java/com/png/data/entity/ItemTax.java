package com.png.data.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name="item_tax")
public class ItemTax extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_item_tax")
    private Integer idItemTax;

    @Column (name="item_tax_code", nullable = false, unique = true)
    @NotEmpty
    private String itemTaxCode;

    @Column (name="item_tax_description", nullable = false)
    @NotEmpty
    private String itemTaxDescription;

    @Column (name="item_tax_percent", nullable = false)
    @NotEmpty
    private String itemTaxPercent;

    public Integer getIdItemTax() {
        return idItemTax;
    }

    public void setIdItemTax(Integer idItemTax) {
        this.idItemTax = idItemTax;
    }

    public String getItemTaxCode() {
        return itemTaxCode;
    }

    public void setItemTaxCode(String itemTaxCode) {
        this.itemTaxCode = itemTaxCode;
    }

    public String getItemTaxDescription() {
        return itemTaxDescription;
    }

    public void setItemTaxDescription(String itemTaxDescription) {
        this.itemTaxDescription = itemTaxDescription;
    }

    public String getItemTaxPercent() {
        return itemTaxPercent;
    }

    public void setItemTaxPercent(String itemTaxPercent) {
        this.itemTaxPercent = itemTaxPercent;
    }
}
