package com.png.data.dto.property;

import java.sql.Timestamp;
import java.util.HashMap;

public class PropertyDto {
    private Long idProperty;

    private String name;
    
    private String tagLine;
    
    private String shortDesc;

    private String description;

	private Timestamp createdTimestamp;

	private Timestamp updatedTimestamp;

    private Boolean deleteFlag =false;
	
    private Boolean enabledFlag =true;

    private HashMap<String,Boolean> facilities;

	private HashMap<String,Object> images;

	public Long getIdProperty() {
		return idProperty;
	}

	public void setIdProperty(Long idProperty) {
		this.idProperty = idProperty;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTagLine() {
		return tagLine;
	}

	public void setTagLine(String tagLine) {
		this.tagLine = tagLine;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public Timestamp getUpdatedTimestamp() {
		return updatedTimestamp;
	}

	public void setUpdatedTimestamp(Timestamp updatedTimestamp) {
		this.updatedTimestamp = updatedTimestamp;
	}

	public Boolean getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Boolean getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(Boolean enabledFlag) {
		this.enabledFlag = enabledFlag;
	}

	public HashMap<String, Boolean> getFacilities() {
		return facilities;
	}

	public void setFacilities(HashMap<String, Boolean> facilities) {
		this.facilities = facilities;
	}

	public HashMap<String, Object> getImages() {
		return images;
	}

	public void setImages(HashMap<String, Object> images) {
		this.images = images;
	}
}
