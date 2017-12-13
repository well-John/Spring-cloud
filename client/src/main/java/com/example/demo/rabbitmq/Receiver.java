package com.example.demo.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者
 * Created by shixi09 on 2017/12/12.
 */
@Component
public class Receiver {


    @RabbitListener(queues = "hello.queue1")
    public  String processMessage1(String  msg){
        System.out.println(Thread.currentThread().getName()+"接收到来自hello.queue1队列的消息："+msg);
        return  msg;
    }

    @RabbitListener(queues = "hello.queue2")
    public  String processMessage2(String  msg){
        System.out.println(Thread.currentThread().getName()+"接收到来自hello.queue2队列的消息："+msg);
        return  msg;
    }



}

