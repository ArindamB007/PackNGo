package com.png.auth.service;

import com.png.data.entity.User;

public interface UserService {
	void save(User user);

    User findByUsername(String email);
    User findByEmailValidationCode(String validationCode);
    User resendEmailValidationCode(String email);
    User resetEmailValidationCode(String emailValidationCode);
}
