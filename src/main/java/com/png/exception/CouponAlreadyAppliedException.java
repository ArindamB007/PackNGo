package com.png.exception;


public class CouponAlreadyAppliedException extends BaseException {
    private static final long serialVersionUID = 5045694856946794522L;

    public CouponAlreadyAppliedException(String errorCode, String errorMessage) {
        setErrorCode(errorCode);
        setErrorMessage(errorMessage);
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
