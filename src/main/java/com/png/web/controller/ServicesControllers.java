package com.png.web.controller;

import com.png.auth.service.SecurityService;
import com.png.auth.service.UserService;
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
import com.png.exception.BaseException;
import com.png.exception.EmailVerificationExpiredException;
import com.png.exception.InvalidEmailVerificationCodeException;
import com.png.exception.ValidationException;
import com.png.menu.Menu;
import com.png.services.*;

import com.png.util.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	UserValidator userValidator;

	@Autowired
	EmailValidator emailValidator;

	@Autowired
	OtpGeneratorService otpGeneratorService;
	
	@Autowired
	UserService userService;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	SecurityService securityService;

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

    private HashMap<String,String> populateErrorDetails(BaseException e){
        HashMap<String,String> errorDetails = new HashMap<String,String>();
        errorDetails.put("type", e.getClass().getSimpleName());
        errorDetails.put("errorCode",e.getErrorCode());
        errorDetails.put("message", e.getErrorMessage());
        return errorDetails;
    }
    private List <HashMap <String,String>> getBindingErrors(BindingResult bindingResult) {
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
	@RequestMapping(value ="/sign_up",method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> signup(@RequestBody User user,BindingResult bindingResult) throws ValidationException{
		ArrayList <HashMap <String,String>> errorList = new ArrayList<HashMap <String,String>>();
		userValidator.validate(user, bindingResult);
		if (bindingResult.hasErrors()){
			for (FieldError error : bindingResult.getFieldErrors()){
				HashMap<String,String> errors = new HashMap<String,String>();
				errors.put("fieldName", error.getField());
				errors.put("errorType", error.getCode());
				errors.put("message", error.getDefaultMessage());
				errorList.add(errors);
			}
			return new ResponseEntity<Object>(errorList,HttpStatus.BAD_REQUEST);
		}

		try {
			userService.save(user);
			emailService.sendValidationEmailAsync(user.getFirstName(),user.getEmail(),
					user.getEmailValidUptoTimestamp(),user.getEmailValidationCode());
		} catch(Exception e){
			HashMap<String,String> errorDetails = new HashMap<String,String>();
			errorDetails.put("type", e.getClass().getSimpleName());
			errorDetails.put("message", e.getMessage());
			return new ResponseEntity<Object>(errorDetails, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@RequestMapping(value ="/user_login",method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> userLogin(@RequestBody Map<String,String> payload){
		System.out.println("User Name: " + payload.get("email"));
		System.out.println("Password: " + payload.get("password"));
		try {
			UserContext userContext = securityService.login(payload.get("email"), payload.get("password"));
			return new ResponseEntity<>(userContext, HttpStatus.OK);
		} catch (BaseException e){
					return new ResponseEntity<>(populateErrorDetails(e), HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value ="/resend_verification_email",method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> resendVerificationEmail(@RequestBody Map<String,String> payload){
		System.out.println("User Name: " + payload.get("email"));
		try {
			User user = userService.resendEmailValidationCode(payload.get("email"));
			emailService.sendValidationEmailAsync(user.getFirstName(),user.getEmail(),
					user.getEmailValidUptoTimestamp(),user.getEmailValidationCode());
			return new ResponseEntity<>(true, HttpStatus.OK);
		} catch (BaseException e){
			return new ResponseEntity<>(populateErrorDetails(e), HttpStatus.NOT_FOUND);
		}

	}

    @RequestMapping(value ="/user_menu",method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> userMenu(@RequestBody UserContext userContext){
		ArrayList <HashMap <String,String>> errorList = new ArrayList<HashMap <String,String>>();
		try {
			Collection<Menu> menuToShow= userDetailsService.getUserMenu(userContext.getIdUser());
			return new ResponseEntity<Object>(menuToShow, HttpStatus.OK);
		} catch (Exception e){
			HashMap<String,String> errors = new HashMap<String,String>();
			errors.put("type", e.getClass().getSimpleName());
			errors.put("message", e.getMessage());
			errorList.add(errors);
			return new ResponseEntity<Object>(errorList, HttpStatus.NOT_FOUND);
		}

	}
	@RequestMapping(value ="/user_logoff",method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> userLogoff(HttpServletRequest request, HttpServletResponse response){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return new ResponseEntity<Object>("",HttpStatus.OK);
	}
	@RequestMapping(value ="/search_room_types",method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> searchRoom(@RequestBody CheckInOutSearchParamsDto checkInSearchParams){
		List <HashMap <String,String>> errorList = new ArrayList<HashMap <String,String>>();
		try{
			System.out.println("Checkin Date : " + checkInSearchParams.getCheckInTimestamp());
			System.out.println("Checkin Date Converted : " +
					dateFormatter.getTimestampFromString(checkInSearchParams.getCheckInTimestamp()));
			System.out.println("Checkout Date : " + checkInSearchParams.getCheckOutTimestamp());
			System.out.println("Checkout Date Converted : " +
					dateFormatter.getTimestampFromString(checkInSearchParams.getCheckOutTimestamp()));
			List<AvailableRoomTypeDto> availableRoomTypeDtos =
					roomTypeService.getAvailableRoomTypes(
							dateFormatter.getTimestampFromString(checkInSearchParams.getCheckInTimestamp()),
							dateFormatter.getTimestampFromString(checkInSearchParams.getCheckOutTimestamp()),
							checkInSearchParams.getIdProperty());
			System.out.println(availableRoomTypeDtos);
			return new ResponseEntity<Object>(availableRoomTypeDtos,HttpStatus.OK);
		} catch (Exception e){
			HashMap<String,String> errors = new HashMap<String,String>();
			errors.put("type", e.getClass().getSimpleName());
			errors.put("message", e.getMessage());
			errorList.add(errors);
			return new ResponseEntity<Object>(errorList, HttpStatus.NOT_FOUND);
		}
		
	}

	@RequestMapping(value ="/getAllEnabledProperties",method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getAllEnabledProperties(HttpServletResponse response){
		ArrayList <HashMap <String,String>> errorList = new ArrayList<HashMap <String,String>>();
		try {
			ArrayList <PropertyDto> properties = propertyService.getAllProperties();
			System.out.println(properties);
			return new ResponseEntity<>(properties,HttpStatus.OK);
		} catch (Exception e){
			HashMap<String,String> errors = new HashMap<String,String>();
			errors.put("type", e.getClass().getSimpleName());
			errors.put("message", e.getMessage());
			errorList.add(errors);
			return new ResponseEntity<Object>(errorList, HttpStatus.NOT_FOUND);
		}
	}

    @RequestMapping(value ="/verify_email/{verificationCode}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> verifyEmail(@PathVariable String verificationCode){
        try {
            User user = userService.findByEmailValidationCode(verificationCode);
            if (user == null)
                throw new InvalidEmailVerificationCodeException("1003","Invalid Email Verification Code");
            if (new Timestamp(new java.util.Date().getTime()).after(user.getEmailValidUptoTimestamp()))
                throw new EmailVerificationExpiredException("1002",
                        String.format("Email verification code expired on %s",
                                DateFormatter.getDateStringFromTimestamp(user.getEmailValidUptoTimestamp())));
            System.out.println("Service called");
            userService.resetEmailValidationCode(verificationCode);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (BaseException e){
            return new ResponseEntity<>(populateErrorDetails(e), HttpStatus.BAD_REQUEST);
        }

    }

	@RequestMapping(value ="/prepare_invoice",method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> prepareInvoice(@RequestBody BookingCartDto bookingCartDto){
		try {
			InvoiceDto invoice = invoiceProcessorService.createInvoice(bookingCartDto);
			return new ResponseEntity<>(invoice, HttpStatus.OK);
		} catch (BaseException e){
			return new ResponseEntity<>(populateErrorDetails(e), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value ="/send_email_otp",method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> sendEmailOtp(@RequestBody EmailOtpValidationDto emailOtp,
											   BindingResult bindingResult) throws ValidationException{
		try {
			List <HashMap <String,String>> errorList = new ArrayList<HashMap <String,String>>();
			emailValidator.validate(emailOtp, bindingResult);
			if (bindingResult.hasErrors()){
				return new ResponseEntity<Object>(getBindingErrors(bindingResult),HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(otpGeneratorService.generateEmailOpt(emailOtp), HttpStatus.OK);
		} catch (BaseException e){
			return new ResponseEntity<>(populateErrorDetails(e), HttpStatus.BAD_REQUEST);
		}
	}
}
