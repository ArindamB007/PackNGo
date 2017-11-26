package com.png.data.entity;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="property")
public class Property {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_property")
    private Long idProperty;

    @Column (name="name")
    @NotEmpty
    private String name;
    
    @Column(name="tagline")
    private String tagLine;
    
    @Column(name="short_desc")
    private String shortDesc;

    @Column(name="description")
    private String description;
    
    @Column(name="img_path")
    private String imgPath;

	@Column(name = "created_timestamp")
	private Timestamp createdTimestamp;

	@Column(name = "updated_timestamp")
	private Timestamp updatedTimestamp;

	@Column(name = "deleted_flag")
    private Boolean deleteFlag =false;
	
	@Column(name = "enabled_flag")
    private Boolean enabledFlag =true;

	@ManyToMany
    @JoinTable(name = "property_facilities", joinColumns = @JoinColumn(name = "id_property"), inverseJoinColumns = @JoinColumn(name = "id_facility"))
    private Set<Facility> facilities;
	
	@ManyToMany
    @JoinTable(name = "property_propertyimages", joinColumns = @JoinColumn(name = "id_vendor_property"), inverseJoinColumns = @JoinColumn(name = "id_property_image"))
    private Set<PropertyImage> property_images;

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

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
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

	public Set<Facility> getFacilities() {
		return facilities;
	}

	public void setFacilities(Set<Facility> facilities) {
		this.facilities = facilities;
	}

	public Set<PropertyImage> getProperty_images() {
		return property_images;
	}

	public void setProperty_images(Set<PropertyImage> property_images) {
		this.property_images = property_images;
	}

}
