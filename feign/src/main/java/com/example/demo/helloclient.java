package com.example.demo;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/12/13.
 */
@FeignClient("client2")
public interface helloclient {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello();

}


