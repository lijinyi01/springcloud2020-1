package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl<PaymentMapper,payment> extends ServiceImpl implements PaymentService {
    //@Autowired
 //   @Resource
 //   private PaymentDao paymentDao;
 //   public int  create(Payment payment){

   //     return paymentDao.create(payment);
 //   };
    //  public  Payment getPaymentById(@Param("id") Long id)
  //  public  Payment getPaymentById(Long id){
   //     return  paymentDao.getPaymentById(id);
  // };

}
