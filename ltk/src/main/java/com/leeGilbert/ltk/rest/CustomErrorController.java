//package com.leeGilbert.ltk.rest;
//
//import org.springframework.boot.autoconfigure.web.ServerProperties;
//import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
//import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//
//import javax.servlet.http.HttpServletRequest;
//
//@Controller
//public class CustomErrorController extends BasicErrorController {
//
//    public CustomErrorController(ServerProperties serverProperties) {
//        super(new DefaultErrorAttributes(), serverProperties.getError());
//    }
//
//    @Override
//    public ResponseEntity error(HttpServletRequest request) {
//        HttpStatus status = getStatus(request);
//        if (status.equals(HttpStatus.INTERNAL_SERVER_ERROR)){
//            return ResponseEntity.status(status).body(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", null));
//        }else if (status.equals(HttpStatus.BAD_REQUEST)){
//            return ResponseEntity.status(status).body(new ApiResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request", null));
//        }
//        return super.error(request);
//    }
//}


