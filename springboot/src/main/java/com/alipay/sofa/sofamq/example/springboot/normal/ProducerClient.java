package com.alipay.sofa.sofamq.example.springboot.normal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alipay.sofa.sofamq.example.springboot.config.MqConfig;

import io.openmessaging.api.OMS;
import io.openmessaging.api.Producer;

@Configuration
public class ProducerClient {

    @Autowired
    private MqConfig mqConfig;

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public Producer buildProducer() {
        Producer producer = OMS.builder().driver("sofamq").build(mqConfig.getMqProperties())
            .createProducer(mqConfig.getMqProperties());
        return producer;
    }

}
