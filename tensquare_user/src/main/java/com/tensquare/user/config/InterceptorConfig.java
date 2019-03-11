package com.tensquare.user.config;

import com.tensquare.user.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


////springboot不再有web.xml配置，只能写一个拦截器的类
//@Component
//@Configuration
//public class InterceptorConfig extends WebMvcConfigurationSupport {
//    @Autowired
//    private JwtInterceptor jwtInterceptor;//获得整个拦截器，进行拦截
//    @Override
//    protected  void addInterceptors(InterceptorRegistry registry){
//            //注册拦截器要声明拦截器对象和要拦截的请求（拦截器是谁，拦截器要干什么）
//        registry.addInterceptor(jwtInterceptor)
//                    .addPathPatterns("/**")//拦截所有路径
//                    .excludePathPatterns("/**/login/**");//但不拦截login登录的路径
//
//    }
//
//}
@Component
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {
    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")//先设置拦截器的路径，再特别放行/**/login/**的请求
                .excludePathPatterns("/**/login/**");
    }
}
