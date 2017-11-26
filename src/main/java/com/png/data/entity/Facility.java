package com.png.data.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="facility")
public class Facility extends BaseEntity{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_facility")
    private Long idFacility;
	
	@Column (name="name", unique=true)
	@NotEmpty
	private String name;
	
	@Column (name="css_class_name")
	private String cssClassName;
	
	@ManyToMany (mappedBy ="facilities")
    private Set<Property> properties;
	
/*	@Column(name = "created_timestamp")
	private Timestamp createdTimestamp;

	@Column(name = "updated_timestamp")
	private Timestamp updatedTimestamp;

	@Column(name = "deleted_flag")
    private Boolean deletedFlag =false;
	
	@Column(name = "enabled_flag")
    private Boolean enabledFlag =true;*/

	public Long getIdFacility() {
		return idFacility;
	}

	public void setIdFacility(Long idFacility) {
		this.idFacility = idFacility;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCssClassName() {
		return cssClassName;
	}

	public void setCssClassName(String cssClassName) {
		this.cssClassName = cssClassName;
	}

	public Set<Property> getProperties() {
		return properties;
	}

	public void setProperties(Set<Property> properties) {
		this.properties = properties;
	}

}
