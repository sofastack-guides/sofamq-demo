/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.sofa.sofamq.example.rocketmq.consumer;

import java.util.Date;
import java.util.List;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.message.MessageExt;

public class MessageListener implements MessageListenerConcurrently, MessageListenerOrderly {
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list,
                                                    ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        for (MessageExt msg : list) {
            System.out.println(
                String.format("%s Consume body[%s] msgId[%s]", new Date(), new String(msg.getBody()), msg.getMsgId()));
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

    @Override
    public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
        for (MessageExt msg : list) {
            System.out.println(
                    String.format("%s Consume body[%s] msgId[%s]", new Date(), new String(msg.getBody()), msg.getMsgId()));
        }
        return ConsumeOrderlyStatus.SUCCESS;
    }
}