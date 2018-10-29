package com.png.data.dto.availableroomtype;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import com.png.data.dto.roomtypeimage.RoomTypeImageDto;

public class AvailableRoomTypeDto {
	    private Long idRoomType;

	    private String typeName;

	    private BigDecimal basePrice;
	    
	    private Integer availableCount;

	    private Integer discount;

	    private Integer maxAdultOccupancy;

	    private Integer maxChildOccupancy;

	    private Integer maxExtraAdultOccupancy;

	    private Integer maxExtraChildOccupancy;
	    
	    private Integer maxTotalOccupancy;
	    
	    private String description;

	    private HashMap<String,Boolean> facilities;

	    private HashMap<String,RoomTypeImageDto> roomTypeImages;
	    
	    private List<MealPlanDto> mealPlans;

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

		public Integer getAvailableCount() {
			return availableCount;
		}

		public void setAvailableCount(Integer availableCount) {
			this.availableCount = availableCount;
		}

		public Integer getDiscount() {
			return discount;
		}

		public void setDiscount(Integer discount) {
			this.discount = discount;
		}

		public Integer getMaxAdultOccupancy() {
			return maxAdultOccupancy;
		}

		public void setMaxAdultOccupancy(Integer maxAdultOccupancy) {
			this.maxAdultOccupancy = maxAdultOccupancy;
		}

		public Integer getMaxChildOccupancy() {
			return maxChildOccupancy;
		}

		public void setMaxChildOccupancy(Integer maxChildOccupancy) {
			this.maxChildOccupancy = maxChildOccupancy;
		}

		public String getDescription() {
				return description;
			}

		public void setDescription(String description) {
			this.description = description;
		}

		public HashMap<String,RoomTypeImageDto> getRoomTypeImages() {
			return roomTypeImages;
		}

		public void setRoomTypeImages(HashMap<String,RoomTypeImageDto> roomTypeImages) {
			this.roomTypeImages = roomTypeImages;
		}

		public HashMap<String, Boolean> getFacilities() {
			return facilities;
		}

		public void setFacilities(HashMap<String, Boolean> facilities) {
			this.facilities = facilities;
		}

		public List<MealPlanDto> getMealPlans() {
			return mealPlans;
		}

		public void setMealPlans(List<MealPlanDto> mealPlans) {
			this.mealPlans = mealPlans;
		}

		
		public Integer getMaxTotalOccupancy() {
			return maxTotalOccupancy;
		}

		public void setMaxTotalOccupancy(Integer maxTotalOccupancy) {
			this.maxTotalOccupancy = maxTotalOccupancy;
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
}
