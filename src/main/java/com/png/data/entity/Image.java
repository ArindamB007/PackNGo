package com.png.data.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;


@MappedSuperclass
public class Image extends BaseEntity{
	@Column (name="name")
	@NotEmpty
	private String name;
	
	@Column (name="short_desc")
	private String shortDesc;

	@Column(name="img_path")
	private String imgPath;
	
	@Lob
	@Column
	private byte[] picture;

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

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
}
