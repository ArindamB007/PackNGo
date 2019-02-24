package com.png.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid Coupon Code")
@ResponseBody
public class InvalidCouponCodeException extends BaseException {

    private static final long serialVersionUID = 5045698745546794522L;

    public InvalidCouponCodeException(String errorCode, String errorMessage) {
        setErrorCode(errorCode);
        setErrorMessage(errorMessage);
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
