package com.anleus.authservice.config;

import com.anleus.authservice.dto.ErrorDto;
import com.anleus.authservice.exceptions.AuthException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {AuthException.class})
    @ResponseBody
    public ResponseEntity<ErrorDto> handleException(AuthException ex) {
        return ResponseEntity.status(ex.getHttpStatus())
                .body(new ErrorDto(ex.getMessage()));
    }
}
