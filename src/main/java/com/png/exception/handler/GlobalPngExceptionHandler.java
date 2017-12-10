package com.png.exception.handler;

import com.png.exception.BaseException;
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
    
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) 
    @ExceptionHandler(value = Exception.class)  
    public ResponseEntity<?> handleException(Exception e){
		return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		} 
}
