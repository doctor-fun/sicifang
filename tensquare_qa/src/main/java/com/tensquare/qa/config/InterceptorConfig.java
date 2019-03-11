package com.tensquare.qa.config;

import com.tensquare.qa.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

//springboot不再有web.xml配置，只能写一个拦截器的类
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {
    @Autowired
    private JwtInterceptor jwtInterceptor;//获得整个拦截器，进行拦截

    protected  void addInterceptors(InterceptorRegistry registry){
            //注册拦截器要声明拦截器对象和要拦截的请求（拦截器是谁，拦截器要干什么）
        registry.addInterceptor(jwtInterceptor)
                    .addPathPatterns("/**")//拦截所有路径
                    .excludePathPatterns("/**/login/**");//但不拦截login登录的路径

    }

}
