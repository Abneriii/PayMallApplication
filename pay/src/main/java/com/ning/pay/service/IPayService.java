package com.ning.pay.service;


import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayResponse;

import java.math.BigDecimal;

public interface IPayService {
    /*
    * 接口的访问权限没有写，但是实现类中写了
    * */

    //mine:接受订单号和金额，然后向微信支付平台发起请求，从微信平台发来的消息中拿到支付二维码的文本形式code_url。
     PayResponse create(String orderId, BigDecimal amount, BestPayTypeEnum bestPayTypeEnum);//mine?:为什么返回PayResponse

    String asyncNotify(String notifyData);

    String test();

}
