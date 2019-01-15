package com.tensequare.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class CreateJwt {//对相应用户生成令牌
    public static  void main(String[] args){
       JwtBuilder jwtBuilder= Jwts.builder()
                .setId("666")
                .setSubject("小马")//用户名
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"itcast")
                .setExpiration(new Date(new Date().getTime()+60000))//设置过期时间
                .claim("role","admin");//因为claim就是个map，所以可以往里面直接自定义增加用户角色属性

        System.out.println(jwtBuilder.compact());
    }
}
