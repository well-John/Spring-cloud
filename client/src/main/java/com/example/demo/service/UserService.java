package com.example.demo.service;


import com.example.demo.dao.UserMapper;
import com.example.demo.entity.User;
import com.example.demo.entity.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by shixi09 on 2017/12/13.
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;


    @Cacheable("user")
    public List<User> selectAll() {
        return userMapper.selectByExample(null);
    }

    @Transactional
    public int insertUser(User user) {
        return userMapper.insertSelective(user);
    }

    @Cacheable("user")
    public User SelectUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public int deleteUserById(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    public int updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    public boolean deleteAll() {
        return userMapper.deleteByExample(null) != 0;
    }

    public boolean login(User user) {

        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(user.getName());
        criteria.andPasswordEqualTo(user.getPassword());
        return userMapper.selectByExample(example).size() == 1 ? true : false;
    }

    public List<User> selectByName(String name){
        UserExample example=new UserExample();
        UserExample.Criteria  criteria=example.createCriteria();
        criteria.andNameEqualTo(name.trim());
        return  userMapper.selectByExample(example);
    }
}
