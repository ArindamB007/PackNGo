package com.png.auth.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.png.auth.service.UserService;
import com.png.data.entity.User;
import com.png.properties.ValidationProperties;

@Component
@ConfigurationProperties("signUp")
public class UserValidator implements Validator {
	@Autowired
	private ValidationProperties vP;
	
	@Autowired
	private UserService userService;
	
	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		User user = (User) obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required",vP.getSignupRequiredName());
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required",vP.getSignupRequired());
		if (user.getEmail()!=null && (user.getEmail().length() < 7 || user.getEmail().length() > 48)){
			errors.rejectValue("email", "custom",vP.getSignupSizeEmail());
		}
		if (userService.findByUsername(user.getEmail()) != null){
			errors.rejectValue("email", "custom",vP.getSignupDuplicateEmail());
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required", vP.getSignupRequired());
		if (user.getPassword()!=null && (user.getPassword().length()<8 ||user.getPassword().length()>48)){
			errors.rejectValue("password", "custom", vP.getSignupSizePassword());
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "required", vP.getSignupRequired());
		if(user.getConfirmPassword()!=null && !user.getConfirmPassword().equals(user.getPassword())){
			System.out.println("ConfirmPassword: " + user.getConfirmPassword());
			System.out.println("Password: " + user.getPassword());
			errors.rejectValue("confirmPassword","custom",vP.getSignupMismatchPassword());
		}
		
	}

}
