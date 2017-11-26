package com.png.data.dto.user;

import com.png.data.entity.User;

import java.sql.Timestamp;
import java.util.ArrayList;

public class UserContext {
    private Long idUser;

    private String email;

    private Boolean emailValidated = false;

    private String firstName;
    
    private String lastName;
    
	private Timestamp lastLoginTimestamp;
	
	private Timestamp createdTimestamp;
	
	private Timestamp updatedTimestamp;

    private Boolean deleteFlag =false;
    
    private ArrayList<String> userRoles;

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getEmailValidated() {
		return emailValidated;
	}

	public void setEmailValidated(Boolean emailValidated) {
		this.emailValidated = emailValidated;
	}

	public Boolean getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
	public void setUserDetails(User user){
		this.idUser = user.getIdUser();
		this.email = user.getEmail();
		this.emailValidated = user.getEmailValidated();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.lastLoginTimestamp = user.getLastLoginTimestamp();
		this.createdTimestamp = user.getCreatedTimestamp();
		this.updatedTimestamp = user.getUpdatedTimestamp();
		this.deleteFlag =user.getDeleteFlag();
	}

	public Timestamp getLastLoginTimestamp() {
		return lastLoginTimestamp;
	}

	public void setLastLoginTimestamp(Timestamp lastLoginTimestamp) {
		this.lastLoginTimestamp = lastLoginTimestamp;
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

	public ArrayList<String> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(ArrayList<String> userRoles) {
		this.userRoles = userRoles;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



}
