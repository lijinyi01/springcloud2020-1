package com.atguigu.springcloud.service;


import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService {

    @Override
    public String paymentInfo_ok(Integer id) {
        return "PaymentFallbackServiceok";
    }

    @Override
    public String paymentInfotimeout(Integer id) {
        return "PaymentFallbackServiceotimeout";
    }
}
