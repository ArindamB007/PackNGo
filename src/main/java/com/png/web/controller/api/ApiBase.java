package com.png.web.controller.api;

import com.png.auth.service.SecurityService;
import com.png.auth.service.UserService;
import com.png.comms.email.EmailService;
import com.png.data.responses.ApiResponse;
import com.png.exception.ApiBusinessException;
import com.png.exception.BaseException;
import com.png.services.*;
import com.png.util.DateFormatter;
import com.png.validators.EmailValidator;
import com.png.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ApiBase {
    @Autowired
    protected UserValidator userValidator;

    @Autowired
    protected EmailValidator emailValidator;

    @Autowired
    protected OtpGeneratorService otpGeneratorService;

    @Autowired
    protected UserService userService;

    @Autowired
    protected CustomUserDetailsService userDetailsService;

    @Autowired
    protected SecurityService securityService;

    @Autowired
    protected PropertyService propertyService;

    @Autowired
    protected RoomTypeService roomTypeService;

    @Autowired
    protected EmailService emailService;

    @Autowired
    protected InvoiceProcessorService invoiceProcessorService;

    @Autowired
    protected InvoiceCancellationService invoiceCancellationService;

    @Autowired
    protected CouponProcessorService couponProcessorService;

    @Autowired
    protected InvoiceSearchService invoiceSearchService;

    @Autowired
    protected RoomMaintenanceService roomMaintenanceService;

    @Autowired
    protected BCryptPasswordEncoder bCryptPasswordEncoder;


    HashMap<String, String> populateErrorDetails(Exception e) {
        HashMap<String, String> errorDetails = new HashMap<String, String>();
        errorDetails.put("type", e.getClass().getSimpleName());
        errorDetails.put("errorCode", ((BaseException) e).getErrorCode());
        errorDetails.put("message", ((BaseException) e).getErrorMessage());
        return errorDetails;
    }

    ApiResponse populateErrorResponse(Exception e) {
        ApiResponse apiResponse = new ApiResponse();
        HashMap<String, String> errorDetails = new HashMap<String, String>();
        errorDetails.put("type", e.getClass().getSimpleName());
        errorDetails.put("errorCode", ((BaseException) e).getErrorCode());
        if (e.getClass() == ApiBusinessException.class) {
            errorDetails.put("message", ((BaseException) e).getErrorMessage());
            e.printStackTrace();
            //todo log the error
        } else {
            e.printStackTrace();
            //todo log the error here
            errorDetails.put("message", "An unexpected error occurred, please report to our support section");
        }
        apiResponse.setApiError(errorDetails);
        return apiResponse;
    }

    List<HashMap<String, String>> getBindingErrors(BindingResult bindingResult) {
        List<HashMap<String, String>> errorList = new ArrayList<HashMap<String, String>>();
        if (bindingResult.hasErrors()) {
            for (FieldError error : bindingResult.getFieldErrors()) {
                HashMap<String, String> errors = new HashMap<String, String>();
                errors.put("fieldName", error.getField());
                errors.put("errorType", error.getCode());
                errors.put("message", error.getDefaultMessage());
                errorList.add(errors);
            }
        }
        return errorList;
    }
}
