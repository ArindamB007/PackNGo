package com.png.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("validation.properties")
@ConfigurationProperties
public class ValidationProperties {
	private String signupRequired;
	private String signupRequiredName;
	private String signupInvalidEmail;
	private String signupDuplicateEmail;
	private String signupSizeEmail;
	private String signupSizePassword;
	private String signupMismatchPassword;
	public String getSignupInvalidEmail() {
		return signupInvalidEmail;
	}
	public void setSignupInvalidEmail(String signupInvalidEmail) {
		this.signupInvalidEmail = signupInvalidEmail;
	}
	public String getSignupDuplicateEmail() {
		return signupDuplicateEmail;
	}
	public void setSignupDuplicateEmail(String signupDuplicateEmail) {
		this.signupDuplicateEmail = signupDuplicateEmail;
	}
	public String getSignupSizeEmail() {
		return signupSizeEmail;
	}
	public void setSignupSizeEmail(String signupSizeEmail) {
		this.signupSizeEmail = signupSizeEmail;
	}
	public String getSignupSizePassword() {
		return signupSizePassword;
	}
	public void setSignupSizePassword(String signupSizePassword) {
		this.signupSizePassword = signupSizePassword;
	}
	public String getSignupMismatchPassword() {
		return signupMismatchPassword;
	}
	public void setSignupMismatchPassword(String signupMismatchPassword) {
		this.signupMismatchPassword = signupMismatchPassword;
	}
	public String getSignupRequired() {
		return signupRequired;
	}
	public void setSignupRequired(String signupRequired) {
		this.signupRequired = signupRequired;
	}
	public String getSignupRequiredName() {
		return signupRequiredName;
	}
	public void setSignupRequiredName(String signupRequiredName) {
		this.signupRequiredName = signupRequiredName;
	}

}
