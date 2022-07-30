package com.ning.mall.controller;


import com.ning.mall.enums.ResponseEnum;
import com.ning.mall.form.UserLoginForm;
import com.ning.mall.form.UserRegisterForm;
import com.ning.mall.pojo.User;
import com.ning.mall.service.UserService;
import com.ning.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@Slf4j
public class UserController {


    @Autowired
    private UserService userService;

    /**
     *
     * @param userRegisterForm
     * @param
     * @return
     */
    @PostMapping("/user/register")
    public ResponseVo<User> register(@Valid @RequestBody UserRegisterForm userRegisterForm
                              ){

        User user=new User();
        BeanUtils.copyProperties(userRegisterForm,user);
        return userService.register(user);

    }

    /**
     *
     * @param userLoginForm
     * @param httpSession
     * @return
     */
    @PostMapping("/user/login")
    public ResponseVo<User> login(@Valid @RequestBody UserLoginForm userLoginForm,
                                  HttpSession httpSession) {

        ResponseVo<User> userResponseVo=userService.login(userLoginForm.getUsername(),userLoginForm.getPassword());

        httpSession.setAttribute("currentUser",userResponseVo.getData());
        return userResponseVo;
    }

    /**
     *
     * @param httpSession
     * @return
     */
    @GetMapping("/user")
    public ResponseVo<User> userInfo(HttpSession httpSession){
        User user=(User) httpSession.getAttribute("currentUser");
//        已加拦截器，不用判断了
//        if(user==null){
//            return ResponseVo.error(ResponseEnum.NEED_LOGIN,ResponseEnum.NEED_LOGIN.getDesc());
//        }
        return ResponseVo.success(ResponseEnum.SUCCESS.getCode(),user);
    }

    /**
     *
     * @param httpSession
     * @return
     */
    @PostMapping("/user/logout")
    public ResponseVo<User> logout(HttpSession httpSession){
        User user=(User) httpSession.getAttribute("currentUser");
//
//        已加拦截器，不用判断了
//        if(user==null){
//            return ResponseVo.error(ResponseEnum.NEED_LOGIN,ResponseEnum.NEED_LOGIN.getDesc());
//        }
        httpSession.removeAttribute("currentUser");
        return ResponseVo.successByMsg(ResponseEnum.SUCCESS.getCode(),"退出成功");

    }






}
