package com.ning.mall.service;


import com.ning.mall.pojo.User;
import com.ning.mall.vo.ResponseVo;
import org.springframework.stereotype.Service;



@Service
public interface IUserService {


    ResponseVo<User> register(User user);//mine在controller层会有一个

    ResponseVo<User> login(String username,String password);


}
