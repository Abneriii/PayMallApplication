package com.ning.mall.service;


import com.ning.mall.vo.CategoryVo;
import com.ning.mall.vo.ResponseVo;

import java.util.List;
import java.util.Set;

public interface CategoryService {


    ResponseVo<List<CategoryVo>> categories();

    ResponseVo<List<CategoryVo>> categories2();

    Set<Integer> subCategories(Integer categoryRootId,Set<Integer> categorySubId);



}
