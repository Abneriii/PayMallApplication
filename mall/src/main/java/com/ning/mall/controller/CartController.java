package com.ning.mall.controller;


import com.ning.mall.enums.ResponseEnum;
import com.ning.mall.form.CartAddForm;
import com.ning.mall.form.CartUpdateForm;
import com.ning.mall.pojo.Cart;
import com.ning.mall.pojo.User;
import com.ning.mall.service.impl.CartServiceImpl;
import com.ning.mall.vo.CartVo;
import com.ning.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class CartController {

    @Autowired
    private CartServiceImpl cartService;

    @GetMapping("/carts")
    public ResponseVo<CartVo> list(HttpSession httpSession)
    {
        User user = (User) httpSession.getAttribute("currentUser");
        return cartService.list(user.getId());
    }



    @PostMapping("/carts")
    public ResponseVo<CartVo> addOneProduct(@Valid @RequestBody CartAddForm cartAddForm, HttpSession httpSession){

        User user = (User) httpSession.getAttribute("currentUser");
        return cartService.addOneProduct(user.getId(),cartAddForm);
    }


    @PutMapping("/carts/{productId}")
    public ResponseVo<CartVo> update(@Valid @RequestBody CartUpdateForm cartUpdateForm,@PathVariable  Integer productId,HttpSession httpSession){
        User user = (User) httpSession.getAttribute("currentUser");

        return cartService.update(user.getId(),productId,cartUpdateForm);

    }


    @DeleteMapping("/carts/{productId}")
    public ResponseVo<CartVo> delete(@PathVariable  Integer productId,HttpSession httpSession){
        User user = (User) httpSession.getAttribute("currentUser");

        return cartService.delete(user.getId(),productId);

    }


    @PutMapping("/carts/selectAll")
    public ResponseVo<CartVo> selectAll(HttpSession httpSession){
        User user = (User) httpSession.getAttribute("currentUser");
        return cartService.selectAll(user.getId());
    }



    @PutMapping("/carts/unSelectAll")
    public ResponseVo<CartVo> unSelectAll(HttpSession httpSession){
        User user = (User) httpSession.getAttribute("currentUser");
        return cartService.unSelectAll(user.getId());

    }


    @GetMapping("/carts/products/sum")
    public ResponseVo<Integer> sum(HttpSession httpSession){
        User user = (User) httpSession.getAttribute("currentUser");
        return cartService.sum(user.getId());
    }







}
