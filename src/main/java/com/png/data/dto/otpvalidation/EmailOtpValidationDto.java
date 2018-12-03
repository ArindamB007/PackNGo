package com.png.data.dto.otpvalidation;

import com.png.util.StringGenerator;

public class EmailOtpValidationDto extends Otp{
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
