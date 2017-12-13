package com.example.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by shixi09 on 2017/12/12.
 */
@Configuration
public class RabbitConfig {

    //声明队列
    @Bean
    public Queue queue1(){
        return new Queue("hello.queue1",true);//true表示持久化队列
    }
    @Bean
    public Queue queue2(){
        return new Queue("hello.queue2",true);//true表示持久化队列
    }

    //声明交互器

    TopicExchange topicExchange(){
        return new TopicExchange("topicExchange",true,true);
    }

    //绑定
    @Bean
    public Binding binding1(){
        return BindingBuilder.bind(queue1()).to(topicExchange()).with("key.1");
    }
    @Bean
    public Binding binding2(){
        return BindingBuilder.bind(queue2()).to(topicExchange()).with("key.#");
    }

}
