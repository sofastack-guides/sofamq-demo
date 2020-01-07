package com.alipay.sofa.sofamq.example.tcp.producer;

import java.util.Properties;

import com.alipay.sofa.sofamq.client.PropertyKeyConst;
import com.alipay.sofa.sofamq.example.tcp.AccessPoint;
import com.alipay.sofa.sofamq.example.tcp.MqConfig;

import io.openmessaging.api.Message;
import io.openmessaging.api.SendResult;
import io.openmessaging.api.transaction.LocalTransactionExecuter;
import io.openmessaging.api.transaction.TransactionProducer;
import io.openmessaging.api.transaction.TransactionStatus;

/**
 * MQ 发送事务消息示例 Demo
 */
public class SimpleTransactionProducer {

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.GROUP_ID, MqConfig.GROUP_ID);
        TransactionProducer transactionProducer = AccessPoint.getAccessPoint().createTransactionProducer(properties,
            new LocalTransactionCheckerImpl());
        transactionProducer.start();

        Message message = new Message(MqConfig.TOPIC, MqConfig.TAG, "mq send transaction message test".getBytes());

        SendResult sendResult = transactionProducer.send(message, new LocalTransactionExecuter() {
            public TransactionStatus execute(Message msg, Object arg) {
                System.out.println("执行本地事务, 并根据本地事务的状态提交TransactionStatus.");
                return TransactionStatus.CommitTransaction;
            }
        }, null);
        System.out.println("Send transaction message success.");
    }
}