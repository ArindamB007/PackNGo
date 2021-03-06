package com.png.exception;

import com.png.data.dto.user.UserContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "User name not found.")
@ResponseBody
public class UsernameNotFoundException extends BaseException{
    private static final long serialVersionUID = 5058963151246794522L;
    public UsernameNotFoundException(String errorCode, String errorMessage){
        setErrorCode(errorCode);
        setErrorMessage(errorMessage);
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
