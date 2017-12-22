package com.png.data.dto.availableroomtype;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Set;

import com.png.data.entity.RoomTypeImage;

public class AvailableRoomTypeDto {
	    private Integer idRoomType;

	    private String typeName;

	    private BigDecimal basePrice;
	    
	    private Integer availableCount;

	    private Integer discount;

	    private String description;

	    private HashMap<String,Boolean> facilities;

	    private Set<RoomTypeImage> roomTypeImages;

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

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Set<RoomTypeImage> getRoomTypeImages() {
			return roomTypeImages;
		}

		public void setRoomTypeImages(Set<RoomTypeImage> roomTypeImages) {
			this.roomTypeImages = roomTypeImages;
		}

		public HashMap<String, Boolean> getFacilities() {
			return facilities;
		}

		public void setFacilities(HashMap<String, Boolean> facilities) {
			this.facilities = facilities;
		}
		
		
	    
}
