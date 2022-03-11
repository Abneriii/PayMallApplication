package com.ning.mall.service;

import com.ning.mall.form.CartAddForm;
import com.ning.mall.form.CartUpdateForm;
import com.ning.mall.pojo.Cart;
import com.ning.mall.vo.CartProductVo;
import com.ning.mall.vo.CartVo;
import com.ning.mall.vo.ResponseVo;


import java.util.List;

public interface ICartService {



    //购物车列表
    ResponseVo<CartVo> list(Integer uid);


    //给一个商品数量加1,参数必填
     ResponseVo<CartVo> addOneProduct(Integer uid,CartAddForm cartAddForm);


    //更新购物车。参数非必填(diaYW非必填可为空，or直接可不传)。
     ResponseVo<CartVo> update(Integer uid,Integer productId,CartUpdateForm cartUpdateForm);

     ResponseVo<CartVo> delete(Integer uid,Integer productId);


     ResponseVo<CartVo> selectAll(Integer uid);

     ResponseVo<CartVo> unSelectAll(Integer uid);


     ResponseVo<Integer> sum(Integer uid);

     List<Cart> listForCart(Integer uid);
}
