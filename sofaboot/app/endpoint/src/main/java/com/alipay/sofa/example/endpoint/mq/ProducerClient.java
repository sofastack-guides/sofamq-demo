package com.alipay.sofa.example.endpoint.mq;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alipay.sofa.sofamq.client.PropertyKeyConst;

import io.openmessaging.api.OMS;
import io.openmessaging.api.Producer;

@Configuration
public class ProducerClient {
    @Autowired
    private MqConfig mqConfig;

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public Producer buildProducer() {
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.GROUP_ID, mqConfig.getGroupId());
        Producer producer = OMS.builder().driver("sofamq").build(mqConfig.getMqProperties()).createProducer(properties);
        return producer;
    }
}