package com.xiu.mvc;

import org.junit.Test;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;


/**
 * spring视图解析器
 */
public class ViewResolverTest {
    /**
     * 视图解析器架构逻辑
     */
    @Test
    public void viewResolverStructure(){
        ViewResolver beanNameViewResolver = new BeanNameViewResolver();

        ViewResolver  viewResolver = new ContentNegotiatingViewResolver();
    }
}
