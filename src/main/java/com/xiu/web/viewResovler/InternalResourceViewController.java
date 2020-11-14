package com.xiu.web.viewResovler;

import com.xiu.entity.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName ContentNegotiatingViewController
 * @Desc 根据请求类型的不同渲染页面
 * @Author xieqx
 * @Date 2020/11/9 17:09
 **/
@Controller
@RequestMapping(value = "/internalResourceView")
public class InternalResourceViewController {
    /**
     * 使用InternaleResourceViewResolver进行jsp页面的渲染
     * @return 进入jsp页面
     */
    @RequestMapping(value="/jsp")
    public ModelAndView jspPage() {
       ModelAndView mv = new ModelAndView();
       mv.addObject("message","welcome jsp page");
       mv.setViewName("jspIndex");
       return mv;
    }
    /**
     * 使用FreeMarkerViewResolver进行freeMarker页面的渲染
     * @return 进入freemarker页面
     */
    @RequestMapping(value="/freeMarker")
    public ModelAndView freeMarkerPage() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("message","welcome freeMarker page");
        mv.setViewName("freeMarkerIndex");
        return mv;
    }

    /**
     * 请求转发
     * @param modelMap
     * @return
     */
    @RequestMapping(value="/forward")
    public String forward(ModelMap modelMap) {
        modelMap.put("message","welcome jsp page");
        return "forward:jsp";
    }
}
