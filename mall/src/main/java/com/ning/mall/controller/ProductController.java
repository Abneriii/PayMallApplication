package com.ning.mall.controller;


import com.ning.mall.enums.ResponseEnum;
import com.ning.mall.pojo.Product;
import com.ning.mall.service.impl.CategoryServiceImpl;
import com.ning.mall.service.impl.ProductServiceImpl;
import com.ning.mall.vo.ProductVo;
import com.ning.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@Slf4j
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;


    @GetMapping("/products")
    public ResponseVo<List<ProductVo>> products(@RequestParam(required = false) Integer categoryId){
        return productService.products(categoryId);

    }



    @GetMapping("/products/{productId}")
    public ResponseVo<Product> product(@PathVariable  Integer productId){
        return productService.product(productId);
    }





}
