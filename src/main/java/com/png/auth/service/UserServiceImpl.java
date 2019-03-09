package com.png.auth.service;

import com.png.data.entity.User;
import com.png.data.repository.RoleRepository;
import com.png.data.repository.UserRepository;
import com.png.exception.ApiBusinessException;
import com.png.exception.EmailVerificationExpiredException;
import com.png.util.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Override
	public User save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		//set email validation related data for first time login
		user.setEmailValidationCode(user.generateUUIDValidationCode());
		//add 1 hour to the current time
		user.setEmailValidUptoTimestamp(new Timestamp(new java.util.Date().getTime() + (60*60)*1000L));
		user.setEmailSentTimestamp(new Timestamp(new java.util.Date().getTime()));
		//Assign only user role to the newly signed up user
		user.setRoles(new HashSet<>(roleRepository.findByName("ROLE_USER")));
		user.setCreatedTimestamp(new Timestamp(new java.util.Date().getTime()));
		user.setUpdatedTimestamp(new Timestamp(new java.util.Date().getTime()));
		return userRepository.save(user);
	}
	@Override
	public User findByUsername(String email) {
		return userRepository.findByEmail(email);
	}

	public User findByMobile(String mobileNo){return userRepository.findByMobile(mobileNo); }
	@Override
	public User findByEmailValidationCode(String emailValidationCode){
		return userRepository.findByEmailValidationCode(emailValidationCode);
	}

	@Override
	public User findByForgotPasswordCode(String forgotPasswordCode) {
		return userRepository.findByForgotPasswordCode(forgotPasswordCode);
	}
	@Override
	public User resetEmailValidationCode(String emailValidationCode){
		User user = userRepository.findByEmailValidationCode(emailValidationCode);
		user.setEmailValidated(true);
		user.setEmailValidationCode(null);
		user.setEmailValidUptoTimestamp(null);
		userRepository.save(user);
		return user;
	}
	@Override
	public User sendEmailValidationCode(String email) {
		User user = userRepository.findByEmail(email);
		//set email validation related data
		user.setEmailValidationCode(user.generateUUIDValidationCode());
		//add 1 hour to the current time
		user.setEmailValidUptoTimestamp(new Timestamp(new java.util.Date().getTime() + (60*60)*1000L));
		user.setEmailSentTimestamp(new Timestamp(new java.util.Date().getTime()));
		userRepository.save(user);
		return user;
	}

	@Override
	public User forgotPassword(String email) {
		User user = userRepository.findByEmail(email);
		if (user == null)
			throw new ApiBusinessException("1000", String.format("We searched all places :(. But, were unable to find your email " +
							"address(%s). Please use the email address that was used to sign up to our services. Happy Booking!",
					email));
		user.setPasswordLinkSentTimestamp(new Timestamp(new java.util.Date().getTime()));
		user.setForgotPasswordCode(user.generateUUIDValidationCode());
		//add 1 hour to the current time
		user.setPasswordLinkValidUptoTimestamp(new Timestamp(new java.util.Date().getTime() + (60 * 60) * 1000L));
		userRepository.save(user);
		return user;
	}

	@Override
	public User resetPassword(String passwordCode, String newPassword) {
		User user = userRepository.findByForgotPasswordCode(passwordCode);
		if (user == null)
			throw new ApiBusinessException("1000", "We searched all places :(. But, were unable to find " +
					"your password reset request");
		if (new Timestamp(new java.util.Date().getTime()).after(user.getPasswordLinkValidUptoTimestamp()))
			throw new EmailVerificationExpiredException("1000", "Reset password code is expired on. Please send a new " +
					"request for your register email address.");
		user.setPasswordLinkSentTimestamp(null);
		user.setForgotPasswordCode(null);
		user.setPasswordLinkValidUptoTimestamp(null);
		// change the password to the new one
		user.setPassword(bCryptPasswordEncoder.encode(newPassword));
		userRepository.save(user);
		return user;
	}

	@Override
	public void changePassword(User user, String newPassword) {
		user.setPassword(bCryptPasswordEncoder.encode(newPassword));
		userRepository.save(user);
	}
	
	

}
