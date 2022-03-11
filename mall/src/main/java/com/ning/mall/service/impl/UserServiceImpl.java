package com.ning.mall.service.impl;

import com.ning.mall.dao.UserMapper;
import com.ning.mall.enums.ResponseEnum;
import com.ning.mall.enums.RoleEnum;
import com.ning.mall.pojo.User;
import com.ning.mall.service.IUserService;
import com.ning.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;


@Service
public class UserServiceImpl implements IUserService {




    @Autowired
    private UserMapper userMapper;

    /**
     * 用户注册，需要字段username、email、password
     */
    @Override
    public ResponseVo register(User user) {

        //username不重复
       int countByUsername=userMapper.countByUsername(user.getUsername());
        if(countByUsername>0){
            return ResponseVo.error(ResponseEnum.USERNAME_EXIST,ResponseEnum.USERNAME_EXIST.getDesc());
        }
        //email不重复
        int countByEmail=userMapper.countByEmail(user.getEmail());
        if(countByEmail>0){
            return ResponseVo.error(ResponseEnum.EMAIL_EXIST,ResponseEnum.EMAIL_EXIST.getDesc());
        }



        user.setRole(RoleEnum.CUSTOMER.getCode());
        //MD5摘要算法(Spring自带)
        user.setPassword(DigestUtils.md5DigestAsHex(
                user.getPassword().getBytes(StandardCharsets.UTF_8)
        ));

        int resultCount=userMapper.insertSelective(user);//为啥不用insert？返回值含义？-----insert和insertSelective啥区别
        if(resultCount==0){
            return ResponseVo.error(ResponseEnum.ERROR,ResponseEnum.ERROR.getDesc());
        }

        return ResponseVo.successByMsg(ResponseEnum.SUCCESS.getCode(),ResponseEnum.SUCCESS.getDesc());
    }

    @Override
    public ResponseVo<User> login(String username,String password){//mine为什么报错Could not autowire. No beans of 'String' type found.
            User user=userMapper.selectByUsername(username);//mineYW如果用户名不存在这个方法返回什么---返回null---mybatis的知识？
            if(user==null){
                return ResponseVo.error(ResponseEnum.USERNAME_OR_PASSWORD_ERROR,ResponseEnum.USERNAME_OR_PASSWORD_ERROR.getDesc());
            }

            //YW视频中比较大小用的是equalsIngoreCase()
        /**
         * if中的比较语句有误，dai排查，作为issue提交
         * if(user.getPassword()!=DigestUtils.md5DigestAsHex(
         *                 password.getBytes(StandardCharsets.UTF_8)
         *             )){
         *                 return ResponseVo.error(ResponseEnum.USERNAME_OR_PASSWORD_ERROR,ResponseEnum.USERNAME_OR_PASSWORD_ERROR.getDesc());
         *             }
         */
        if(!user.getPassword().equalsIgnoreCase(DigestUtils.md5DigestAsHex(
                password.getBytes(StandardCharsets.UTF_8)
        ))){
                return ResponseVo.error(ResponseEnum.USERNAME_OR_PASSWORD_ERROR,ResponseEnum.USERNAME_OR_PASSWORD_ERROR.getDesc());
            }

            user.setPassword("");//将password字段置空，ResponseVo类的注解会不返回这个空值
            return ResponseVo.success(ResponseEnum.SUCCESS.getCode(),user);

    }





}
