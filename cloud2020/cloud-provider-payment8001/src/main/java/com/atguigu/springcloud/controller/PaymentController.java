package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;

import com.atguigu.springcloud.service.impl.PaymentServiceImpl;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    //@Autowired SPRING

    @Value("${server.port}")
   private String servicePort;

   // @Autowired
    @Resource
  private PaymentService paymentService;

    //  @Autowired
   @Resource
 private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        boolean result =paymentService.save(payment);
      log.info("**插入结果"+result);
        if (result = true){
            return  new CommonResult(200,"返回成功,serveice:"+ result);
        }else {
            return new CommonResult(444,"返回失败,serverPort:"+ null);
        }
    }

    @GetMapping (value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment =(Payment) paymentService.getById(id);
        log.info("**插入结果"+payment);
        if (payment !=null){
            return  new CommonResult(200,"返回成功,serveice:",payment);
        }else {
            return new CommonResult(444,"返回失败,serverPort:",null);
        }
    }
    @GetMapping(value="/payment/dicovery")
  public Object discovery(){
       List<String> services = discoveryClient.getServices();
        for(String element : services){
            log.info("****: "+ element);
        }
      List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for(ServiceInstance instance : instances){
          log.info("****: "+ instance.getServiceId()+"\t" + instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
    return  this.discoveryClient;
   }

    @GetMapping(value = "/payment/feign/timeout")
    public  String paymentFeignTimeout(){
     try {
        TimeUnit.SECONDS.sleep(3);
    }catch (InterruptedException e){e.printStackTrace();}
     return  servicePort;
   }

}
