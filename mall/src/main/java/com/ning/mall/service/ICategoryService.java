package com.ning.mall.service;


import com.ning.mall.vo.CategoryVo;
import com.ning.mall.vo.ResponseVo;

import java.util.List;
import java.util.Set;

public interface ICategoryService {


    ResponseVo<List<CategoryVo>> categories();

    Set<Integer> subCategories(Integer categoryRootId,Set<Integer> categorySubId);



}
