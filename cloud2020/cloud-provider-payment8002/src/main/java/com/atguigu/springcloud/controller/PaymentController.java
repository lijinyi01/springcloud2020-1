package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String servicePort;
    //@Autowired SPRING
    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result =paymentService.create(payment);
      log.info("**插入结果"+result);
        if (result > 0){
            return  new CommonResult(200,"返回成功,serverPort:"+servicePort,result);
        }else {
            return new CommonResult(444,"返回失败,serverPort:"+servicePort,null);
        }
    }

    @GetMapping (value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment =paymentService.getPaymentById(id);
        log.info("**插入结果"+payment);
        if (payment !=null){
            return  new CommonResult(200,"返回成功",payment);
        }else {
            return new CommonResult(444,"查不到id"+id,null);
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CommonResult<T> {
        private  Integer code;
        private  String message;
        private  T  date;

     public CommonResult(Integer code,String message){
         this(code,message,null );
        }
 }
}
