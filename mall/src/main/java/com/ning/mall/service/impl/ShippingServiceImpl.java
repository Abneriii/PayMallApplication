package com.ning.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ning.mall.dao.ShippingMapper;
import com.ning.mall.enums.ResponseEnum;
import com.ning.mall.form.ShippingForm;
import com.ning.mall.pojo.Shipping;
import com.ning.mall.service.IShippingService;
import com.ning.mall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShippingServiceImpl implements IShippingService {

    @Autowired
    private ShippingMapper shippingMapper;



    @Override
    public ResponseVo<Map<String, Integer>> add(Integer uid, ShippingForm form) {
        Shipping shipping = new Shipping();
        BeanUtils.copyProperties(form, shipping);
        shipping.setUserId(uid);
        int row = shippingMapper.insertSelective(shipping);
        if (row == 0) {
            return ResponseVo.error(ResponseEnum.ERROR,ResponseEnum.ERROR.getDesc());
        }
        //YW---传shipping对象过去后shipping自己的域Id的自增，在这里能查到？
        Map<String, Integer> map = new HashMap<>();
        map.put("shippingId", shipping.getId());
        return ResponseVo.success(ResponseEnum.SUCCESS.getCode(),map);

    }

    @Override
    public ResponseVo delete(Integer uid, Integer shippingId) {
        //先检查，这个shippingId是否与uid对应。
        //dai----mine有问题，dai排查。
//        Shipping shipping=shippingMapper.selectByPrimaryKey(shippingId);
//        if(!shipping.getUserId().equals(uid)){
//            return ResponseVo.error(ResponseEnum.SHIPPING_NOT_EXIST,ResponseEnum.SHIPPING_NOT_EXIST.getDesc());
//        }
//
//        int row=shippingMapper.deleteByPrimaryKey(uid);
//        if(row==0){
//            return ResponseVo.error(ResponseEnum.DELETE_SHIPPING_FAIL,ResponseEnum.DELETE_SHIPPING_FAIL.getDesc());
//        }
//        return ResponseVo.success(ResponseEnum.SUCCESS.getCode(),ResponseEnum.SUCCESS.getDesc());
//
//
        int row = shippingMapper.deleteByIdAndUid(uid, shippingId);
        if (row == 0) {
            return ResponseVo.error(ResponseEnum.DELETE_SHIPPING_FAIL,ResponseEnum.DELETE_SHIPPING_FAIL.getDesc());
        }

        return ResponseVo.success(ResponseEnum.SUCCESS.getCode(),ResponseEnum.SUCCESS.getDesc());

    }

    @Override
    public ResponseVo update(Integer uid, Integer shippingId,ShippingForm form) {
        //mineDai---我的方法dai---判断shippingId是否存在，判断uid和shippingId是否匹配，调用insertselective()给shippingId对应的行重新赋值。
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

    //dai
    @Override
    public ResponseVo<PageInfo> list(Integer uid, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Shipping> shippings = shippingMapper.selectByUid(uid);
        PageInfo pageInfo = new PageInfo(shippings);
        return ResponseVo.success(ResponseEnum.SUCCESS.getCode(),pageInfo);
    }


}
