package com.xiu.HandlerAdapter;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.servlet.http.HttpServletRequest;

public class MyRequestHandlerMappingAdapter extends RequestMappingHandlerAdapter {
    @Override
    protected long getLastModifiedInternal(HttpServletRequest request, HandlerMethod handlerMethod) {
        return 100L;
    }
}
