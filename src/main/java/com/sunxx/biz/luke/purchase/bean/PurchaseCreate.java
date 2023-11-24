package com.sunxx.biz.luke.purchase.bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PurchaseCreate(
        String no,
        String name,
        Integer num,
        BigDecimal price,
        BigDecimal payable,
        BigDecimal paid,
        Long buyer,
        String channel,
        LocalDateTime buyAt,
        String transport
) {
}
