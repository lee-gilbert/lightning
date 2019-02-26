//package com.leegilbert.ltk.rest;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.server.WebExceptionHandler;
//import reactor.core.publisher.Mono;
//
//
//// Spring 5 reactive way
//@Component
//@Order(-2)
//@Slf4j
//public class RestExceptionHandler implements WebExceptionHandler {
//
//    private ObjectMapper objectMapper;
//
//    public RestExceptionHandler(ObjectMapper objectMapper) {
//        this.objectMapper = objectMapper;
//    }
//
//    @Override
//    public ApiResponse<Void> handle(ServerWebExchange exchange, Throwable ex) {
//        return new ApiResponse(500, ex.getMessage(), null);
//    }
//
//}
