/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.sofa.sofamq.example.rocketmq.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.consumer.rebalance.AllocateMessageQueueAveragely;

import com.alipay.sofa.sofamq.example.rocketmq.MqConfig;

public class SimpleOrderConsumer {
    public static void main(String... args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(MqConfig.INSTANCE, MqConfig.GROUP_ID,
            MqConfig.RPC_HOOK, new AllocateMessageQueueAveragely(), true, null);
        consumer.setNamesrvAddr(MqConfig.NAMESRV);
        consumer.subscribe(MqConfig.TOPIC, MqConfig.TAG);
        consumer.registerMessageListener(((MessageListenerOrderly) new MessageListener()));
        consumer.start();
    }
}