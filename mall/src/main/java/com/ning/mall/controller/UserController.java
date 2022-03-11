package com.ning.mall.controller;


import com.ning.mall.enums.ResponseEnum;
import com.ning.mall.form.UserLoginForm;
import com.ning.mall.form.UserRegisterForm;
import com.ning.mall.pojo.User;
import com.ning.mall.service.IUserService;
import com.ning.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@Slf4j
public class UserController {

    //mineYW:用啥接受
    @Autowired
    private IUserService userService;

    /**
     *
     * @param userRegisterForm
     * @param
     * @return
     * mineYW--@PostMapping和@RequestMapping和@GetMappping。BindingResult .@Valid
     */
    @PostMapping("/user/register")
    public ResponseVo<User> register(@Valid @RequestBody UserRegisterForm userRegisterForm
                              ){

        User user=new User();
        BeanUtils.copyProperties(userRegisterForm,user);//dai---BeanUtils
        return userService.register(user);


    }


    @PostMapping("/user/login")
    public ResponseVo<User> login(@Valid @RequestBody UserLoginForm userLoginForm,
                                  HttpSession httpSession) {//mine:如何接受参数。mine-controller层和service层的职责区分。

        ResponseVo<User> userResponseVo=userService.login(userLoginForm.getUsername(),userLoginForm.getPassword());

        //dai视频中将这"currentUser"设置为了一个外部类中的常量
        httpSession.setAttribute("currentUser",userResponseVo.getData());//YW这个getData()是属于@Data？

        return userResponseVo;


    }

    @GetMapping("/user")
    public ResponseVo<User> userInfo(HttpSession httpSession){
        User user=(User) httpSession.getAttribute("currentUser");
//        已加拦截器，不用判断了
//        if(user==null){
//            return ResponseVo.error(ResponseEnum.NEED_LOGIN,ResponseEnum.NEED_LOGIN.getDesc());
//        }
        return ResponseVo.success(ResponseEnum.SUCCESS.getCode(),user);
    }

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
