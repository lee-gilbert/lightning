package com.leeGilbert.ltk.rest;

import org.hibernate.JDBCException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

// spring v4 way
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(value="com.leeGilbert.ltk.rest")
public class ExceptionAdvice {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({DataIntegrityViolationException.class, PersistenceException.class, RuntimeException.class})
    public ApiResponse<Void> handleNotFoundException(Throwable ex) {
        return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null);
    }
}
