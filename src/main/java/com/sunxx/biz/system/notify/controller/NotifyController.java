package com.sunxx.biz.system.notify.controller;


import com.sunxx.biz.system.notify.entity.Notify;
import com.sunxx.biz.system.notify.handler.KafkaProducerHandler;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/notify")
public class NotifyController {

    @Resource
    private KafkaProducerHandler kafkaProducerHandler;


    @PostMapping(value = "/send")
    public void send(String message) {
        log.info("message:{}", message);
        kafkaProducerHandler.sendNotification(message);
    }
}
