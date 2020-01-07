package com.alipay.sofa.sofamq.example.springboot.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alipay.sofa.sofamq.example.springboot.config.MqConfig;

import io.openmessaging.api.OMS;
import io.openmessaging.api.order.OrderProducer;

@Configuration
public class OrderProducerClient {

    @Autowired
    private MqConfig mqConfig;

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public OrderProducer buildOrderProducer() {
        OrderProducer producer = OMS.builder().driver("sofamq").build(mqConfig.getMqProperties())
            .createOrderProducer(mqConfig.getMqProperties());
        return producer;
    }

}
