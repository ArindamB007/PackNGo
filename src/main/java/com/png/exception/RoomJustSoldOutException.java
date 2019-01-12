package com.png.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Room just sold out")
@ResponseBody
public class RoomJustSoldOutException extends BaseException{

    private static final long serialVersionUID = 4966373151247989122L;
    public RoomJustSoldOutException(String errorCode, String errorMessage){
        setErrorCode(errorCode);
        setErrorMessage(errorMessage);
    }
    public String getMessage(){return getErrorMessage();}
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
