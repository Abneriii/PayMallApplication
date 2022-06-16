package com.ning.pay.config;

import com.lly835.bestpay.config.WxPayConfig;
import com.lly835.bestpay.service.BestPayService;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BestPayConfig {

    @Bean//什么意思
    public BestPayService bestPayService(){
        WxPayConfig wxPayConfig = new WxPayConfig();
        wxPayConfig.setAppId("wx3e6b9f1c5a7ff034"); //公众号Id

        //支付商户资料
        wxPayConfig.setMchId("1614433647");
        wxPayConfig.setMchKey("Aa111111111122222222223333333333");
        wxPayConfig.setNotifyUrl("http://abner66.natapp1.cc/pay/notify");//商户接受微信平台发来支付成功的异步通知的地址


        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayConfig(wxPayConfig);
        return bestPayService;
    }

}
