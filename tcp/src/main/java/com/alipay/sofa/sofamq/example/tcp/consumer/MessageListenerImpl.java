package com.alipay.sofa.sofamq.example.tcp.consumer;

import java.util.Date;

import io.openmessaging.api.Action;
import io.openmessaging.api.ConsumeContext;
import io.openmessaging.api.Message;
import io.openmessaging.api.MessageListener;

/**
 * MQ消息处理类
 */
public class MessageListenerImpl implements MessageListener {
    public Action consume(Message message, ConsumeContext consumeContext) {
        System.out.println(
            new Date() + " Receive message, Topic is:" + message.getTopic() + ", MsgId is:" + message.getMsgID());
        //如果想测试消息重投的功能,可以将Action.CommitMessage 替换成Action.ReconsumeLater
        return Action.CommitMessage;
    }
}
