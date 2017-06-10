package com.png.auth.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.png.auth.service.UserService;
import com.png.data.entity.User;

public class UserValidator implements Validator {
	@Autowired
	private UserService userService;
	
	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		User user = (User) obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
		if (user.getEmail().length() < 7 || user.getEmail().length() > 48){
			errors.rejectValue("email", "Size.userForm.email");
		}
		if (userService.findByUsername(user.getEmail()) != null){
			errors.rejectValue("email", "Duplicate.serForm.username");
		}
		ValidationUtils.rejectIfEmpty(errors, "password", "NotEmpty");
		if (user.getPassword().length()<8 ||user.getPassword().length()>48){
			errors.rejectValue("password", "Size.userform.password");
		}
		if(!user.getConfirmPassword().equals(user.getPassword())){
			errors.rejectValue("confirmPassword","Diff.userform.confirmPassword");
		}
		
	}

}
