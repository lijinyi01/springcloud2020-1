package com.atguigu.srpingcloud.service;
import java.util.Random;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.commons.util.IdUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentInfo_OK(Integer id){
        return "线程池: "+ Thread.currentThread().getName()+"paymentInfo_OK,id: "+ id +"\t" +"haha";
    }


    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String paymentInfo_timeout(Integer id){

          int timeNummber=5;
         try{ TimeUnit.SECONDS.sleep(timeNummber);}catch (InterruptedException e){e.printStackTrace();}
        return "线程池: "+ Thread.currentThread().getName()+"paymentInfo_timeout,id: "+ id+"\t"+"haoshis :"+timeNummber;
    }

    public String paymentInfo_TimeOutHandler(Integer id){
        return  "/(haha)调用支付接口超时或异常:\t"+"\t 当前线程名字FFISHI"+Thread.currentThread().getName();
    }


    //rongduan

   @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
           @HystrixProperty(name ="circuitBreaker.enabled",value="true"),//开启断路器
           @HystrixProperty(name ="circuitBreaker.requestVolumeThreshold",value="10"),//请求次数超过了峰值，熔断器将会关闭
           @HystrixProperty(name ="circuitBreaker.sleepWindowInMilliseconds", value="10000"),//时间范围 快照时间窗默认10s
           @HystrixProperty(name ="circuitBreaker.errorThresholdPercentage",value="60"),})//失败率多少跳闸
     public String paymentCirucitBreaker(@PathVariable("id") Integer id){
        if(id < 0){
            throw new RuntimeException("*****ID不能是负数");
        }
          String serialNumber= IdUtil.simpleUUID();
          return Thread.currentThread().getName()+"\t"+"调用成功，流水号:"+ serialNumber;
  }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id,不能是负数，请重试，//:: ID:"+id;
    }
}
