package com.alipay.sofa.sofamq.example.springboot.batch;

import java.util.List;

import org.springframework.stereotype.Component;

import io.openmessaging.api.Action;
import io.openmessaging.api.ConsumeContext;
import io.openmessaging.api.Message;
import io.openmessaging.api.batch.BatchMessageListener;

@Component
public class BatchDemoMessageListener implements BatchMessageListener {

    @Override
    public Action consume(final List<Message> messages, final ConsumeContext context) {
        System.out.println("Receive: " + messages.size() + " messages");
        for (Message message : messages) {
            System.out.println(message);
        }
        try {
            //do something..
            return Action.CommitMessage;
        } catch (Exception e) {
            //消费失败
            return Action.ReconsumeLater;
        }
    }
}
