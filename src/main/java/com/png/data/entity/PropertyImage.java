package com.png.data.entity;

import javax.persistence.*;

@Entity
@Table(name="property_image")
public class PropertyImage extends Image{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_property_image")
    private Long idPropertyImage;

    @Column(name= "property_id")
    private Long propertyId;

    public Long getIdPropertyImage() {
        return idPropertyImage;
    }

    public void setIdPropertyImage(Long idPropertyImage) {
        this.idPropertyImage = idPropertyImage;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }
}
