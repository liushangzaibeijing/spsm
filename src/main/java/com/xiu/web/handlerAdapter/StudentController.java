package com.xiu.web.handlerAdapter;

import com.xiu.entity.Student;
import com.xiu.utils.JsonUtil;
import com.xiu.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.handler.AbstractHandlerMethodMapping;
import org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.dyuproject.protostuff.MapSchema.MessageFactories.ConcurrentHashMap;
import static com.dyuproject.protostuff.MapSchema.MessageFactories.HashMap;

@Controller
@RequestMapping("/student/*")
@SessionAttributes("dream")
public class StudentController extends BaseController {

    @Autowired
    private StudentValidator studentValidator;
    /**
     * 在请求被对应的HandlerMethod执行前，调用该方法进行相关的一些初始化的操作
     * @param binder
     */
    @InitBinder
    private void registerDataFormat(WebDataBinder binder){
        //注册属性编辑器
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat,false));
        //注册校验器
        binder.addValidators(studentValidator);

   }

    @ResponseBody
    @RequestMapping(value = "addByForm",method = RequestMethod.POST,
            consumes = "application/x-www-form-urlencoded",produces = "application/json")
    public String addStudentByForm(Student student){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info("出生日期：{}", dateFormat.format(student.getBrithday()));
       ;
        logger.info("学生信息：{}",JsonUtil.obj2str(student));
        return success();
    }

    @ResponseBody
    @RequestMapping(value = "addByJson",method = RequestMethod.POST)
    public String addStudentByJson(@RequestBody  Student student){

        logger.info("学生信息：{}",JsonUtil.obj2str(student));
        return success();
    }


    //ModelAttribute注解的第一种方式 使用其中的value属性作为key
    //其中方法的返回值作为value 设置到model中
    @ModelAttribute("name")
    public String setName(){
        return "索隆";
    }
    //ModelAttribute注解的第二种方式 方法中含有model 则直接设置到model对象中
    @ModelAttribute()
    public void setModel(Model model){
        Student student = new Student();
        student.setName("路飞");
        student.setAge(24);
        model.addAttribute("student",student);
    }
    //ModelAttribute注解的第三种方式
    //注解即没有value的属性值作为key,也没有model对象
    //这会通过ModleFactory根据返回类型解析出参数名作为key,并将返回对象作为value存放到model中
    //TODO 这里没有起作用
    @ModelAttribute
    public Student setModelTwo(){
        Student studentTWO = new Student();
        studentTWO.setName("山治");
        studentTWO.setAge(24);
        return studentTWO;
    }
    //这里解析参数名为string
    @ModelAttribute
    public String  setModelThree(){
        return "山治";
    }
    //在model中进行了设置可以在该方法中进行获取
    @RequestMapping(value = "testModelAttribute",method = RequestMethod.GET)
    public String testModelAttribute(Model model,HttpSession session){
       Map param  = model.asMap();
       logger.info("获取modelAttribute第一种方式设置的参数:{}",param.get("name"));
       logger.info("获取modelAttribute第二种方式设置的参数:{}",((Student)param.get("student")).getName());
       logger.info("获取modelAttribute第三种方式设置的参数:{}",param.get("name"));
       String dream = (String) session.getAttribute("dream");
       logger.info(dream);
       return "/demo";
    }


    //ResponseBodyAdvice接口 该接口可以处理返回值为json格式的数据 在该数据返回之前对数据进行处理
    @ResponseBody
    @RequestMapping(value = "query/{id}",method = RequestMethod.GET)
    public Student queryStudent(@PathVariable("id") Integer id){
        Student student = new Student();

        student.setName("山治");
        student.setAge(24);
        return student;
    }

    /**
     * ModelAttribute注解作用在方法上或者方法的参数上，
     * 表示将被注解的方法的返回值或者是被注解的参数作为Model的属性加入到Model中，
     * 然后Spring框架自会将这个Model传递给ViewResolver。Model的生命周期只有一个http请求的处理过程，
     * 请求处理完后，Model就销毁了。
     *如果想让参数在多个请求间共享，那么可以用到要说到的@SessionAttribute注解
     * SessionAttribute只能作用在类上
     */
    @RequestMapping(value = "testSetSessionAttr",method = RequestMethod.GET)
    @ResponseBody
    public String  testSessionAttribute(Model model){
        model.addAttribute("dream","one picec");
        return "sucesss";
    }
    @RequestMapping(value = "testSessionAttr",method = RequestMethod.GET)
    public void testSessionAttribute(HttpSession session){
        String dream = (String) session.getAttribute("dream");
        logger.info(dream);
    }
}
