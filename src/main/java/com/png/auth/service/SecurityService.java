package com.png.auth.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface SecurityService {
	 String findLoggedInUsername();
	 void autologin(String email, String password);
	 UserDetails userLogin(String email,String password);
}
