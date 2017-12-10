package com.png.data.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by Arindam on 12/5/2017.
 */

@Entity
@Table(name="room_type")
public class RoomType extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_room_type")
    private Integer idRoomType;

    @Column (name="type_name", nullable = false,unique = true)
    @NotEmpty
    private String typeName;

    @Column (name = "base_price")
    private BigDecimal basePrice;

    @Column (name="discount")
    private Integer discount;

    @Column(name="description")
    private String description;

    @ManyToMany
    @JoinTable(name = "room_types_facilities", joinColumns = @JoinColumn(name = "id_room_type"), inverseJoinColumns = @JoinColumn(name = "id_facility"))
    private Set<Facility> facilities;

    public Integer getIdRoomType() {
        return idRoomType;
    }

    public void setIdRoomType(Integer idRoomType) {
        this.idRoomType = idRoomType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Facility> getFacilities() {
        return facilities;
    }

    public void setFacilities(Set<Facility> facilities) {
        this.facilities = facilities;
    }
}
