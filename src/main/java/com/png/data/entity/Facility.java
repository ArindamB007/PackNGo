package com.png.data.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

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

}
