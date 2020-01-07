package com.alipay.sofa.sofamq.example.springboot.order;

import org.springframework.stereotype.Component;

import io.openmessaging.api.Message;
import io.openmessaging.api.order.ConsumeOrderContext;
import io.openmessaging.api.order.MessageOrderListener;
import io.openmessaging.api.order.OrderAction;

@Component
public class OrderDemoMessageListener implements MessageOrderListener {
    @Override
    public OrderAction consume(final Message message, final ConsumeOrderContext context) {
        System.out.println("Receive: " + message);
        try {
            //do something..
            return OrderAction.Success;
        } catch (Exception e) {
            //消费失败，挂起当前队列
            return OrderAction.Suspend;
        }
    }
}
