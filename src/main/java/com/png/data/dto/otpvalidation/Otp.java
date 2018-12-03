package com.png.data.dto.otpvalidation;

import com.png.util.StringGenerator;

public class Otp {
    private String otp;

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public void createOtp(int otpLength){
        this.otp= StringGenerator.randomNumeric(otpLength);
    }
}
