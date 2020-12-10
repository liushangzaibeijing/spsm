import com.xiu.mvc.BaseTest;
import org.junit.Test;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.context.request.async.*;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Callable;

public class BuninseeServiceTest extends BaseTest {
   //Servlet异步请求
   //web.xml配置开启支持异步请求
   //request开启异步请求 返回AsyncContext(异步上下文)
    public void testAsyncServlet(){
//        HttpServletRequest request;
//        AsyncContext asyncContext = request.startAsync();
    }



    public void testAsyncSpringMVCServlet(){
        AsyncWebRequest request = null;
        WebAsyncManager webAsyncManager = null;
        WebAsyncUtils webAsyncUtils = null;

        //spring异步请求分成四部分
//        1、Callable
//        2、WebAsyncTask
//        3、DeferredResult
//        4、ListenableFuture
    }

}


