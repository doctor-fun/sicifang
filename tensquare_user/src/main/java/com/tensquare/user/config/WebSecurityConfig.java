package com.tensquare.user.config;




import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

    @Configuration
    @EnableWebSecurity
    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        //pom导入了spring-boot-starter-security，那么所有请求都会被拦截，因此需要设置一下，放行所有请求
        //anyRequest().authenticated()但是所有请求都要授权验证
        // 让加盐起作用，其他security性质不起作用
        @Override
        protected  void configure(HttpSecurity http) throws Exception{
            //authorizeRequests 所有security全注解配置实现的开端，表示开始说明需要的权限，
            //需要的权限分两部分，第一部分是拦截的路径，
            // 第二部分访问该路径需要的权限。
            //antMatchers（/**）表示拦截所有路径，permitAll任何权限都可以访问，这两个一加的意思就是springsecurity放行所有访问请求
            //anyRequest()任何的请求，authenticated认证后才能访问
            //.and().csrf().disable()；固定写法，表示csrf拦截失效（csrf为网络攻击的一种技术）
            http
                    .authorizeRequests()
                    .antMatchers("/**").permitAll()
                    .anyRequest().authenticated()
                    .and().csrf().disable();
        }

    }


