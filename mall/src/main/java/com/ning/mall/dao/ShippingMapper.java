package com.ning.mall.dao;

import com.ning.mall.pojo.Shipping;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface ShippingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Shipping record);

    //将返回值配置成了：数据的id
    int insertSelective(Shipping record);

    Shipping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Shipping record);

    int updateByPrimaryKey(Shipping record);

    //dai
    Shipping selectByUidAndShippingId(@Param("uid") Integer uid,
                                      @Param("shippingId") Integer shippingId);

    int deleteByIdAndUid(@Param("uid") Integer uid,
                         @Param("shippingId") Integer shippingId);



    //dai
    List<Shipping> selectByUid(Integer uid);



    List<Shipping> selectByIdSet(@Param("idSet") Set idSet);
}