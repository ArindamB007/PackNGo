package com.png.auth.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.png.data.entity.UserContext;

public interface SecurityService {
	 String findLoggedInUsername();
	 void autologin(String email, String password);
	 UserDetails userLogin(String email,String password);
	 UserContext login (String email, String password);
}
