package com.png.web.controller.api;

import com.png.data.dto.user.UserContext;
import com.png.data.entity.User;
import com.png.data.responses.ApiResponse;
import com.png.exception.*;
import com.png.menu.Menu;
import com.png.util.DateFormatter;
import com.png.util.StringGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/user")
public class UserServiceController extends ApiBase {

    @RequestMapping(value = "/sign_up", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> signup(@RequestBody User user, BindingResult bindingResult,
                                         HttpServletRequest request) throws ValidationException {
        ApiResponse apiResponse = new ApiResponse();
        ArrayList<HashMap<String, String>> errorList = new ArrayList<HashMap<String, String>>();
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            for (FieldError error : bindingResult.getFieldErrors()) {
                HashMap<String, String> errors = new HashMap<String, String>();
                errors.put("fieldName", error.getField());
                errors.put("errorType", error.getCode());
                errors.put("message", error.getDefaultMessage());
                errorList.add(errors);
            }
            apiResponse.setValidationErrors(errorList);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
        UserContext userContext;
        try {
            user = userService.save(user);
			/*userDetailsService.setUser(user);
			userContext = userDetailsService.getUserContext();*/
            emailService.sendValidationEmailAsync(StringGenerator.getBaseUrl(request),
                    user.getFirstName(),
                    user.getEmail(),
                    user.getEmailValidUptoTimestamp(),
                    user.getEmailValidationCode());
            //apiResponse.setResponseData(userContext);
            apiResponse.setMessage(String.format(
                    "Hi <b>%s!</b>, Thank you for signing up with us.<br>" +
                            "An email has been sent to <b>%s</b>, please click the link to complete sign up before " +
                            "logging in. The link will be valid till the next hour.<br>" +
                            "See you soon!", user.getFirstName(), user.getEmail()));
        } catch (Exception e) {
            HashMap<String, String> errorDetails = new HashMap<String, String>();
            errorDetails.put("type", e.getClass().getSimpleName());
            errorDetails.put("message", e.getMessage());
            apiResponse.setApiError(errorDetails);
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/user_login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> userLogin(@RequestBody Map<String, String> payload) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            UserContext userContext = securityService.login(payload.get("email"), payload.get("password"));
            apiResponse.setResponseData(userContext);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (BaseException e) {
            apiResponse.setApiError(populateErrorDetails(e));
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value = "/forgot_password", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> forgotPassword(@RequestBody Map<String, String> payload,
                                                 HttpServletRequest request) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            User user = userService.forgotPassword(payload.get("email"));
            emailService.sendForgotPasswordEmailAsync(StringGenerator.getBaseUrl(request), user.getFirstName(), user.getEmail(),
                    user.getPasswordLinkValidUptoTimestamp(), user.getForgotPasswordCode());
            apiResponse.setMessage(String.format("An email has been sent to %s, click the link in the email"
                    + " to reset your password. The link will be valid for an hour. Happy Booking!", user.getEmail()));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (BaseException e) {
            apiResponse.setApiError(populateErrorDetails(e));
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value = "/reset_password", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> resetPassword(@RequestBody Map<String, String> payload) {
        String resetCode = payload.get("resetCode");
        String newPassword = payload.get("newPassword");
        System.out.println("Password Reset Code: " + resetCode);
        System.out.println("New Password: " + newPassword);
        try {
            User user = userService.resetPassword(resetCode, newPassword);
            if (user == null)
                throw new ApiBusinessException("1000", "User name not found, please use the email address " +
                        "originally used to sign up");
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setMessage(String.format("Password has been changed for %s . Please login using your " +
                    "new password.", user.getEmail()));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (BaseException e) {
            return new ResponseEntity<>(populateErrorDetails(e), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/change_password", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> changePassword(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        String newPassword = payload.get("newPassword");
        String oldPassword = payload.get("oldPassword");
        System.out.println("Email: " + email);
        System.out.println("New Password: " + newPassword);
        System.out.println("Old Password: " + oldPassword);

        try {
            User user = userService.findByUsername(email);
            if (user == null)
                throw new ApiBusinessException("1000", "User name not found, please use the email address " +
                        "originally used to sign up");
            if (user.getPassword().equals(bCryptPasswordEncoder.encode(oldPassword))) {
                userService.changePassword(user, newPassword);
            } else {
                throw new ApiBusinessException("1000", "Existing password does not match, please retry.");
            }

            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (BaseException e) {
            return new ResponseEntity<>(populateErrorDetails(e), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/resend_verification_email", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> resendVerificationEmail(@RequestBody Map<String, String> payload,
                                                          HttpServletRequest request) {
        System.out.println("User Name: " + payload.get("email"));
        try {
            User user = userService.sendEmailValidationCode(payload.get("email"));
            emailService.sendValidationEmailAsync(StringGenerator.getBaseUrl(request),
                    user.getFirstName(),
                    user.getEmail(),
                    user.getEmailValidUptoTimestamp(),
                    user.getEmailValidationCode());
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (BaseException e) {
            return new ResponseEntity<>(populateErrorDetails(e), HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value = "/user_menu", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> userMenu(@RequestBody UserContext userContext) {
        ArrayList<HashMap<String, String>> errorList = new ArrayList<HashMap<String, String>>();
        try {
            Collection<Menu> menuToShow = userDetailsService.getUserMenu(userContext.getIdUser());
            return new ResponseEntity<Object>(menuToShow, HttpStatus.OK);
        } catch (Exception e) {
            HashMap<String, String> errors = new HashMap<String, String>();
            errors.put("type", e.getClass().getSimpleName());
            errors.put("message", e.getMessage());
            errorList.add(errors);
            return new ResponseEntity<Object>(errorList, HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value = "/user_logoff", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> userLogoff(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return new ResponseEntity<Object>("", HttpStatus.OK);
    }


    @RequestMapping(value = "/verify_email/{verificationCode}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> verifyEmail(@PathVariable String verificationCode) {
        try {
            User user = userService.findByEmailValidationCode(verificationCode);
            if (user == null)
                throw new InvalidEmailVerificationCodeException("1003", "Invalid Email Verification Code");
            if (new Timestamp(new java.util.Date().getTime()).after(user.getEmailValidUptoTimestamp()))
                throw new EmailVerificationExpiredException("1002",
                        String.format("Email verification code expired on %s",
                                DateFormatter.getDateStringFromTimestamp(user.getEmailValidUptoTimestamp())));
            userService.resetEmailValidationCode(verificationCode);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (BaseException e) {
            return new ResponseEntity<>(populateErrorDetails(e), HttpStatus.BAD_REQUEST);
        }

    }
}
