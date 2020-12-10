package com.xiu.async.servlet;

import com.xiu.async.WeekTask;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * @ClassName AsyncServlet
 * @Desc servlet
 * @Author xieqx
 * @Date 2020/12/9 15:38
 **/
public class AsyncServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //开启异步支持
        //异步管理上下文
        resp.setCharacterEncoding("GBK");
        PrintWriter writer = resp.getWriter();
        writer.println("周工作任务布置开始");
        AsyncContext asyncContext = req.startAsync();
        asyncContext.start(new WeekTask(asyncContext));
        //添加监听器
        asyncContext.addListener(new AsyncListener() {
            @Override
            public void onComplete(AsyncEvent asyncEvent) throws IOException {
                System.out.println("工作在"+new Date()+"处理完成");
            }

            @Override
            public void onTimeout(AsyncEvent asyncEvent) throws IOException {
                System.out.println("工作在"+new Date()+"处理超时");
            }

            @Override
            public void onError(AsyncEvent asyncEvent) throws IOException {
                System.out.println("工作在"+new Date()+"处理出错");
            }

            @Override
            public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
                System.out.println("工作在"+new Date()+"处理开始");
            }
        });

        writer.println("周工作任务布置完成");
        writer.flush();
    }

}
