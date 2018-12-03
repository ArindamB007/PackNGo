package com.png.data.dto.otpvalidation;

public class MobileOtpValidationDto extends Otp{
    private String mobileNo;

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
}
