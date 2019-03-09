package com.png.exception;

public class BaseException extends RuntimeException{
/**
	 * 
	 */
	private static final long serialVersionUID = 4220652593343624408L;
	private String errorCode;
	private String errorMessage;
	private String extendedErrorMessage;
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getExtendedErrorMessage() {
		return extendedErrorMessage;
	}

	public void setExtendedErrorMessage(String extendedErrorMessage) {
		this.extendedErrorMessage = extendedErrorMessage;
	}
}
