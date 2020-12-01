package com.xiu.exception.config;

import com.xiu.exception.AServiceException;
import com.xiu.exception.BServiceException;
import com.xiu.exception.CServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName ServiceExceptionHandlerAdvice
 * @Desc springMVC全局异常处理器
 * @Author xieqx
 * @Date 2020/11/28 9:57
 * 定义了三个方法分别处理AServiceException、BServiceException、CServiceException
 **/
@ControllerAdvice
public class ServiceExceptionHandlerAdvice {
    //不使用注解则返回的是对应的视图名
    @ExceptionHandler()
    //@ResponseBody
    String handleAServiceException(AServiceException e){
        //return "AServiceException Deal! 异常码："+e.getCode()+"异常信息：" + e.getMsg();
        return "/error/index";
    }
    @ExceptionHandler()
    @ResponseBody
    String handleBServiceException(BServiceException e){
        return "BServiceException Deal! 异常码："+e.getCode()+"异常信息：" + e.getMsg();
    }

    @ExceptionHandler()
    @ResponseBody
    String handleCServiceException(CServiceException e){
        return "CServiceException Deal!  异常码："+e.getCode()+"异常信息：" + e.getMsg();
    }
}
