package com.xiu.web.handlerAdapter;

import com.xiu.entity.Student;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * 自定义校验器
 */
@Component
public class StudentValidator implements Validator {
    /**
     * 该校验器只支持参数为Student类
     * @param clazz
     * @return
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return Student.class.equals(clazz);
    }

    /**
     * 具体的校验逻辑
     */
    @Override
    public void validate(Object target, Errors errors) {
         Student student = (Student) target;
         int age = student.getAge();
         if(age>20){
             errors.rejectValue("age","这个年纪，自律是一种风骨");
         }
    }
}
