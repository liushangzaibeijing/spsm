package com.xiu.web.viewResovler;

import com.xiu.entity.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

/**
 * @ClassName ContentNegotiatingViewController
 * @Desc 根据请求类型的不同渲染页面
 * @Author xieqx
 * @Date 2020/11/9 17:09
 **/
@Controller
@RequestMapping(value = "/contentNegotiatingView")
public class ContentNegotiatingViewController {

    @RequestMapping(value="/book/{id}")
    public Book queryUser(@PathVariable("id") long id, ModelMap model) {
        Book book = new Book();
        book.setAuthor("陈忠实");
        book.setId(1111);
        book.setName("白鹿原");
        book.setPublisHouse("中信出版社");
        return book;

    }
}
