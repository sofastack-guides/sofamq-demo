/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.sofa.sofamq.example.rocketmq.producer;

import java.util.Date;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.MessageExt;

import com.alipay.sofa.sofamq.example.rocketmq.MqConfig;

public class SimpleProducer {

    public static void main(String... args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer(MqConfig.GROUP_ID, MqConfig.RPC_HOOK, true, null);
        producer.setNamesrvAddr(MqConfig.NAMESRV);
        producer.setNamespace(MqConfig.INSTANCE);
        producer.start();

        MessageExt msg = new MessageExt();
        msg.setTopic(MqConfig.TOPIC);
        msg.setBody((new Date() + " Hello world").getBytes());
        msg.setTags(MqConfig.TAG);
        String timestamp = Long.toString(System.currentTimeMillis());
        msg.setKeys(timestamp);
        SendResult sendResult = producer.send(msg);
        System.out.println(new Date() + " " + sendResult);
    }

}