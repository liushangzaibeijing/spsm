package com.xiu.web.handlerAdapter;

import com.xiu.entity.Student;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 实现自定义的ReponseBodyAdvice接口并配合ControllerAdvice注解（所有处理器都适用）
 * 用于将所有处理器中返回值为json格式数据响应之前对其进行修改
 */
@ControllerAdvice
public class StudentReponseBodyAdvice implements ResponseBodyAdvice {
    /**
     * 在请求被对应的HandlerMethod执行前，调用该方法进行相关的一些初始化的操作 全局
     * @param binder
     */
    @InitBinder
    private void registerDataFormat(WebDataBinder binder) {
        //注册属性编辑器
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
    //其中方法的返回值作为value 设置到model中
    @ModelAttribute("age")
    public String setName(){
        return "26";
    }
    //ModelAttribute注解的第二种方式 方法中含有model 则直接设置到model对象中
    @ModelAttribute()
    public void setModel(Model model){
        Student student = new Student();
        student.setName("刘亦菲");
        student.setAge(26);
        model.addAttribute("student",student);
    }

        /**
         * 用于判断该Advice 是否其作用
         * @param returnType 对应的方法包含其参数对象
         * @param converterType 类型转换对象  .MappingJackson2HttpMessageConverter
         * @return
         */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType selectedContentType, Class selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {

        System.out.println("TestResponseBodyAdvice==>beforeBodyWrite:" + body.toString() + ","
                + returnType);

        //转换数据
        if(body instanceof Student){
            Student student = (Student) body;
            student.setName("文斯莫克·山治");
            return student;
        }
        return body;
    }
}
