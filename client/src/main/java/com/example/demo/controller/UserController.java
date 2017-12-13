package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.rabbitmq.Sender;
import com.example.demo.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by shixi09 on 2017/12/13.
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;


    @Autowired
    Sender sender;

    @RequestMapping("/login")
    @ResponseBody
    public String login(User user) {
        if (userService.login(user)) {
           sender.send(user.getName());
            return "success";
        }
        return "error";
    }

    @RequestMapping("/update")
    public String updateUser(User user) {
        userService.updateUser(user);
        return "login";
    }

    @RequestMapping("/select")
    @ResponseBody
    public User selectAll(Integer id, Model model) {
        System.out.println("如果第二次没有走到这里说明缓存添加成功！！！");
        User user = userService.SelectUserById(id);
        return user;
    }


    @RequestMapping("/save")
    @ResponseBody
    public String save(User user) {
        if (userService.insertUser(user) == 1) {
            return "success";
        } else {
            return "error";
        }
    }


    public  User error(Integer id, Model model){
        System.out.println("短路器启动：");
        User user = new User();
        user.setId(id);
        return null;
    }

}
