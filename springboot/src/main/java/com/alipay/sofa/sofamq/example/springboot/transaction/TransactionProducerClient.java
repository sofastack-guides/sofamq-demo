package com.alipay.sofa.sofamq.example.springboot.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alipay.sofa.sofamq.example.springboot.config.MqConfig;

import io.openmessaging.api.OMS;
import io.openmessaging.api.transaction.TransactionProducer;

@Configuration
public class TransactionProducerClient {

    @Autowired
    private MqConfig                    mqConfig;

    @Autowired
    private DemoLocalTransactionChecker localTransactionChecker;

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public TransactionProducer buildTransactionProducer() {
        TransactionProducer producer = OMS.builder().driver("sofamq").build(mqConfig.getMqProperties())
            .createTransactionProducer(mqConfig.getMqProperties(), localTransactionChecker);
        return producer;
    }

}
