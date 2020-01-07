package com.alipay.sofa.sofamq.example.springboot.transaction;

import org.springframework.stereotype.Component;

import io.openmessaging.api.Message;
import io.openmessaging.api.transaction.LocalTransactionChecker;
import io.openmessaging.api.transaction.TransactionStatus;

@Component
public class DemoLocalTransactionChecker implements LocalTransactionChecker {
    @Override
    public TransactionStatus check(Message msg) {
        System.out.println("开始回查本地事务状态");
        return TransactionStatus.CommitTransaction; //根据本地事务状态检查结果返回不同的TransactionStatus
    }
}
