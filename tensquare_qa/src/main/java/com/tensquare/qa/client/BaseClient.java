package com.tensquare.qa.client;
  import com.tensquare.qa.client.impl.BaseClientImpl;
  import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
  import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//本类用于调用Base服务
@FeignClient(value = "tensquare-base",fallback = BaseClientImpl.class)//如果执行失败就进入fallback
public interface BaseClient {
    //调用base模块下的controller包下的findById，要多加个母label
    @RequestMapping(value = "/label/{labelId}",method= RequestMethod.GET)
    public Result findById(@PathVariable("labelId") String labelId);//这里必须要在PathVariable后面
    // 加个（"labelId"）原来可不加，但服务调用必须加
}
