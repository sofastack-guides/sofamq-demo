/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.sofa.sofamq.example.tcp.producer;

import java.util.Properties;

import com.alipay.sofa.sofamq.client.PropertyKeyConst;
import com.alipay.sofa.sofamq.example.tcp.AccessPoint;
import com.alipay.sofa.sofamq.example.tcp.MqConfig;

import io.openmessaging.api.Message;
import io.openmessaging.api.TransactionalResult;
import io.openmessaging.api.transaction.TransactionProducer;

/**
 * 手动两阶段事务消息 demo
 */
public class TwoPhaseTransactionProducer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.GROUP_ID, MqConfig.GROUP_ID);
        TransactionProducer transactionProducer = AccessPoint.getAccessPoint().createTransactionProducer(properties,
            new LocalTransactionCheckerImpl());
        transactionProducer.start();

        Message message = new Message(MqConfig.TOPIC, MqConfig.TAG, "mq send transaction message test".getBytes());

        TransactionalResult result = transactionProducer.prepare(message);
        System.out.println("send transaction message " + result);
        System.out.println("do something...");
        result.commit();
    }
}