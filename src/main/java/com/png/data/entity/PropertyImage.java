package com.png.data.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name="image")
public class PropertyImage {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_property_image")
    private Long idPropertyImage;
	
	@Column (name="name")
	@NotEmpty
	private String name;
	
	@Column (name="short_desc")
	private String shortDesc;
	
	@Lob
	@Column
	private byte[] picture;
	
	@ManyToMany (mappedBy ="property_images")
    private Set<Property> properties;

	public Long getIdPropertyImage() {
		return idPropertyImage;
	}

	public void setIdPropertyImage(Long idPropertyImage) {
		this.idPropertyImage = idPropertyImage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public Set<Property> getProperties() {
		return properties;
	}

	public void setProperties(Set<Property> properties) {
		this.properties = properties;
	}


}
