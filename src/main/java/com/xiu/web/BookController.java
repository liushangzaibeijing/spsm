package com.xiu.web;

import com.github.pagehelper.PageInfo;
import com.xiu.entity.Book;
import com.xiu.service.BookService;
import com.xiu.utils.JsonUtil;
import com.xiu.vo.BookQuery;
import com.xiu.vo.BookVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.handler.AbstractHandlerMethodMapping;

/**
 * Created by zhangwb on 2018/5/14.
 */
@Controller
@RequestMapping("/book/*")
public class BookController extends BaseController {

    @Autowired
    BookService bookService;



    @RequestMapping(value = "/book/ListPage",method = RequestMethod.POST)
    @ResponseBody
    public String getBookPage(@RequestBody BookQuery bookQuery){
        Integer pageNum = (Integer) bookQuery.getPageNum();
        Integer pageSize = (Integer) bookQuery.getPageSize();
        logger.info("当前查询的当前页码：{}",pageNum);
        Book book = new Book();
        BeanUtils.copyProperties(bookQuery,book);
        PageInfo<BookVo> pageInfo =bookService.queryBookListWithPage(book,pageNum,pageSize);
        logger.info("查询出来的书籍信息为：{}", JsonUtil.obj2str(pageInfo.getList()));
        return success(pageTotal(pageInfo));
    }



    @RequestMapping(value = "/book/Detail",method = RequestMethod.POST)
    @ResponseBody
    public String getBookDetail(@RequestBody Integer id){
       logger.info("查询书籍详情开始");
        BookVo bookVo =bookService.queryBookbyId(id);

        if(bookVo==null){
            failure(404,"没有查询到对应的书籍信息");
        }
        logger.info("查询出来的id 为{}书籍信息为：{}",id, JsonUtil.obj2str(bookVo));

        return success(JsonUtil.obj2str(bookVo));
    }


    @RequestMapping(value = "/book/{id}",method = RequestMethod.GET)
    public String getBookById(@PathVariable("id") Integer id){
            BookVo bookVo =bookService.queryBookbyId(id);

        if(bookVo==null){
            failure(404,"没有查询到对应的书籍信息");
        }
        logger.info("查询出来的id 为{}书籍信息为：{}",id, JsonUtil.obj2str(bookVo));

        return success(JsonUtil.obj2str(bookVo));
    }
}
