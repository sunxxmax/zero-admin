package com.sunxx.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RandomUtils {

    // 生成以时间开始的随机订单号
    // 2021-08-01 12:34:56 -> 20210801123456 + 100 ~ 999
    public static String generateOrderNumber() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");
        String time = now.format(formatter);

        return time + (int) ((Math.random() * 9 + 1) * 100);
    }
}
