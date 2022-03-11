package com.ning.mall.service.impl;

import com.ning.mall.MallApplicationTests;
import com.ning.mall.enums.ResponseEnum;
import com.ning.mall.form.ShippingForm;
import com.ning.mall.service.IShippingService;
import com.ning.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static org.junit.Assert.*;



@Slf4j
@Transactional
public class ShippingServiceImplTest extends MallApplicationTests {


    @Autowired
    private IShippingService shippingService;

    private Integer uid = 1;

    private ShippingForm form;

    private Integer shippingId;

    @Before
    public void before() {
        ShippingForm form = new ShippingForm();
        form.setReceiverName("王二");
        form.setReceiverAddress("浙江工业大学");
        form.setReceiverCity("杭州");
        form.setReceiverMobile("188123333333");
        form.setReceiverPhone("010123222");
        form.setReceiverProvince("浙江省");
        form.setReceiverDistrict("钱塘区");
        form.setReceiverZip("002222");
        this.form = form;

        add();
    }

    @Test
    public void add() {
//        ShippingForm form = new ShippingForm();
//        form.setReceiverName("王二");
//        form.setReceiverAddress("浙江工业大学");
//        form.setReceiverCity("杭州");
//        form.setReceiverMobile("188123333333");
//        form.setReceiverPhone("010123222");
//        form.setReceiverProvince("浙江省");
//        form.setReceiverDistrict("钱塘区");
//        form.setReceiverZip("002222");
        ResponseVo<Map<String, Integer>> responseVo = shippingService.add(uid, form);
        log.info("result={}", responseVo);
        this.shippingId = responseVo.getData().get("shippingId");
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

    @After
    public void delete() {
        ResponseVo responseVo = shippingService.delete(uid, shippingId);
        log.info("result={}", responseVo);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

//    @Test
//    public void delete() {
//        Integer shippingId=22;
//        ResponseVo responseVo = shippingService.delete(uid, shippingId);
//        log.info("result={}", responseVo);
//        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
//    }


    @Test
    public void update() {
        form.setReceiverCity("杭州");
        ResponseVo responseVo = shippingService.update(uid, shippingId, form);
        log.info("result={}", responseVo);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

    @Test
    public void list() {
        ResponseVo responseVo = shippingService.list(uid, 1, 10);
        log.info("result={}", responseVo);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }
}