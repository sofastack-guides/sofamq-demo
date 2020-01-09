package com.alipay.sofa.sofamq.example.springboot.config;

import java.util.Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.alipay.sofa.sofamq.client.PropertyKeyConst;

@Configuration
@ConfigurationProperties(prefix = "sofamq")
public class MqConfig {

    private String accessKey;
    private String secretKey;
    private String endpoint;
    private String instanceId;
    private String dataCenter;
    private String topic;
    private String groupId;
    private String tag;

    public Properties getMqProperties() {
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.ACCESS_KEY, this.accessKey);
        properties.setProperty(PropertyKeyConst.SECRET_KEY, this.secretKey);
        properties.setProperty(PropertyKeyConst.ENDPOINT, this.endpoint);
        properties.setProperty(PropertyKeyConst.INSTANCE_ID, this.instanceId);
        properties.setProperty(PropertyKeyConst.DATA_CENTER, this.dataCenter);
        properties.setProperty(PropertyKeyConst.GROUP_ID, this.groupId);
        return properties;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getDataCenter() {
        return dataCenter;
    }

    public void setDataCenter(String dataCenter) {
        this.dataCenter = dataCenter;
    }
}
