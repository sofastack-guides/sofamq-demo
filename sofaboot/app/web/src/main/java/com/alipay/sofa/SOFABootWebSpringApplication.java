package com.alipay.sofa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

@ImportResource({ "classpath*:META-INF/sofamq-demo-sofaboot/*.xml" })
@org.springframework.boot.autoconfigure.SpringBootApplication(scanBasePackages = "com.alipay.sofa.example")
public class SOFABootWebSpringApplication {

    private static final Logger logger = LoggerFactory.getLogger(SOFABootWebSpringApplication.class);

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SOFABootWebSpringApplication.class);
        ApplicationContext applicationContext = springApplication.run(args);
    }
}
