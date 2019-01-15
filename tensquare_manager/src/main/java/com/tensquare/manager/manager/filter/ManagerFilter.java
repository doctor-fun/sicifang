package com.tensquare.manager.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

@Component
public class ManagerFilter extends ZuulFilter {
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 在请求前pre或者后post执行
     * @return
     */
    @Override
    public String filterType() {//过滤器的类型
        return "pre";//操作之后过滤
    }

    /**
     * 多个过滤器的执行顺序,数字越小越先执行,现在只有一个
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 当前过滤器是否开启，return false表示关闭
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 这个是过滤器的实际体
     * 过滤器内执行的操作return 任何object的值都表示继续执行
     * setsendzullResponse（false） 表示不再继续执行
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        System.out.println("经过后台过滤器了");
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String header = request.getHeader("Authorization");
        if(request.getMethod().equals("options")){//第一次请求不是httprequest，而是网关的业务request，用于内部识别将请求转发到哪个业务
            return null;
        }
        //当前这个单词login在这个请求中出现的位置，没出现为0，出现了则值肯定大于0
        //不是Login请求
        if(request.getRequestURI().indexOf("login")>0){
            return null;
        }
        if(header!=null&& !"".equals(header)){
            if(header.startsWith("Bearer ")){
                String token=header.substring(7);
                try{
                  Claims claims=  jwtUtil.parseJWT(token);
                  String roles=(String)claims.get("roles");
                    if(roles.equals("admin")){
                        //把头信息转发下去，并且放行
                        currentContext.addZuulRequestHeader("Authorization",header);
                        return  null;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    currentContext.setSendZuulResponse(false);//终止运行
                }
            }
        }
        currentContext.setSendZuulResponse(false);
        currentContext.setResponseStatusCode(403);
        currentContext.setResponseBody("权限不足");
        currentContext.getResponse().setContentType("text/html;charset=utf-8");
        return null;
    }
}
