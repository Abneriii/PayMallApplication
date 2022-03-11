package com.ning.mall.service.impl;

import com.ning.mall.MallApplicationTests;
import com.ning.mall.enums.ResponseEnum;
import com.ning.mall.service.ICategoryService;
import com.ning.mall.vo.CategoryVo;
import com.ning.mall.vo.ResponseVo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class CategoryServiceImplTest extends MallApplicationTests {


    @Autowired
    private ICategoryService categoryService;

    @Test
    public void categories() {
        ResponseVo<List<CategoryVo>> responseVo=categoryService.categories();
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }
}