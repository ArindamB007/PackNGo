package com.png.auth.service;

import com.png.data.entity.User;

public interface UserService {
    User save(User user);

    User findByUsername(String email);
    User findByMobile(String mobile);
    User findByEmailValidationCode(String validationCode);

    User findByForgotPasswordCode(String forgotPasswordCode);

    User sendEmailValidationCode(String email);
    User resetEmailValidationCode(String emailValidationCode);

    User forgotPassword(String email);

    User resetPassword(String passwordCode, String newPassword);

    void changePassword(User user, String newPassword);
}
