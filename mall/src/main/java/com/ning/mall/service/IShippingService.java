package com.ning.mall.service;


import com.github.pagehelper.PageInfo;
import com.ning.mall.form.ShippingForm;
import com.ning.mall.vo.ResponseVo;

import java.util.Map;

//收获地址
public interface IShippingService {

    ResponseVo<Map<String, Integer>> add(Integer uid, ShippingForm form);

    ResponseVo delete(Integer uid,Integer shippingId);


    ResponseVo update(Integer uid, Integer shippingId,ShippingForm form);



    ResponseVo<PageInfo> list(Integer uid, Integer pageNum, Integer pageSize);



}
