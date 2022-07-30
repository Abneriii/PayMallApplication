package com.ning.mall.service;

import com.ning.mall.pojo.Product;
import com.ning.mall.vo.ProductVo;
import com.ning.mall.vo.ResponseVo;

import java.util.List;
import java.util.Set;

public interface ProductService {

    ResponseVo<List<ProductVo>> products(Integer categoryId);

    ResponseVo<Product> product(Integer productId);

}
