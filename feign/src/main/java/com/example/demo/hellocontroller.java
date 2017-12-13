package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/12/13.
 */
@RestController
public class hellocontroller {

    @Autowired
    helloclient helloclient;

    @RequestMapping("/hello")
    public String hello(){
        return  helloclient.hello();
    }

}

