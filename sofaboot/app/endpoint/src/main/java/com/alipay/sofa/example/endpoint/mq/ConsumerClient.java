package com.alipay.sofa.example.endpoint.mq;

import java.util.Date;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alipay.sofa.healthcheck.startup.SofaBootAfterReadinessCheckCallback;
import com.alipay.sofa.sofamq.client.PropertyKeyConst;

import io.openmessaging.api.Action;
import io.openmessaging.api.ConsumeContext;
import io.openmessaging.api.Consumer;
import io.openmessaging.api.Message;
import io.openmessaging.api.MessageListener;
import io.openmessaging.api.OMS;

@Configuration
public class ConsumerClient implements SofaBootAfterReadinessCheckCallback {
    @Autowired
    private MqConfig mqConfig;

    @Bean(destroyMethod = "shutdown")
    public Consumer buildConsumer() {
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.GROUP_ID, mqConfig.getGroupId());
        // LDC should set submode
        //        properties.setProperty(PropertyKeyConst.LDC_SUB_MODE, LdcSubMode.DEFAULT.name());
        Consumer consumer = OMS.builder().driver("sofamq").build(mqConfig.getMqProperties()).createConsumer(properties);
        consumer.subscribe(mqConfig.getTopic(), mqConfig.getTag(), new MessageListener() {
            @Override
            public Action consume(Message message, ConsumeContext context) {
                System.out.println(String.format("%s consume body[%s] msgId[%s]", new Date(),
                    new String(message.getBody()), message.getMsgID()));
                return Action.CommitMessage;
            }
        });
        return consumer;
    }

    @Override
    public Health onHealthy(ApplicationContext applicationContext) {
        Consumer consumer = buildConsumer();
        consumer.start();
        return Health.up().build();
    }
}