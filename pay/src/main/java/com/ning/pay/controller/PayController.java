package com.ning.pay.controller;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayResponse;
import com.ning.pay.service.Impl.PayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.plaf.PanelUI;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static com.lly835.bestpay.enums.BestPayTypeEnum.WXPAY_NATIVE;


@Controller
@RequestMapping("/pay")
@Slf4j
public class PayController {

    @Autowired
    private PayServiceImpl payServiceImpl;


    //目前只支持微信NATIVE支付
    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("amount")BigDecimal amount,
                               @RequestParam("payType") BestPayTypeEnum bestPayTypeEnum){
        /*
        * 调用PayService，拿到响应，返回给前端code_url
        *
        * */
       PayResponse response= payServiceImpl.create(orderId,amount,bestPayTypeEnum);
//        PayResponse response= payServiceImpl.create("222222222222",BigDecimal.valueOf(6));
        if (bestPayTypeEnum == BestPayTypeEnum.WXPAY_NATIVE){
            Map map=new HashMap<>();
            map.put("codeUrl",response.getCodeUrl());
            return new ModelAndView("create",map);
        }
        throw new RuntimeException("暂不支持的支付类型");
    }

    /*
    *异步通知中要处理什么；
    *接受微信支付发来的消息：1.验证签名，证明这是微信平台发来的消息，而非假包 2.验证金额 3.
    * */
    @PostMapping("/notify")
    @ResponseBody
    public String asyncNotify(@RequestBody String notifyData){
        log.info("catch");
    return payServiceImpl.asyncNotify(notifyData);
    }

    @GetMapping("/testRabbitMQ")
    public String test(){
        return payServiceImpl.test();
    }
}
