package com.example.demo.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * 生产者类
 * Created by shixi09 on 2017/12/12.
 */
@Component
public class Sender implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    @Autowired
    private  RabbitTemplate rabbitTemplate;


    @PostConstruct
    public  void init(){
        System.out.println("开始初始化-----------------------------");
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if(ack){
            System.out.println("消息发送成功："+correlationData);
        }else{
            System.out.println("消息发送失败！！！"+cause);
        }

    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println(message.getMessageProperties().getCorrelationIdString()+"发送失败！！！");
    }

    //发送消息，供外部调用
    public void send(String msg){
        CorrelationData correlationData=new CorrelationData(UUID.randomUUID().toString());
        System.out.println("开始发送消息 ："+msg);
        String  response= rabbitTemplate.convertSendAndReceive("topicExchange","key.1",msg,correlationData).toString();
        System.out.println("结束发送消息："+msg);
        System.out.println("消费者响应："+response+" 消息处理完成！");






    }

}
