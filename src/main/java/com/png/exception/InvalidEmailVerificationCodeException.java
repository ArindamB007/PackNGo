package com.png.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Email verification code expired")
@ResponseBody
public class InvalidEmailVerificationCodeException extends BaseException{
    private static final long serialVersionUID = 5058979551475794522L;
    public InvalidEmailVerificationCodeException(String errorCode, String errorMessage){
        setErrorCode(errorCode);
        setErrorMessage(errorMessage);
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
