package com.ning.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ning.mall.dao.ShippingMapper;
import com.ning.mall.enums.ResponseEnum;
import com.ning.mall.form.ShippingForm;
import com.ning.mall.pojo.Shipping;
import com.ning.mall.service.ShippingService;
import com.ning.mall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShippingServiceImpl implements ShippingService {

    @Autowired
    private ShippingMapper shippingMapper;


    /**
     *
     * @param uid
     * @param form
     * @return
     *
     *
     */
    @Override
    public ResponseVo<Map<String, Integer>> add(Integer uid, ShippingForm form) {
        Shipping shipping = new Shipping();
        BeanUtils.copyProperties(form, shipping);
        shipping.setUserId(uid);
        int row = shippingMapper.insertSelective(shipping);
        if (row == 0) {
            return ResponseVo.error(ResponseEnum.ERROR,ResponseEnum.ERROR.getDesc());
        }
        Map<String, Integer> map = new HashMap<>();
        map.put("shippingId", shipping.getId());
        return ResponseVo.success(ResponseEnum.SUCCESS.getCode(),map);

    }

    @Override
    public ResponseVo delete(Integer uid, Integer shippingId) {
        int row = shippingMapper.deleteByIdAndUid(uid, shippingId);
        if (row == 0) {
            return ResponseVo.error(ResponseEnum.DELETE_SHIPPING_FAIL,ResponseEnum.DELETE_SHIPPING_FAIL.getDesc());
        }

        return ResponseVo.success(ResponseEnum.SUCCESS.getCode(),ResponseEnum.SUCCESS.getDesc());

    }

    @Override
    public ResponseVo update(Integer uid, Integer shippingId,ShippingForm form) {
        Shipping shipping = new Shipping();
        BeanUtils.copyProperties(form, shipping);
        shipping.setUserId(uid);
        shipping.setId(shippingId);
        int row = shippingMapper.updateByPrimaryKeySelective(shipping);
        if (row == 0) {
            return ResponseVo.error(ResponseEnum.ERROR,"更新地址失败");
        }
        return ResponseVo.successByMsg(ResponseEnum.SUCCESS.getCode(),"更新地址成功");


    }


    @Override
    public ResponseVo<PageInfo> list(Integer uid, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Shipping> shippings = shippingMapper.selectByUid(uid);
        PageInfo pageInfo = new PageInfo(shippings);
        return ResponseVo.success(ResponseEnum.SUCCESS.getCode(),pageInfo);
    }


}
