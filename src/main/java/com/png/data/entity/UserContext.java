package com.png.data.entity;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

public class UserContext {
    private Long idUser;

    private String email;

    private Boolean emailValidated = false;

    private String name;

	private String lastLoginTimestamp;

	private String createdTimestamp;

	private String updatedTimestamp;

    private Boolean deleteFlag =false;
    
    private Set<GrantedAuthority> grantedAuthorities;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastLoginTimestamp() {
		return lastLoginTimestamp;
	}

	public void setLastLoginTimestamp(Timestamp lastLoginTimestamp) {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		this.lastLoginTimestamp = df.format(lastLoginTimestamp);
	}

	public String getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		this.createdTimestamp = df.format(createdTimestamp);
	}

	public String getUpdatedTimestamp() {
		return updatedTimestamp;
	}

	public void setUpdatedTimestamp(Timestamp updatedTimestamp) {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		this.updatedTimestamp = df.format(updatedTimestamp);
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
		this.name = user.getName();
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		if (user.getLastLoginTimestamp()!=null)
			this.lastLoginTimestamp = df.format(user.getLastLoginTimestamp());
		if (user.getCreatedTimestamp()!=null)
			this.createdTimestamp = df.format(user.getCreatedTimestamp());
		if (user.getUpdatedTimestamp()!=null)
			this.updatedTimestamp = df.format(user.getUpdatedTimestamp());
		this.deleteFlag =user.getDeleteFlag();
	}

	public Set<GrantedAuthority> getGrantedAuthorities() {
		return grantedAuthorities;
	}

	public void setGrantedAuthorities(Set<GrantedAuthority> grantedAuthorities) {
		this.grantedAuthorities = grantedAuthorities;
	}

}
