package com.alipay.sofa.sofamq.example.springboot.normal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.alipay.sofa.sofamq.example.springboot.config.MqConfig;

import io.openmessaging.api.Consumer;
import io.openmessaging.api.OMS;

//项目中加上 @Configuration 注解，这样服务启动时consumer也启动了
public class ConsumerClient {

    @Autowired
    private MqConfig            mqConfig;

    @Autowired
    private DemoMessageListener messageListener;

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public Consumer buildConsumer() {
        Consumer consumer = OMS.builder().driver("sofamq").build(mqConfig.getMqProperties())
            .createConsumer(mqConfig.getMqProperties());
        consumer.subscribe(mqConfig.getTopic(), mqConfig.getTag(), messageListener);
        return consumer;
    }

}
