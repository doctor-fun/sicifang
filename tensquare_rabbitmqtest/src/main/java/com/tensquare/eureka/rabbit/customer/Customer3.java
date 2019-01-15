package com.tensquare.eureka.rabbit.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RabbitListener(queues="kudingyu")//有文件来了
@Component
public class Customer3 {
    @RabbitHandler
    public  void getMsg(String msg){
        System.out.println("kudingyu:"+msg);

    }


}
