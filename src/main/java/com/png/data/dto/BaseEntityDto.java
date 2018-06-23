package com.png.data.dto;

import java.sql.Timestamp;

public class BaseEntityDto {
	private Timestamp createdTimestamp;
	private Timestamp updatedTimestamp;
    private Boolean deletedFlag =false;
    private Boolean enabledFlag =true;
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
}
