package com.png.auth.service;

import com.png.services.CustomUserDetailsService;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.png.data.entity.UserContext;

@Service
public class SecurityServiceImpl implements SecurityService{
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

	@Override
	public String findLoggedInUsername() {
		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
		if (userDetails instanceof UserDetails){
			return ((UserDetails) userDetails).getUsername();
		}
		return null;
	}

	@Override
	public void autologin(String email, String password) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(email);
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
				new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities()); 
		authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		
		if (usernamePasswordAuthenticationToken.isAuthenticated()){
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			logger.debug(String.format("User %s auto logged in successfully!", email));
		}
	}
	
	@Override
	public UserDetails userLogin(String email, String password){
		UserDetails userDetails = null;
		userDetails = userDetailsService.loadUserByUsername(email);
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
				new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
		authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		if (usernamePasswordAuthenticationToken.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			System.out.println(String.format("User %s logged in successfully!", email));
		}
		return userDetails;
	}
	
	@Override
	public UserContext login(String email, String password){
		UserDetails userDetails = null;
		UserContext userContext = null;
		userDetails = userDetailsService.loadUserByUsername(email);
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
				new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
		authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		if (usernamePasswordAuthenticationToken.isAuthenticated()) {
			userDetailsService.updateLastLoginTimeStamp(new Timestamp(new java.util.Date().getTime()));
			userDetailsService.saveUser();
			userContext = userDetailsService.getUserContext();
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			System.out.println(String.format("User %s logged in successfully!", email));
		}
		return userContext;
	}
	
}
