package com.ning.mall.dao;

import com.ning.mall.pojo.Category;

import java.util.List;


public interface CategoryMapper {


    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    List<Category> selectAll();//YW----查出所有数据，这个接口如何写,----mybatis接口命名规则？


}