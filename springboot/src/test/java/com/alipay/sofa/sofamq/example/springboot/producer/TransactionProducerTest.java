package com.alipay.sofa.sofamq.example.springboot.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alipay.sofa.sofamq.example.springboot.config.MqConfig;

import io.openmessaging.api.Message;
import io.openmessaging.api.SendResult;
import io.openmessaging.api.transaction.LocalTransactionExecuter;
import io.openmessaging.api.transaction.TransactionProducer;
import io.openmessaging.api.transaction.TransactionStatus;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionProducerTest {

    //事务消息的Producer 已经注册到了spring容器中，后面需要使用时可以直接注入到其它类中
    @Autowired
    private TransactionProducer transactionProducer;

    @Autowired
    private MqConfig            mqConfig;

    @Test
    public void testSend() {

        Message msg = new Message(mqConfig.getTopic(), "TagA", "Hello MQ".getBytes());
        SendResult sendResult = transactionProducer.send(msg, new LocalTransactionExecuter() {
            @Override
            public TransactionStatus execute(Message msg, Object arg) {
                System.out.println("执行本地事务");
                return TransactionStatus.CommitTransaction; //根据本地事务执行结果来返回不同的TransactionStatus
            }
        }, null);
        System.out.println(sendResult);

    }

}
