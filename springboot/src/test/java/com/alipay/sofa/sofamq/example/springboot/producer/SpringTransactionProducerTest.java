/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.sofa.sofamq.example.springboot.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.sofa.sofamq.client.TransactionHelper;
import com.alipay.sofa.sofamq.example.springboot.config.MqConfig;

import io.openmessaging.api.Message;
import io.openmessaging.api.TransactionalResult;
import io.openmessaging.api.transaction.TransactionProducer;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SpringTransactionProducerTest {

    //事务消息的Producer 已经注册到了spring容器中，后面需要使用时可以直接注入到其它类中
    @Autowired
    private TransactionProducer transactionProducer;

    @Autowired
    private MqConfig            mqConfig;

    @Test
    @Rollback(false)
    public void testSend() {
        transactionA();
    }

    public void transactionA() {
        System.out.println("start exec transactionA");
        Message msg = new Message(mqConfig.getTopic(), mqConfig.getTag(), "transactionA message".getBytes());
        TransactionalResult result = transactionProducer.prepare(msg);
        TransactionHelper.synchronizeSpringTransaction(result);
        System.out.println("send transaction message " + result);
        transactionB();
        System.out.println("end exec transactionA");
    }

    public void transactionB() {
        System.out.println("start exec transactionB");
        Message msg = new Message(mqConfig.getTopic(), mqConfig.getTag(), "transactionB message".getBytes());
        TransactionalResult result = transactionProducer.prepare(msg);
        System.out.println("send transaction message " + result);
        System.out.println("end exec transactionB");
    }
}