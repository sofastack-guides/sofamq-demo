package com.alipay.sofa.sofamq.example.tcp.consumer;

import java.util.Properties;

import com.alipay.sofa.sofamq.client.PropertyKeyConst;
import com.alipay.sofa.sofamq.example.tcp.AccessPoint;
import com.alipay.sofa.sofamq.example.tcp.MqConfig;

import io.openmessaging.api.Consumer;
import io.openmessaging.api.MessagingAccessPoint;
import io.openmessaging.api.OMS;
import io.openmessaging.api.OMSBuiltinKeys;

/**
 * MQ 接收消息示例 Demo
 */
public class SimpleConsumer {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.GROUP_ID, MqConfig.GROUP_ID);
        Consumer consumer = AccessPoint.getAccessPoint().createConsumer(properties);
        consumer.subscribe(MqConfig.TOPIC, MqConfig.TAG, new MessageListenerImpl());
        consumer.start();
        System.out.println("Consumer start success.");

        //等待固定时间防止进程退出
        try {
            Thread.sleep(200000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
