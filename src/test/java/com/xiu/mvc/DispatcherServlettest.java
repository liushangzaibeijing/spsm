package com.xiu.mvc;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.async.WebAsyncManager;
import org.springframework.web.context.request.async.WebAsyncUtils;
import org.springframework.web.servlet.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * SpringMVC核心入口类DispatcherServlet测试
 */
public class DispatcherServlettest  {

    @Test
    public void init() throws ServletException {
        //初始化
        //HttpServletBean 的init()方法
        HttpServletBean servletBean = new HttpServletBean() {
        };
        servletBean.init();
        //FrameworkServlet 的initServletBean()()
        FrameworkServlet frameworkServlet = new FrameworkServlet() {
            @Override
            protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {

            }
        };
        //frameworkServlet.initServletBean();

      //  XmlBeanFactory xmlBeanFactory = new XmlBeanFactory();
       // xmlBeanFactory.getBean();

    }

///**
// * 初始化springMVC的核心九大组件
// * @param context
// */
//protected void initStrategies(ApplicationContext context) {
//    //初始化文件上传解析对象组件MultipartResolver
//    //该组件主要用于支持springMVC的文件上传
//    initMultipartResolver(context);
//    //初始化国际化资源解析器LocaleResolver
//    initLocaleResolver(context);
//    //初始化主题资源解析器 一个主题就是一组静态资源  其包含主题资源和主题解析器
//    initThemeResolver(context);
//    //初始化处理器映射器 该组件主要是根据对应的请求 获取对应的处理逻辑组件Handler
//    //（说白了就是我们编写的Controller的方法）
//    initHandlerMappings(context);
//    //初始化处理器适配器，因为我们编写的Handler 可能是不同的了类型，比如简单类型，http类型等
//    //为了使请求可能被不同的handler处理统一起来 这里会使用适配模式提供统一的处理请求的返回结果的接口
//    //所有业务逻辑执行是在该处理器适配器中执行的
//    //比如：我们国家的电压220V 但是和手机，洗衣机需要的电压，这里充电的时候会有不同的适配器将其电压转换为
//    //合适终端的电压，此即为处理器适配器
//    initHandlerAdapters(context);
//    //初始化 处理异常的组件，该组件有一个方法resolveException（） 该方法会对请求处理过程中出现异常的情况
//    //进行处理 根据不同的异常返回不同的异常页面
//    initHandlerExceptionResolvers(context);
//    //当Controller处理方法并没有返回视图的时候，且没有在reponse存放数据（往reponse中存放数据大多数是下载功能）
//    //该组件按照其getViewName()设置视图 从而返回
//    initRequestToViewNameTranslator(context);
//    //初始化视图解析器，当请求被处理放入ModelAndView.该组件会选择合适的视图去进行渲染
//    initViewResolvers(context);
//    //初始化FlashMapManager 用于在重定向的时候 还能继续使用香港的数据（一般情况重定向请求后请求参数会丢失）
//    initFlashMapManager(context);
//}


