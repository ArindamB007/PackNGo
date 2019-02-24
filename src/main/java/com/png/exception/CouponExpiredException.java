package com.png.exception;

import com.png.util.DateFormatter;

public class CouponExpiredException extends BaseException {
    private static final long serialVersionUID = 5045694895946794522L;

    public CouponExpiredException(String errorCode, String errorMessage) {
        setErrorCode(errorCode);
        setErrorMessage(errorMessage);
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
