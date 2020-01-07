package com.alipay.sofa.sofamq.example.tcp.producer;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.alipay.sofa.sofamq.client.PropertyKeyConst;
import com.alipay.sofa.sofamq.example.tcp.AccessPoint;
import com.alipay.sofa.sofamq.example.tcp.MqConfig;

import io.openmessaging.api.Message;
import io.openmessaging.api.Producer;
import io.openmessaging.api.SendResult;

/**
 * MQ发送定时消息示例 Demo
 */
public class SimpleDelayProducer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.GROUP_ID, MqConfig.GROUP_ID);
        Producer producer = AccessPoint.getAccessPoint().createProducer(properties);

        producer.start();

        Message message = new Message("$topic", "YOUR_TAG", "hello world".getBytes());
        // 延时消息，单位毫秒（ms），在指定延迟时间（当前时间之后）进行投递，例如消息在 5 秒后投递
        message.setStartDeliverTime(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(5));
        SendResult sendResult = producer.send(message);
        System.out.println(sendResult);
    }
}
