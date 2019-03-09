package com.png.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Business exception")
@ResponseBody
public class ApiBusinessException extends BaseException {
    private static final long serialVersionUID = 5058963145796794522L;

    public ApiBusinessException(String errorCode, String errorMessage) {
        setErrorCode(errorCode);
        setErrorMessage(errorMessage);
    }

    public ApiBusinessException(String errorCode, String errorMessage, String extendedErrorMessage) {
        setErrorCode(errorCode);
        setErrorMessage(errorMessage);
        setExtendedErrorMessage(extendedErrorMessage);
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
