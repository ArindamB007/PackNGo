package com.png.web.controller;

import com.png.auth.service.SecurityService;
import com.png.auth.service.UserService;
import com.png.data.dto.search.InvoiceSearchDto;
import com.png.data.dto.search.PagedRequest;
import com.png.data.requests.ApplyCouponRequest;
import com.png.data.requests.InvoiceCancellationRequest;
import com.png.data.responses.ApiResponse;
import com.png.exception.*;
import com.png.util.StringGenerator;
import com.png.validators.EmailValidator;
import com.png.validators.UserValidator;
import com.png.comms.email.EmailService;
import com.png.data.dto.availableroomtype.AvailableRoomTypeDto;
import com.png.data.dto.bookingcart.BookingCartDto;
import com.png.data.dto.checkinoutdetails.CheckInOutSearchParamsDto;
import com.png.data.dto.invoice.InvoiceDto;
import com.png.data.dto.otpvalidation.EmailOtpValidationDto;
import com.png.data.dto.property.PropertyDto;
import com.png.data.dto.user.UserContext;
import com.png.data.entity.User;
import com.png.menu.Menu;
import com.png.services.*;

import com.png.util.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.*;

@Controller
@RequestMapping("/services")
public class ServicesControllers {
	@Autowired
	private UserValidator userValidator;

	@Autowired
	private EmailValidator emailValidator;

	@Autowired
	private OtpGeneratorService otpGeneratorService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private PropertyService propertyService;
	
	@Autowired
	private RoomTypeService roomTypeService;

	@Autowired
	private DateFormatter dateFormatter;

	@Autowired
    private EmailService emailService;

	@Autowired
	private InvoiceProcessorService invoiceProcessorService;

	@Autowired
	private InvoiceCancellationService invoiceCancellationService;

	@Autowired
	private CouponProcessorService couponProcessorService;

	@Autowired
	private InvoiceSearchService invoiceSearchService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

    private HashMap<String,String> populateErrorDetails(Exception e){
		HashMap<String, String> errorDetails = new HashMap<String, String>();
		errorDetails.put("type", e.getClass().getSimpleName());
		errorDetails.put("errorCode", ((BaseException) e).getErrorCode());
		errorDetails.put("message", ((BaseException) e).getErrorMessage());
		return errorDetails;
	}

	private ApiResponse populateErrorResponse(Exception e) {
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


}
