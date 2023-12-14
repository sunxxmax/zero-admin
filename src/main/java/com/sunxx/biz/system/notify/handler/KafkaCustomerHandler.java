package com.sunxx.biz.system.notify.handler;

import com.sunxx.biz.system.notify.entity.Notify;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaCustomerHandler {

    @KafkaListener(topics = {"notify"})
    public void receiveNotification(String notify, Acknowledgment acknowledgment) {
        log.info("received notify:{}", notify);
        // 在这里添加处理通知的逻辑

        // 手动提交
        acknowledgment.acknowledge();
    }

}
