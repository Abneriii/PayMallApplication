package com.ning.mall.controller;


import com.ning.mall.service.impl.CategoryServiceImpl;
import com.ning.mall.vo.CategoryVo;
import com.ning.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class CategoryController {


    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping("/categories")
    private ResponseVo<List<CategoryVo>> categories(){

    return categoryService.categories();


    }

    @GetMapping("/categories2")
    private ResponseVo<List<CategoryVo>> categories2(){

        return categoryService.categories2();


    }

}
