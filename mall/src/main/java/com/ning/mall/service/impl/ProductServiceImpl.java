package com.ning.mall.service.impl;

import com.ning.mall.dao.ProductMapper;
import com.ning.mall.enums.ResponseEnum;
import com.ning.mall.pojo.Product;
import com.ning.mall.service.ProductService;
import com.ning.mall.vo.ProductVo;
import com.ning.mall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ResponseVo<List<ProductVo>> products(Integer categoryId) {
        Set<Integer> categoriesId=new HashSet<>();
        categoryService.subCategories(categoryId,categoriesId);
        categoriesId.add(categoryId);
        List<Product> productList=productMapper.selectByCategoryIdSet(categoriesId);
        List<ProductVo> productVoList=new ArrayList<>();
        for(Product product:productList){
            ProductVo productVo=new ProductVo();
            BeanUtils.copyProperties(product,productVo);
            productVoList.add(productVo);
        }
        return new ResponseVo<>(ResponseEnum.SUCCESS.getCode(),productVoList);
    }


    @Override
    public ResponseVo<Product> product(Integer productId) {
        return  new ResponseVo<>(ResponseEnum.SUCCESS.getCode(),productMapper.selectByPrimaryKey(productId));
    }


}
