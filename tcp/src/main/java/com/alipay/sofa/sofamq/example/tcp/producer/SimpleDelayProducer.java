package com.alipay.sofa.sofamq.example.tcp.producer;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.alipay.sofa.sofamq.client.PropertyKeyConst;
import com.alipay.sofa.sofamq.example.tcp.MqConfig;

import io.openmessaging.api.Message;
import io.openmessaging.api.MessagingAccessPoint;
import io.openmessaging.api.OMS;
import io.openmessaging.api.OMSBuiltinKeys;
import io.openmessaging.api.Producer;
import io.openmessaging.api.SendResult;

/**
 * MQ发送定时消息示例 Demo
 */
public class SimpleDelayProducer {
    public static void main(String[] args) {
        Properties credentials = new Properties();
        credentials.setProperty(OMSBuiltinKeys.ACCESS_KEY, MqConfig.ACCESS_KEY);
        credentials.setProperty(OMSBuiltinKeys.SECRET_KEY, MqConfig.SECRET_KEY);

        MessagingAccessPoint accessPoint = OMS.builder().driver("sofamq").endpoint(MqConfig.ENDPOINT)
            .withCredentials(credentials).build();

        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.INSTANCE_ID, MqConfig.INSTANCE);
        properties.setProperty(PropertyKeyConst.GROUP_ID, MqConfig.GROUP_ID);
        Producer producer = accessPoint.createProducer(properties);

        producer.start();

        Message message = new Message("$topic", "YOUR_TAG", "hello world".getBytes());
        // 延时消息，单位毫秒（ms），在指定延迟时间（当前时间之后）进行投递，例如消息在 5 秒后投递
        message.setStartDeliverTime(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(5));
        SendResult sendResult = producer.send(message);
        System.out.println(sendResult);
    }
}
