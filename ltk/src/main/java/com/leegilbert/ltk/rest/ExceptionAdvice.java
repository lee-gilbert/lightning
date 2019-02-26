package com.leegilbert.ltk.rest;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

// spring v4 way, see https://stackoverflow.com/questions/19498378/setting-precedence-of-multiple-controlleradvice-exceptionhandlers/19500823#19500823
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(value="com.leegilbert.ltk.rest")
@ResponseBody
public class ExceptionAdvice {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Void> handleArgumentNotValid(MethodArgumentNotValidException exception) {
        String errors = exception.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(","));
        return new ApiResponse(HttpStatus.BAD_REQUEST.value(), errors, null);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Void> handleConstraintViolation(ConstraintViolationException exception) {
         String errors = exception.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(","));
        return new ApiResponse(HttpStatus.BAD_REQUEST.value(), errors, null);
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({DataIntegrityViolationException.class, PersistenceException.class, RuntimeException.class})
    public ApiResponse<Void> handleNotFoundException(Throwable ex) {
        return new ApiResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), null);
    }
}
