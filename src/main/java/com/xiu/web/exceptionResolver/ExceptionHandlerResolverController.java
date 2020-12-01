package com.xiu.web.exceptionResolver;

import com.xiu.entity.Book;
import com.xiu.exception.AServiceException;
import com.xiu.exception.BServiceException;
import com.xiu.exception.CServiceException;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.context.config.ContextNamespaceHandler;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.MvcNamespaceHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ExceptionHandlerResolverController
 * @Desc 用于验证处理异常的web接口
 * @Author xieqx
 * @Date 2020/11/24 11:09
 **/
@Controller
@RequestMapping(value = "/exception")
public class ExceptionHandlerResolverController {

    @RequestMapping(value = "hello", method = RequestMethod.POST)
    @ResponseBody
    public String toHello(HttpServletRequest request){
        System.out.println("该方法是get类型的方法");
        //返回时String 类型的View 对象
        return "helloView";
    }

    //异常测试 需要注释或者放开指定的代码片段
    @RequestMapping(value = "rederException", method = RequestMethod.GET)
    @ResponseBody
    public String rederException(String type) throws  FileNotFoundException,AServiceException, BServiceException, CServiceException {
        //1、测试空指针异常
//        String nullStr = null;
//        nullStr.split(",");
        //2、测试ClassCastException 类型转换异常
//        nullStr = "13213";
//        Map data = new HashMap();
//        data.put("key",nullStr);
//        Integer num = (Integer)data.get("key");
        //测试IO异常
//        File file = new File("D:\\doc\\需求开发\\信审新增相关字段\\工作.txt");
//        FileInputStream in = new FileInputStream(file);
        //测试异常的模糊匹配
        if(type.equals("A")){
           throw new AServiceException("512","服务器内部错误");
       }
        if(type.equals("B")){
            throw new BServiceException("513","服务器无响应");
        }
        if(type.equals("C")){
            throw new CServiceException("514","服务器不正常");
        }
        return "helloView";
    }

    @RequestMapping(value = "charSet", method = RequestMethod.GET)
    @ResponseBody
    public String charSet() {
        return  "我的团长我的团";
    }
}