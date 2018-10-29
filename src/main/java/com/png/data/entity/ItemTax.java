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

    @Column (name="item_tax_code", nullable = false)
    @NotEmpty
    private String itemTaxCode;

    @Column (name="item_tax_description", nullable = false)
    @NotEmpty
    private String itemTaxDescription;

    @Column (name="item_tax_percent", nullable = false)
    @NotEmpty
    private String itemTaxPercent;

}
