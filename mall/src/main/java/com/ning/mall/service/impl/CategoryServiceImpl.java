package com.ning.mall.service.impl;

import com.ning.mall.dao.CategoryMapper;
import com.ning.mall.enums.ResponseEnum;
import com.ning.mall.pojo.Category;
import com.ning.mall.service.CategoryService;
import com.ning.mall.vo.CategoryVo;
import com.ning.mall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;


    /**
     * @return 返回所有类目
     */
    @Override
    public ResponseVo<List<CategoryVo>> categories() {
        List<Category> categoriesList = categoryMapper.selectAll();
        List<CategoryVo> categoriesResult = new ArrayList<>();
        //找出parentId为0的类别

        for (Category category : categoriesList) {
            if (category.getParentId().equals(0)) {
                CategoryVo categoryVoRoot = new CategoryVo();
                BeanUtils.copyProperties(category, categoryVoRoot);
                categoryVoRoot.setSubCategories(subCategories(categoryVoRoot, categoriesList));//YW---传过去，能否实现内容的增加-----传类对象过去能否把这个对象本身改变了
                categoriesResult.add(categoryVoRoot);
            }
        }
        return new ResponseVo<>(ResponseEnum.SUCCESS.getCode(), categoriesResult);//

    }

    //递归调用，拿着自己的ID去找，查询有无以自己的ID作为parentID的数据，
    private List<CategoryVo> subCategories(CategoryVo categoryVo, List<Category> categoriesList) {
        List<CategoryVo> list = new ArrayList<>();
        for (Category category : categoriesList) {
            if (category.getParentId().equals(categoryVo.getId())) {
                CategoryVo categoryVoHelp = new CategoryVo();
                BeanUtils.copyProperties(category, categoryVoHelp);
                categoryVoHelp.setSubCategories(subCategories(categoryVoHelp, categoriesList));
                list.add(categoryVoHelp);
            }
        }
        return list;
    }

    /**
     * @param categoryRootId 根商品ID
     * @param categorySubId  根商品ID的子类目的ID集合
     * @return 返回
     */
    @Override
    public Set<Integer> subCategories(Integer categoryRootId, Set<Integer> categorySubId) {
        List<Category> categoriesList = categoryMapper.selectAll();
        return subCategories(categoryRootId, categorySubId, categoriesList);
    }


    private Set<Integer> subCategories(Integer categoryRootId, Set<Integer> categorySubId, List<Category> categoriesList) {
        for (Category category : categoriesList) {
            if (category.getParentId().equals(categoryRootId)) {
                categorySubId.add(category.getId());
                subCategories(category.getId(), categorySubId, categoriesList);
            }
        }
        return categorySubId;


    }


    @Override
    public ResponseVo<List<CategoryVo>> categories2() {
        List<CategoryVo> list = new ArrayList<>();
        List<Category> categoryList = categoryMapper.selectAll();
        for (Category category : categoryList) {
            if (category.getParentId() == 0) {
                CategoryVo categoryVo = new CategoryVo();
                BeanUtils.copyProperties(category, categoryVo);
                subCategories2(categoryVo);
                list.add(categoryVo);
            }
        }
        return ResponseVo.success(ResponseEnum.SUCCESS.getCode(), list);
    }

    //将category的子分类设置好，然后放入list
    private void subCategories2(CategoryVo categoryVo) {
        //
        List<Category> categoryChildren = categoryMapper.selectByParentId(categoryVo.getId());
        categoryVo.setSubCategories(new ArrayList<>());
        if (CollectionUtils.isEmpty(categoryChildren)) {
            return;
        }
        for (Category category : categoryChildren) {
            CategoryVo categoryVo2 = new CategoryVo();
            BeanUtils.copyProperties(category, categoryVo2);
            subCategories2(categoryVo2);
            categoryVo.getSubCategories().add(categoryVo2);
        }

    }

}