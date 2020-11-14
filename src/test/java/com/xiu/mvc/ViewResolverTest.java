package com.xiu.mvc;

import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.*;

import java.util.Locale;


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
