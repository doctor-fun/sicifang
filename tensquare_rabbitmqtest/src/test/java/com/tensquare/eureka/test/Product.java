package com.tensquare.eureka.test;

import com.tensquare.eureka.rabbit.RabbitApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)//替代junit原生的运行器
@SpringBootTest(classes= RabbitApplication.class)
public class Product {
    @Autowired
    private RabbitTemplate rabbitTemplate;

     @Test
    public void sendMsg1(){
        rabbitTemplate.convertAndSend("itcast","直接模式测试");
    }
    @Test
    /**
     * chuanzhi是交换器的名字
     */
    public void sendMsg2(){
        rabbitTemplate.convertAndSend("chuanzhi","","分裂模式测试");
    }
    //主题模式
    @Test
    public void sendMsg3(){
        rabbitTemplate.convertAndSend("topic84","good.log","主题模式测试");
    }



}
