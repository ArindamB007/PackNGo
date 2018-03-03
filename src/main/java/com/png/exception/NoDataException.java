package com.png.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No Data Found")
@ResponseBody
public class NoDataException extends BaseException{

    private static final long serialVersionUID = 4966373151246794522L;
    public NoDataException(String errorCode, String errorMessage){
        setErrorCode(errorCode);
        setErrorMessage(errorMessage);
    }
    public String getMessage(){return getErrorMessage();}
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
