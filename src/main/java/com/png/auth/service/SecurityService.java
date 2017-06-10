package com.png.auth.service;

public interface SecurityService {
	 String findLoggedInUsername();
	 void autologin(String email, String password);
}
