package com.png.exception.handler;

import com.png.exception.BaseException;
import com.png.exception.EmailNotVerifiedException;
import com.png.exception.NoDataException;
import com.png.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RestController
public class GlobalPngExceptionHandler {
    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public ResponseEntity<?> handleBaseException(ValidationException e){
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }
    @ResponseBody
    public ResponseEntity<?> handleBaseException(EmailNotVerifiedException e){
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }
    @ResponseBody
    public ResponseEntity<?> handleBaseException(NoDataException e){
        return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
    }
    
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) 
    @ExceptionHandler(value = Exception.class)  
    public ResponseEntity<?> handleException(Exception e){
		return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		} 
}
