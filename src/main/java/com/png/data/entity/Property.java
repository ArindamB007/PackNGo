package com.png.data.entity;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="property")
public class Property extends BaseEntity{
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

	@ManyToMany
    @JoinTable(name = "property_facilities", joinColumns = @JoinColumn(name = "id_property"), inverseJoinColumns = @JoinColumn(name = "id_facility"))
    private Set<Facility> facilities;
	
	@OneToMany (mappedBy = "idEntity")
    private Set<Image> propertyImages;

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

	public Set<Facility> getFacilities() {
		return facilities;
	}

	public void setFacilities(Set<Facility> facilities) {
		this.facilities = facilities;
	}

	public Set<Image> getPropertyImages() {
		return propertyImages;
	}

	public void setPropertyImages(Set<Image> propertyImages) {
		this.propertyImages = propertyImages;
	}
}
