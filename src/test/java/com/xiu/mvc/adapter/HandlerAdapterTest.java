//package com.xiu.mvc.adapter;
//
//import org.junit.Test;
//import org.springframework.core.log.LogFormatUtils;
//import org.springframework.lang.Nullable;
//import org.springframework.web.bind.support.WebDataBinderFactory;
//import org.springframework.web.context.request.ServletWebRequest;
//import org.springframework.web.context.request.async.AsyncWebRequest;
//import org.springframework.web.context.request.async.WebAsyncManager;
//import org.springframework.web.context.request.async.WebAsyncUtils;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.method.annotation.ModelFactory;
//import org.springframework.web.method.support.ModelAndViewContainer;
//import org.springframework.web.servlet.HandlerAdapter;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;
//import org.springframework.web.servlet.support.RequestContextUtils;
//import org.springframework.web.util.WebUtils;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//public class HandlerAdapterTest {
//
//    @Test
//    public void testAapter(){
//    }
//
///**
// * 该方法是我们针对获取到的Handler对象进行转换解析处理的入口方法
// * 主要包含请求request的相关校验校验
// * 解析、转换、执行相关的handler的invokeHandlerMethod 并返回对应的数据视图对象。
// * 对应执行结束的响应对象response的设置 比如SessionAttributes信息的缓存控制
// */
//protected ModelAndView handleInternal(HttpServletRequest request,
//                                      HttpServletResponse response, HandlerMethod handlerMethod) throws Exception {
//
//    ModelAndView mav;
//    //如果设置supportMethods、requireSession属性需要对其进行校验
//    //如果处理的请求方式不是supportMethods集合支持的抛出异常
//    //或者requireSession为true但是request不包含session同样抛出异常
//    checkRequest(request);
//
//    // 针对线程不安全的session 提供两种方式 一种是同步 从而保证该会话的用户串行执行请求
//    // 一种是非同步处理请求 不管同步与否最终调用核心方法invokeHandlerMethod 调用HandlerMethod
//    if (this.synchronizeOnSession) {
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            Object mutex = WebUtils.getSessionMutex(session);
//            synchronized (mutex) {
//                mav = invokeHandlerMethod(request, response, handlerMethod);
//            }
//        }
//        else {
//            // No HttpSession available -> no mutex necessary
//            mav = invokeHandlerMethod(request, response, handlerMethod);
//        }
//    }
//    else {
//        // No synchronization on session demanded at all...
//        mav = invokeHandlerMethod(request, response, handlerMethod);
//    }
//    //为响应资源设置对应的缓存时间，指定时间内可以直接使用本地缓存
//    if (!response.containsHeader(HEADER_CACHE_CONTROL)) {
//        if (getSessionAttributesHandler(handlerMethod).hasSessionAttributes()) {
//            applyCacheSeconds(response, this.cacheSecondsForSessionAttributeHandlers);
//        }
//        else {
//            prepareResponse(response);
//        }
//    }
//
//    return mav;
//}
//
//
///***************************************************************************************/
///**
// * 解析、转换、执行、返回数据视图的核心方法
// */
//@Nullable
//protected ModelAndView invokeHandlerMethod(HttpServletRequest request,
//                                           HttpServletResponse response, HandlerMethod handlerMethod) throws Exception {
//    //先将请求响应对象包装成ServletWebRequest
//    ServletWebRequest webRequest = new ServletWebRequest(request, response);
//    try {
//        //获取该方法的所有使用@InitBinder注解修饰的方法
//        //包装成WebDataBinderFactory 在handlerMethod执行前调用（包含类作用范围的和全局作用范围的）
//        WebDataBinderFactory binderFactory = getDataBinderFactory(handlerMethod);
//        //获取该方法的所有使用@ModelAttribute注解修饰的方法 包装成ModelFactory（包含类作用范围的和全局作用范围的）
//        ModelFactory modelFactory = getModelFactory(handlerMethod, binderFactory);
//
//        //上面获取@InitBinder/@ModelAttribute注解修饰的方法雷同，都是handlerMethod对应的类
//        //中类范围的注解修饰的方法（解析完成后放入对应的缓存）,接着获取@ControllerAdvice修饰的全局范围的使用如上注解修饰的方法
//        //将对应的方法包装成InvocableHandlerMethod用于后面的执行，并将其包装成WebDataBinderFactory/ModelFactory对象。
//
//        //两者的区别在于ModelFactory中不仅仅包含@ModelAttribute注解修饰的方法还包含@SessionAttribute注解修饰的model处理
//        //同时也将上面解析出来的WebDataBinderFactory存放到modelFactory中。
//
//        //将HandlerMethod方法包装成ServletInvocableHandlerMethod（该对象是InvocableHandlerMethod子类）
//        //设置相关的组件 比如设置参数解析组件argumentResolvers和返回结果处理组件returnValueHandlers
//        //绑定组件binderFactory和parameterNameDiscoverer
//        ServletInvocableHandlerMethod invocableMethod = createInvocableHandlerMethod(handlerMethod);
//        if (this.argumentResolvers != null) {
//            invocableMethod.setHandlerMethodArgumentResolvers(this.argumentResolvers);
//        }
//        if (this.returnValueHandlers != null) {
//            invocableMethod.setHandlerMethodReturnValueHandlers(this.returnValueHandlers);
//        }
//        invocableMethod.setDataBinderFactory(binderFactory);
//        invocableMethod.setParameterNameDiscoverer(this.parameterNameDiscoverer);
//
//        //创建数据模型和视图的容器对象 见名知义其包含我们所常见的数据和视图对象
//        ModelAndViewContainer mavContainer = new ModelAndViewContainer();
//        //对于重定向相关的参数保存需要依赖flashMap对象，如果一个请求是重定向请求 则input_flash_map保存重定向入参
//        //如果一个请求需要进行重定向 则参数会存放到output_flash_map中
//        mavContainer.addAllAttributes(RequestContextUtils.getInputFlashMap(request));
//        //调用@ModelAttribute注解修饰的方法将对应的属性存放到mavContainer 的model中
//        modelFactory.initModel(webRequest, mavContainer, invocableMethod);
//        mavContainer.setIgnoreDefaultModelOnRedirect(this.ignoreDefaultModelOnRedirect);
//
//        //异步请求处理 此处再是先省略
//        AsyncWebRequest asyncWebRequest = WebAsyncUtils.createAsyncWebRequest(request, response);
//        asyncWebRequest.setTimeout(this.asyncRequestTimeout);
//        WebAsyncManager asyncManager = WebAsyncUtils.getAsyncManager(request);
//        asyncManager.setTaskExecutor(this.taskExecutor);
//        asyncManager.setAsyncWebRequest(asyncWebRequest);
//        asyncManager.registerCallableInterceptors(this.callableInterceptors);
//        asyncManager.registerDeferredResultInterceptors(this.deferredResultInterceptors);
//        if (asyncManager.hasConcurrentResult()) {
//            Object result = asyncManager.getConcurrentResult();
//            mavContainer = (ModelAndViewContainer) asyncManager.getConcurrentResultContext()[0];
//            asyncManager.clearConcurrentResult();
//            LogFormatUtils.traceDebug(logger, traceOn -> {
//                String formatted = LogFormatUtils.formatValue(result, !traceOn);
//                return "Resume with async result [" + formatted + "]";
//            });
//            invocableMethod = invocableMethod.wrapConcurrentResult(result);
//        }
//        //通过反射执行相关的handlerMethod 涉及参数解析，返回值的处理、
//        //invocableMethod已经包含了进行参数解析和返回值处理的组件对象
//        invocableMethod.invokeAndHandle(webRequest, mavContainer);
//        if (asyncManager.isConcurrentHandlingStarted()) {
//            return null;
//        }
//        //主要处理sessionAttribute 以及对model中的属性进行属性编辑器的转换处理@InitBinder
//        //对于view对象进行逻辑视图到物理视图的转换以及重定向参数的设置
//        return getModelAndView(mavContainer, modelFactory, webRequest);
//    }
//    finally {
//        webRequest.requestCompleted();
//    }
//}
//
//
//
//
//
//
//
//
//
//}
