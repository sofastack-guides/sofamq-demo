package com.alipay.sofa.sofamq.example.tcp.producer;

import java.util.Properties;

import com.alipay.sofa.sofamq.client.PropertyKeyConst;
import com.alipay.sofa.sofamq.example.tcp.AccessPoint;
import com.alipay.sofa.sofamq.example.tcp.MqConfig;

import io.openmessaging.api.Message;
import io.openmessaging.api.Producer;
import io.openmessaging.api.SendResult;

/**
 * MQ发送普通消息示例 Demo
 */
public class SimpleProducer {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.GROUP_ID, MqConfig.GROUP_ID);
        Producer producer = AccessPoint.getAccessPoint().createProducer(properties);
        producer.start();

        Message message = new Message(MqConfig.TOPIC, MqConfig.TAG, "hello world".getBytes());
        SendResult sendResult = producer.send(message);
        System.out.println(sendResult);
    }
}
