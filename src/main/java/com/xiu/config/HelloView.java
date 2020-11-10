package com.xiu.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * @ClassName Hello
 * @Desc 自定义的View对象
 * @Author xieqx
 * @Date 2020/11/9 17:19
 **/
public class HelloView implements View {
    /**
     * 该视图的media类型
     * @return
     */
    @Override
    public String getContentType() {
        return "text/html";
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.getWriter().print("Welcome to hello View:"+new Date());
    }
}
