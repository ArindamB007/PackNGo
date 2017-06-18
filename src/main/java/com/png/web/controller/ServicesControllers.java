package com.png.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.png.auth.validator.UserValidator;
import com.png.data.entity.User;
import com.png.exception.ValidationException;

@Controller
@RequestMapping("/services")
public class ServicesControllers {
	@Autowired
	UserValidator userValidator;
	
	@RequestMapping(value ="/signup",method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> signup(@RequestBody User user,BindingResult bindingResult) throws ValidationException{
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
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
