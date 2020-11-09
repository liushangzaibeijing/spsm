//import com.xiu.mvc.BaseTest;
//import org.junit.Test;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.method.support.HandlerMethodArgumentResolverComposite;
//import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
//import org.springframework.web.method.support.HandlerMethodReturnValueHandlerComposite;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.handler.AbstractHandlerMethodMapping;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.locks.ReentrantReadWriteLock;
//
//public class BuninseeServiceTest extends BaseTest {
//    //RequestMappingHandlerAdapter 实现了InitializingBean接口 所以其实例化的核心逻辑在该方法中
//    //该方法主要实例化其Adapter类进行参数解析的组件和需要全局起作用的@ControllerAdavice注解修饰的对象和方法
//    @Override
//    public void afterPropertiesSet() {
//        //解析spring容器中所有@ControllerAdvice注解修饰的所有bean实例
//        //
//        initControllerAdviceCache();
//
//        if (this.argumentResolvers == null) {
//            List<HandlerMethodArgumentResolver> resolvers = getDefaultArgumentResolvers();
//            this.argumentResolvers = new HandlerMethodArgumentResolverComposite().addResolvers(resolvers);
//        }
//        if (this.initBinderArgumentResolvers == null) {
//            List<HandlerMethodArgumentResolver> resolvers = getDefaultInitBinderArgumentResolvers();
//            this.initBinderArgumentResolvers = new HandlerMethodArgumentResolverComposite().addResolvers(resolvers);
//        }
//        if (this.returnValueHandlers == null) {
//            List<HandlerMethodReturnValueHandler> handlers = getDefaultReturnValueHandlers();
//            this.returnValueHandlers = new HandlerMethodReturnValueHandlerComposite().addHandlers(handlers);
//        }
//    }
//
//
//
//}
//
//
