package com.tensequare.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.text.SimpleDateFormat;

public class ParseJwtTest {
    public static void main(String[] args) {
      //返回的claims可以看出是包含用户名密码的map
       Claims claims= Jwts.parser().setSigningKey("itcast").parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NjYiLCJzdWIiOiLlsI_pqawiLCJpYXQiOjE1NDY5MzA5MzcsImV4cCI6MTU0NjkzMDk5Nywicm9sZSI6ImFkbWluIn0.safW2wrp5-tWAlEYzS0axsxbqdjSVN9J5b7cITHMaLc")
                .getBody();
        System.out.println("用户id: "+claims.getId());
        System.out.println("用户名："+claims.getSubject());
        System.out.println("登录时间"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getIssuedAt()));
        System.out.println("过期时间"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getExpiration()));
        System.out.println("用户角色:" + claims.get("role"));


    }
}
