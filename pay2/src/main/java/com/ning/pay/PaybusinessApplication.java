package com.ning.pay;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ning.pay.dao")//这句之前没写就报错说找不到UserMapper，但视频里也没写，可能自己别的地方有问题。
public class PaybusinessApplication {
    /*
    * mine:这个类是做什么的？
    * */
    public static void main(String[] args) {
        SpringApplication.run(PaybusinessApplication.class, args);
    }

}
