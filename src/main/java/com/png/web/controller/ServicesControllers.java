package com.png.web.controller;

import com.png.auth.service.SecurityService;
import com.png.auth.service.UserService;
import com.png.auth.validator.UserValidator;
import com.png.data.dto.availableroomtype.AvailableRoomTypeDto;
import com.png.data.dto.checkinoutdetails.CheckInOutDetailsDto;
import com.png.data.dto.property.PropertyDto;
import com.png.data.dto.user.UserContext;
import com.png.data.entity.User;
import com.png.exception.ValidationException;
import com.png.menu.Menu;
import com.png.services.CustomUserDetailsService;
import com.png.services.PropertyService;
import com.png.services.RoomTypeService;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

@Controller
@RequestMapping("/services")
public class ServicesControllers {
	@Autowired
	UserValidator userValidator;
	
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
		userService.save(user);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@RequestMapping(value ="/user_login",method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> userLogin(@RequestBody Map<String,String> payload){
		System.out.println("User Name: " + payload.get("email"));
		System.out.println("Password: " + payload.get("password"));
		try {
			UserContext userContext = securityService.login(payload.get("email"), payload.get("password"));
			return new ResponseEntity<Object>(userContext, HttpStatus.OK);
		} catch (Exception e){
					HashMap<String,String> errorDetails = new HashMap<String,String>();
					errorDetails.put("type", e.getClass().getSimpleName());
					errorDetails.put("message", e.getMessage());
					return new ResponseEntity<Object>(errorDetails, HttpStatus.NOT_FOUND);
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
	public ResponseEntity<Object> searchRoom(@RequestBody CheckInOutDetailsDto checkInOutDetails){
		List <HashMap <String,String>> errorList = new ArrayList<HashMap <String,String>>();
		try{
			System.out.println("Checkin Date : " + checkInOutDetails.getCheckInTimestamp());
			System.out.println("Checkin Date Converted : " +
					dateFormatter.getTimestampFromString(checkInOutDetails.getCheckInTimestamp()));
			System.out.println("Checkout Date : " + checkInOutDetails.getCheckOutTimestamp());
			System.out.println("Checkout Date Converted : " +
					dateFormatter.getTimestampFromString(checkInOutDetails.getCheckOutTimestamp()));
			List<AvailableRoomTypeDto> availableRoomTypeDtos =
					roomTypeService.getAvailableRoomTypes(
							dateFormatter.getTimestampFromString(checkInOutDetails.getCheckInTimestamp()),
							dateFormatter.getTimestampFromString(checkInOutDetails.getCheckOutTimestamp()),
							checkInOutDetails.getIdProperty());
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
			return new ResponseEntity<Object>(properties,HttpStatus.OK);
		} catch (Exception e){
			HashMap<String,String> errors = new HashMap<String,String>();
			errors.put("type", e.getClass().getSimpleName());
			errors.put("message", e.getMessage());
			errorList.add(errors);
			return new ResponseEntity<Object>(errorList, HttpStatus.NOT_FOUND);
		}
	}
}
