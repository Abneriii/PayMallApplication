package com.ning.mall.service;


import com.ning.mall.pojo.User;
import com.ning.mall.vo.ResponseVo;
import org.springframework.stereotype.Service;



@Service
public interface UserService {


    ResponseVo<User> register(User user);

    ResponseVo<User> login(String username,String password);



}
