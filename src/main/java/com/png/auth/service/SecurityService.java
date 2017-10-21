package com.png.auth.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.png.data.domain.UserContext;

public interface SecurityService {
	 String findLoggedInUsername();
	 void autologin(String email, String password);
	 UserDetails userLogin(String email,String password);
	 UserContext login (String email, String password);
	 void logoff(String email);
}
