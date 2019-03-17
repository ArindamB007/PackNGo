package com.png.data.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
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
    private Long idRoomType;

    @Column (name="type_name", nullable = false,unique = true)
    @NotEmpty
    private String typeName;

    @Column (name = "base_price")
    private BigDecimal basePrice;

    @Column (name="discount")
    private Integer discount;

    @Column (name="max_adult_occupancy",nullable = false)
    private Integer maxAdultOccupancy;

    @Column (name="max_child_occupancy",nullable = false)
    private Integer maxChildOccupancy;
    
    @Column (name="max_extra_adult_occupancy",nullable = false)
    private Integer maxExtraAdultOccupancy;

    @Column (name="max_extra_child_occupancy",nullable = false)
    private Integer maxExtraChildOccupancy;

    @Column (name="max_total_occupancy",nullable = false)
    private Integer maxTotalOccupancy;
    
    @Column(name="description")
    private String description;

    @ManyToMany
    @JoinTable(name = "room_types_facilities", joinColumns = @JoinColumn(name = "id_room_type"), inverseJoinColumns = @JoinColumn(name = "id_facility"))
    private Set<Facility> facilities;
    
    @OneToMany (mappedBy = "roomTypeId")
    private Set<RoomTypeImage> roomTypeImages;
    
    @OneToMany (mappedBy = "roomTypeId")
    private List<MealPlan> mealPlans;

    public Long getIdRoomType() {
        return idRoomType;
    }

    public void setIdRoomType(Long idRoomType) {
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

    public Integer getMaxAdultOccupancy() { return maxAdultOccupancy; }

    public void setMaxAdultOccupancy(Integer maxAdultOccupancy) { this.maxAdultOccupancy = maxAdultOccupancy; }

    public Integer getMaxChildOccupancy() { return maxChildOccupancy; }

    public void setMaxChildOccupancy(Integer maxChildOccupancy) { this.maxChildOccupancy = maxChildOccupancy; }

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

    public Set<RoomTypeImage> getRoomTypeImages() {
        return roomTypeImages;
    }

    public void setRoomTypeImages(Set<RoomTypeImage> roomTypeImages) {
        this.roomTypeImages = roomTypeImages;
    }

	public List<MealPlan> getMealPlans() {
		return mealPlans;
	}

	public void setMealPlans(List<MealPlan> mealPlans) {
		this.mealPlans = mealPlans;
	}

	public Integer getMaxExtraAdultOccupancy() {
		return maxExtraAdultOccupancy;
	}

	public void setMaxExtraAdultOccupancy(Integer maxExtraAdultOccupancy) {
		this.maxExtraAdultOccupancy = maxExtraAdultOccupancy;
	}

	public Integer getMaxExtraChildOccupancy() {
		return maxExtraChildOccupancy;
	}

	public void setMaxExtraChildOccupancy(Integer maxExtraChildOccupancy) {
		this.maxExtraChildOccupancy = maxExtraChildOccupancy;
	}

	public Integer getMaxTotalOccupancy() {
		return maxTotalOccupancy;
	}

	public void setMaxTotalOccupancy(Integer maxTotalOccupancy) {
		this.maxTotalOccupancy = maxTotalOccupancy;
	}

}
