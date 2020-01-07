package com.alipay.sofa.sofamq.example.springboot.normal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.alipay.sofa.sofamq.example.springboot.config.MqConfig;

import io.openmessaging.api.Consumer;
import io.openmessaging.api.MessageSelector;
import io.openmessaging.api.OMS;

//正式开发时可以加上 @Configuration 注解，这样服务启动时consumer也启动了
public class SqlConsumerClient {

    @Autowired
    private MqConfig            mqConfig;

    @Autowired
    private DemoMessageListener messageListener;

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public Consumer buildSqlConsumer() {
        Consumer consumer = OMS.builder().driver("sofamq").build(mqConfig.getMqProperties())
            .createConsumer(mqConfig.getMqProperties());
        consumer.subscribe(mqConfig.getTopic(),
            MessageSelector
                .bySql("(TAGS is not null and TAGS in ('TagA', 'TagB')) and (a is not null and a between 0 and 3)"),
            messageListener);
        return consumer;
    }

}
