package com.tensquare.friend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Component
@FeignClient(serviceId ="tensquare-user")//写吊的是哪个服务
public interface UserClient {
    @RequestMapping(value = "/user/{userid}/{friendid}/{x}",method= RequestMethod.PUT)
    public void updatefanscountandfollowcount(@PathVariable("userid") String userid,@PathVariable("friendid") String friendid,@PathVariable("x") int x);
}
