package com.alipay.sofa.sofamq.example.tcp.producer;

import io.openmessaging.api.Message;
import io.openmessaging.api.transaction.LocalTransactionChecker;
import io.openmessaging.api.transaction.TransactionStatus;

/**
 * MQ 发送事务消息本地Check接口实现类
 */
public class LocalTransactionCheckerImpl implements LocalTransactionChecker {
    public TransactionStatus check(Message msg) {
        System.out.println("收到事务消息的回查请求, MsgId: " + msg.getMsgID());
        return TransactionStatus.CommitTransaction;
    }
}
