package com.png.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Incorrect or missing Parameters")
@ResponseBody
public class ValidationException extends BaseException{

	private static final long serialVersionUID = 4966373151246794522L;

	public ValidationException(String errorCode, String errorMessage){
		setErrorCode(errorCode);
		setErrorMessage(errorMessage);
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
