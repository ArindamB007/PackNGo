package com.png.auth.service;

import com.png.data.entity.User;
import com.png.data.repository.RoleRepository;
import com.png.data.repository.UserRepository;
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
	public void save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		//set email validation related data
		user.setEmailSentTimestamp(new Timestamp(new java.util.Date().getTime()));
		user.setEmailValidationCode(user.generateEmailValidationCode());
		//add 1 hour to the current time
		user.setEmailValidUptoTimestamp(new Timestamp(new java.util.Date().getTime() + (60*60)*1000L));
		user.setEmailSentTimestamp(new Timestamp(new java.util.Date().getTime()));
		//Assign only user role to the newly signed up user
		user.setRoles(new HashSet<>(roleRepository.findByName("ROLE_USER")));
		user.setCreatedTimestamp(new Timestamp(new java.util.Date().getTime()));
		user.setUpdatedTimestamp(new Timestamp(new java.util.Date().getTime()));
		userRepository.save(user);
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
	public User resetEmailValidationCode(String emailValidationCode){
		User user = userRepository.findByEmailValidationCode(emailValidationCode);
		user.setEmailValidated(true);
		user.setEmailValidationCode(null);
		user.setEmailValidUptoTimestamp(null);
		userRepository.save(user);
		return user;
	}
	@Override
	public User resendEmailValidationCode(String email){
		User user = userRepository.findByEmail(email);
		//set email validation related data
		user.setEmailSentTimestamp(new Timestamp(new java.util.Date().getTime()));
		user.setEmailValidationCode(user.generateEmailValidationCode());
		//add 1 hour to the current time
		user.setEmailValidUptoTimestamp(new Timestamp(new java.util.Date().getTime() + (60*60)*1000L));
		user.setEmailSentTimestamp(new Timestamp(new java.util.Date().getTime()));
		userRepository.save(user);
		return user;
	}
	
	

}