    /**
     * 处理器适配器
     * @throws ServletException
     */
    @Test
    public void handlerAdapter() throws ServletException {
        //HandlerAdapter


    }


//protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
//    HttpServletRequest processedRequest = request;
//    //处理器链 包含一个Handler和多个与之匹配的拦截器
//    HandlerExecutionChain mappedHandler = null;
//    //用于表示是否文件上传的标识
//    boolean multipartRequestParsed = false;
//    //异步管理器
//    WebAsyncManager asyncManager = WebAsyncUtils.getAsyncManager(request);
//
//    try {
//        ModelAndView mv = null;
//        Exception dispatchException = null;
//
//        try {
//            //判断请求是否文件上传请求 如果是文件上传 该方法中使用我们init()方法初始化的
//            //multipartResolver文件上传解析组件
//            //如果有文件解析组件且通过isMultipart()方法判断contentType的"multipart/" 开头的
//            // 会将所有的请求都包装成MultipartRequest
//            processedRequest = checkMultipart(request);
//            multipartRequestParsed = (processedRequest != request);
//
//            //通过请求获取到对应Handler处理器 和其中匹配的拦截器 组成HandlerExecutionChain
//            mappedHandler = getHandler(processedRequest);
//            if (mappedHandler == null || mappedHandler.getHandler() == null) {
//                noHandlerFound(processedRequest, response);
//                return;
//            }
//
//            //根据处理器获取对应的拦截器适配器
//            HandlerAdapter ha = getHandlerAdapter(mappedHandler.getHandler());
//
//            // 对于请求是get或者head请求 有LastModified的判断
//            //第一次请求的时候返回的响应信息会包含该资源的lastModified最后修改时间
//            //下次在进行请求会将lastModified带过来与资源的lastModified比较 如果时间一致
//            //则表明该资源没有被修改 则直接响应给浏览器 浏览器使用其缓存
//            String method = request.getMethod();
//            boolean isGet = "GET".equals(method);
//            if (isGet || "HEAD".equals(method)) {
//                long lastModified = ha.getLastModified(request, mappedHandler.getHandler());
//                if (logger.isDebugEnabled()) {
//                    logger.debug("Last-Modified value for [" + getRequestUri(request) + "] is: " + lastModified);
//                }
//                if (new ServletWebRequest(request, response).checkNotModified(lastModified) && isGet) {
//                    return;
//                }
//            }
//            //执行HandlerExecutionChain 中所有拦截器的preHandle()方法
//            if (!mappedHandler.applyPreHandle(processedRequest, response)) {
//                return;
//            }
//
//            // 最终使用适配器处理Handler 返回ModelAndView对象
//            mv = ha.handle(processedRequest, response, mappedHandler.getHandler());
//            //如果异步处理则此处直接返回
//            if (asyncManager.isConcurrentHandlingStarted()) {
//                return;
//            }
//            //如果ModelAndView没有view对象则使用我们在之前初始化的RequestToViewNameTranslator组件
//            //为其设置按照一定规则为其设置默认的视图
//            applyDefaultViewName(request, mv);
//            //执行HandlerExecutionChain 中所有拦截器的PostHandle()方法
//            mappedHandler.applyPostHandle(processedRequest, response, mv);
//        }
//        catch (Exception ex) {
//            dispatchException = ex;
//        }
//        //请求结果的处理 包括页面渲染，异常处理、以及请求完成后触发HandlerExecutionChain中所有拦截器的
//        // triggerAfterCompletion方法 进行资源清理工作
//        processDispatchResult(processedRequest, response, mappedHandler, mv, dispatchException);
//    }
//    catch (Exception ex) {
//        //碰到整个处理过程中的外层异常则触发触发HandlerExecutionChain中所有拦截器的
//        //triggerAfterCompletion方法 进行资源清理工作
//        triggerAfterCompletion(processedRequest, response, mappedHandler, ex);
//    }
//    catch (Error err) {
//        ///碰到整个处理过程中的外层error异常则触发触发HandlerExecutionChain中所有拦截器的
//        // 并返回ServletException异常
//        triggerAfterCompletionWithError(processedRequest, response, mappedHandler, err);
//    }
//    finally {
//        //异步处理的AsyncHandlerInterceptor的afterConcurrentHandlingStarted方法执行
//        if (asyncManager.isConcurrentHandlingStarted()) {
//            // Instead of postHandle and afterCompletion
//            if (mappedHandler != null) {
//                mappedHandler.applyAfterConcurrentHandlingStarted(processedRequest, response);
//            }
//        }
//        else {
//            //清除文件上传的请求对象的包装 如果是文件上传请求的话
//            if (multipartRequestParsed) {
//                cleanupMultipart(processedRequest);
//            }
//        }
//    }
//}
//
//
//private void processDispatchResult(HttpServletRequest request, HttpServletResponse response,
//                                   HandlerExecutionChain mappedHandler, ModelAndView mv, Exception exception) throws Exception {
//    boolean errorView = false;
//    if (exception != null) {
//        if (exception instanceof ModelAndViewDefiningException) {
//            logger.debug("ModelAndViewDefiningException encountered", exception);
//            mv = ((ModelAndViewDefiningException) exception).getModelAndView();
//        }
//        else {
//            Object handler = (mappedHandler != null ? mappedHandler.getHandler() : null);
//            mv = processHandlerException(request, response, handler, exception);
//            errorView = (mv != null);
//        }
//    }
//    if (mv != null && !mv.wasCleared()) {
//        render(mv, request, response);
//        if (errorView) {
//            WebUtils.clearErrorRequestAttributes(request);
//        }
//    }
//    else {
//        if (logger.isDebugEnabled()) {
//            logger.debug("Null ModelAndView returned to DispatcherServlet with name '" + getServletName() +
//                    "': assuming HandlerAdapter completed request handling");
//        }
//    }
//    if (WebAsyncUtils.getAsyncManager(request).isConcurrentHandlingStarted()) {
//        // Concurrent handling started during a forward
//        return;
//    }
//    if (mappedHandler != null) {
//        mappedHandler.triggerAfterCompletion(request, response, null);
//    }
//}
}
