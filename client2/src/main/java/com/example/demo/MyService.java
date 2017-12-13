package com.example.demo;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * Created by shixi09 on 2017/12/13.
 */
@Controller
public class MyService {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        System.out.println("-----------------hello--------------");
        return "hello";
    }

   @HystrixCommand(fallbackMethod = "error")
    @RequestMapping("select")
    @ResponseBody
    public String execute(Integer id){
      restTemplate.getForObject("http://localhost:8888/select?id="+id,String.class);
      return "success";
    }

    public String error(Integer id){
        System.out.println("errror-----------------");
        return  "error";
    }
}
