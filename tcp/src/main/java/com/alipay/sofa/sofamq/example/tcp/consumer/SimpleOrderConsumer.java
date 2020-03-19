package com.alipay.sofa.sofamq.example.tcp.consumer;

import java.util.Properties;

import com.alipay.sofa.sofamq.client.PropertyKeyConst;
import com.alipay.sofa.sofamq.example.tcp.AccessPoint;
import com.alipay.sofa.sofamq.example.tcp.MqConfig;

import io.openmessaging.api.Message;
import io.openmessaging.api.order.ConsumeOrderContext;
import io.openmessaging.api.order.MessageOrderListener;
import io.openmessaging.api.order.OrderAction;
import io.openmessaging.api.order.OrderConsumer;

/**
 * MQ 接收消息示例 Demo
 */
public class SimpleOrderConsumer {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.GROUP_ID, MqConfig.GROUP_ID);
        OrderConsumer consumer = AccessPoint.getAccessPoint().createOrderedConsumer(properties);
        consumer.subscribe(MqConfig.TOPIC, MqConfig.TAG, new MessageOrderListener() {
            public OrderAction consume(final Message message, final ConsumeOrderContext context) {
                System.out.println(message);
                return OrderAction.Success;
            }
        });
        consumer.start();
        System.out.println("Consumer start success.");

        // 等待固定时间防止进程退出
        try {
            Thread.sleep(200000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
