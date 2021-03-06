package com.png.data.entity;

import com.png.util.DateFormatter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@MappedSuperclass
public class BaseEntity {
	@Column(name = "created_timestamp")
	private Timestamp createdTimestamp;

	@Column(name = "updated_timestamp")
	private Timestamp updatedTimestamp;

	@Column(name = "deleted_flag")
    private Boolean deletedFlag =false;
	
	@Column(name = "enabled_flag")
    private Boolean enabledFlag =true;

	BaseEntity(){
		this.createdTimestamp = DateFormatter.getCurrentTime();
		this.updatedTimestamp = DateFormatter.getCurrentTime();
	}

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public Timestamp getUpdatedTimestamp() {
		return updatedTimestamp;
	}

	public void setUpdatedTimestamp(Timestamp updatedTimestamp) {
		this.updatedTimestamp = updatedTimestamp;
	}

	public Boolean getDeletedFlag() {
		return deletedFlag;
	}

	public void setDeletedFlag(Boolean deletedFlag) {
		this.deletedFlag = deletedFlag;
	}

	public Boolean getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(Boolean enabledFlag) {
		this.enabledFlag = enabledFlag;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

}
