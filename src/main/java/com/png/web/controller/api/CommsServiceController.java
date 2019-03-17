package com.png.web.controller.api;

import com.png.data.dto.otpvalidation.EmailOtpValidationDto;
import com.png.exception.BaseException;
import com.png.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/api/communication")
public class CommsServiceController extends ApiBase {
    @RequestMapping(value = "/send_email_otp", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> sendEmailOtp(@RequestBody EmailOtpValidationDto emailOtp,
                                               BindingResult bindingResult) throws ValidationException {
        try {
            List<HashMap<String, String>> errorList = new ArrayList<HashMap<String, String>>();
            emailValidator.validate(emailOtp, bindingResult);
            if (bindingResult.hasErrors()) {
                return new ResponseEntity<Object>(getBindingErrors(bindingResult), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(otpGeneratorService.generateEmailOpt(emailOtp), HttpStatus.OK);
        } catch (BaseException e) {
            return new ResponseEntity<>(populateErrorDetails(e), HttpStatus.BAD_REQUEST);
        }
    }
}
