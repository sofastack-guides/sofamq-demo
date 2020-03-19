/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.alipay.sofa.sofamq.example.tcp;

import com.alipay.sofa.sofamq.client.PropertyKeyConst;
import io.openmessaging.api.MessagingAccessPoint;
import io.openmessaging.api.OMS;
import io.openmessaging.api.OMSBuiltinKeys;

import java.util.Properties;

public class AccessPoint {

    public static MessagingAccessPoint getAccessPoint() {
        Properties credentials = new Properties();
        credentials.setProperty(OMSBuiltinKeys.ACCESS_KEY, MqConfig.ACCESS_KEY);
        credentials.setProperty(OMSBuiltinKeys.SECRET_KEY, MqConfig.SECRET_KEY);

        Properties ext = new Properties();
        ext.setProperty(PropertyKeyConst.INSTANCE_ID, MqConfig.INSTANCE);
        ext.setProperty(PropertyKeyConst.DATA_CENTER, MqConfig.DATA_CENTER);

        MessagingAccessPoint accessPoint = OMS.builder().driver("sofamq").endpoint(MqConfig.ENDPOINT)
            .withCredentials(credentials).build(ext);
        return accessPoint;
    }

}