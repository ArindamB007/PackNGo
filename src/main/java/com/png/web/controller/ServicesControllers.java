package com.png.web.controller;

import java.util.*;
import java.util.stream.Collectors;

import com.png.auth.service.SecurityService;
import com.png.menu.Menu;
import com.png.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.png.auth.service.UserService;
import com.png.auth.validator.UserValidator;
import com.png.data.entity.User;
import com.png.data.entity.UserContext;
import com.png.exception.ValidationException;

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
	
	@RequestMapping(value ="/signup",method = RequestMethod.POST)
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

	@RequestMapping(value ="/userlogin",method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> userLogin(@RequestBody Map<String,String> payload){
		ArrayList <HashMap <String,String>> errorList = new ArrayList<HashMap <String,String>>();
		System.out.println("User Name: " + payload.get("email"));
		System.out.println("Password: " + payload.get("password"));
		try {
			UserContext userContext = securityService.login(payload.get("email"), payload.get("password"));
			return new ResponseEntity<Object>(userContext, HttpStatus.OK);
		} catch (Exception e){
					HashMap<String,String> errors = new HashMap<String,String>();
					errors.put("type", e.getClass().getSimpleName());
					errors.put("message", e.getMessage());
					errorList.add(errors);
					return new ResponseEntity<Object>(errorList, HttpStatus.NOT_FOUND);
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
}
