package com.example.errordemo;

import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestControllerAdvice
public class CustomExceptionHandler {

    private final static Logger logger = Logger.getLogger(CustomExceptionHandler.class.getName());

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleException(Exception e) {
        logger.log(Level.SEVERE, "an exception was thrown", e);
        return Map.of("message", "something bad happened");
    }

}
