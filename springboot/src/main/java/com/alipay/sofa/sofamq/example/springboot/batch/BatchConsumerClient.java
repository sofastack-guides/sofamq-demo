package com.alipay.sofa.sofamq.example.springboot.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.alipay.sofa.sofamq.example.springboot.config.MqConfig;

import io.openmessaging.api.OMS;
import io.openmessaging.api.batch.BatchConsumer;

//项目中加上 @Configuration 注解，这样服务启动时consumer也启动了
public class BatchConsumerClient {

    @Autowired
    private MqConfig                 mqConfig;

    @Autowired
    private BatchDemoMessageListener messageListener;

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public BatchConsumer buildBatchConsumer() {
        BatchConsumer consumer = OMS.builder().driver("sofamq").build(mqConfig.getMqProperties())
            .createBatchConsumer(mqConfig.getMqProperties());
        consumer.subscribe(mqConfig.getTopic(), mqConfig.getTag(), messageListener);
        return consumer;
    }

}
