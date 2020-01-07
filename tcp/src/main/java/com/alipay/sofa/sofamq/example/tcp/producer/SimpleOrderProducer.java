package com.alipay.sofa.sofamq.example.tcp.producer;

import java.util.Date;
import java.util.Properties;

import com.alipay.sofa.sofamq.client.PropertyKeyConst;
import com.alipay.sofa.sofamq.example.tcp.AccessPoint;
import com.alipay.sofa.sofamq.example.tcp.MqConfig;

import io.openmessaging.api.Message;
import io.openmessaging.api.SendResult;
import io.openmessaging.api.order.OrderProducer;

/**
 * MQ发送普通消息示例 Demo
 */
public class SimpleOrderProducer {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.GROUP_ID, MqConfig.GROUP_ID);
        OrderProducer producer = AccessPoint.getAccessPoint().createOrderProducer(properties);
        producer.start();

        Message msg = new Message(MqConfig.TOPIC, MqConfig.TAG, "mq send order message test".getBytes());
        SendResult sendResult = producer.send(msg, "SHARDING");
        System.out.println(new Date() + " Send mq message success! Topic is:" + MqConfig.TOPIC + " msgId is: "
                           + sendResult.getMessageId());
    }
}
