package com.ning.mall.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ning.mall.MallApplicationTests;
import com.ning.mall.form.CartAddForm;
import com.ning.mall.vo.CartVo;
import com.ning.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;


@Slf4j
public class CartServiceImplTest extends MallApplicationTests {

    @Autowired
    private CartServiceImpl cartService;

    private Integer productId=26;


    Gson gson=new GsonBuilder().setPrettyPrinting().create();

    private Integer uid=1;


    @Test
    public void list() {
        ResponseVo<CartVo> responseVo =cartService.list(uid);
        log.info("list={}",gson.toJson(responseVo));
    }

    @Test
    public void addOneProduct() {
        CartAddForm form = new CartAddForm();
        form.setProductId(productId);
        form.setSelected(true);
        ResponseVo<CartVo> responseVo = cartService.addOneProduct(uid, form);
        log.info("list={}", gson.toJson(responseVo));

    }


    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void selectAll() {
    }

    @Test
    public void unSelectAll() {
    }

    @Test
    public void sum() {
    }
}