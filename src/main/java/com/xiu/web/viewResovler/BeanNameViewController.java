package com.xiu.web.viewResovler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName BeanNameViewController
 * @Desc 使用BeanNameViewResolver渲染视图
 * @Author xieqx
 * @Date 2020/11/9 17:09
 **/
@Controller
@RequestMapping(value = "/beanNameView")
public class BeanNameViewController {

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String toHello(HttpServletRequest request){
        System.out.println("使用BeanNameViewResolver解析器 获取解析视图");
        //返回时String 类型的View 对象
        return "helloView";
    }

    @RequestMapping(value = "jstlView", method = RequestMethod.GET)
    public String toJstlView(HttpServletRequest request){
        System.out.println("使用BeanNameViewResolver解析器 获取解析视图");
        return "jstlView";
    }
}
