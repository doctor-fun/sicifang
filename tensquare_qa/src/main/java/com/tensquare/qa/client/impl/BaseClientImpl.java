package com.tensquare.qa.client.impl;

import com.tensquare.qa.client.BaseClient;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Component;

@Component
public class BaseClientImpl implements BaseClient {
    //如果BaseClient出错了就往这个分支走
    @Override
    public Result findById(String labelId){
        return new Result(false, StatusCode.ERROR,"熔断器触发了!");

    }
}
