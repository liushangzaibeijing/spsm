//package com.xiu.mvc.transaction;
//
//import com.xiu.entity.Book;
//import com.xiu.mvc.BaseTest;
//import com.xiu.service.BookService;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.servlet.HandlerMapping;
//
//public class TxTest extends BaseTest {
//
//    @Autowired
//    private BookService bookService;
//
//    @Test
//    public void testTx(){
//        Book book = new Book();
//        bookService.saveBook(book);
//    }
//
//
//    @Test
//    public void testXmlTx() throws Exception {
//        Book book = bookService.getById(1517);
//
//        System.out.println(book);
//    }
//}
//
