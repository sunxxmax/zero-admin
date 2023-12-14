package com.sunxx.biz.system.notify.handler;

import com.sunxx.biz.system.notify.entity.Notify;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaProducerHandler {

    private static final String TOPIC_1 = "notify";

    private static final String TOPIC_2 = "notify_group";

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendNotification(String notify) {
        log.info("producer notify:{}", notify);
        kafkaTemplate.send(TOPIC_1, notify);

    }
}
