package com.png.auth.validator;

import com.png.data.dto.otpvalidation.EmailOtpValidationDto;
import com.png.messages.CustomMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
@ConfigurationProperties("emailOtp")
public class EmailValidator implements Validator {
    @Autowired
    CustomMessages customMessages;

    @Override
    public boolean supports(Class<?> aClass) {
        return EmailOtpValidationDto.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        EmailOtpValidationDto emailOtp = (EmailOtpValidationDto) obj;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required",
                customMessages.getMessage("emailotp.invalid.email"));

    }
}
