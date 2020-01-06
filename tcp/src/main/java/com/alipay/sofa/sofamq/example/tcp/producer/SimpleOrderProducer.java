/**
 * Copyright (C) 2010-2016 Alibaba Group Holding Limited
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alipay.sofa.sofamq.example.tcp.producer;

import java.util.Date;
import java.util.Properties;

import com.alipay.sofa.sofamq.client.PropertyKeyConst;
import com.alipay.sofa.sofamq.example.tcp.MqConfig;

import io.openmessaging.api.Message;
import io.openmessaging.api.MessagingAccessPoint;
import io.openmessaging.api.OMS;
import io.openmessaging.api.OMSBuiltinKeys;
import io.openmessaging.api.SendResult;
import io.openmessaging.api.order.OrderProducer;

/**
 * MQ发送普通消息示例 Demo
 */
public class SimpleOrderProducer {

    public static void main(String[] args) {
        Properties credentials = new Properties();
        credentials.setProperty(OMSBuiltinKeys.ACCESS_KEY, MqConfig.ACCESS_KEY);
        credentials.setProperty(OMSBuiltinKeys.SECRET_KEY, MqConfig.SECRET_KEY);

        MessagingAccessPoint accessPoint = OMS.builder().driver("sofamq").endpoint(MqConfig.ENDPOINT)
            .withCredentials(credentials).build();

        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.INSTANCE_ID, MqConfig.INSTANCE);
        properties.setProperty(PropertyKeyConst.GROUP_ID, MqConfig.GROUP_ID);
        OrderProducer producer = accessPoint.createOrderProducer(properties);
        producer.start();

        Message msg = new Message(MqConfig.ORDER_TOPIC, MqConfig.TAG, "mq send order message test".getBytes());
        SendResult sendResult = producer.send(msg, "SHARDING");
        System.out.println(new Date() + " Send mq message success! Topic is:" + MqConfig.ORDER_TOPIC + " msgId is: "
                           + sendResult.getMessageId());
    }
}
