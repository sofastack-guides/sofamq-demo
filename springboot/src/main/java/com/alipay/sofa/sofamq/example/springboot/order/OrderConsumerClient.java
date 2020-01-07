package com.alipay.sofa.sofamq.example.springboot.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.alipay.sofa.sofamq.example.springboot.config.MqConfig;

import io.openmessaging.api.OMS;
import io.openmessaging.api.order.OrderConsumer;

//项目中加上 @Configuration 注解，这样服务启动时consumer也启动了
public class OrderConsumerClient {

    @Autowired
    private MqConfig                 mqConfig;

    @Autowired
    private OrderDemoMessageListener messageListener;

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public OrderConsumer buildOrderConsumer() {
        OrderConsumer consumer = OMS.builder().driver("sofamq").build(mqConfig.getMqProperties())
            .createOrderedConsumer(mqConfig.getMqProperties());
        consumer.subscribe(mqConfig.getTopic(), mqConfig.getTag(), messageListener);
        return consumer;
    }

}
