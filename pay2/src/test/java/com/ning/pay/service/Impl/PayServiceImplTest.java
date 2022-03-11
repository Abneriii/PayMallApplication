package com.ning.pay.service.Impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.ning.pay.PaybusinessApplicationTests;
import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class PayServiceImplTest extends PaybusinessApplicationTests {


    @Autowired
    private PayServiceImpl payServiceImpl;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void create() {
        payServiceImpl.create("11189787", BigDecimal.valueOf(0.01), BestPayTypeEnum.WXPAY_NATIVE);

    }


    @Test
    public void sendMQMsg(){
        amqpTemplate.convertAndSend("payNotify","hi");
    }

}