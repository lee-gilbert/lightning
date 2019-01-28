package com.leeGilbert.ltk.rest;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Component
public class MyCustomErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        return super.getErrorAttributes(webRequest, false);
//        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
//        errorAttributes.put("locale", webRequest.getLocale().toString());
//        errorAttributes.remove("error");
//        return errorAttributes;
    }
}

