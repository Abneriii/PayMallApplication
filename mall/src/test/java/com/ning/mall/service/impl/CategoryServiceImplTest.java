package com.ning.mall.service.impl;

import com.ning.mall.MallApplicationTests;
import com.ning.mall.enums.ResponseEnum;
import com.ning.mall.service.CategoryService;
import com.ning.mall.vo.CategoryVo;
import com.ning.mall.vo.ResponseVo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CategoryServiceImplTest extends MallApplicationTests {


    @Autowired
    private CategoryService categoryService;

    @Test
    public void categories() {
        ResponseVo<List<CategoryVo>> responseVo=categoryService.categories();
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }


    @Test
    public void categories2() {
        ResponseVo<List<CategoryVo>> responseVo=categoryService.categories2();
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }



}