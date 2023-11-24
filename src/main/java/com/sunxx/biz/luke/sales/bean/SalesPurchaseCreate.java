package com.sunxx.biz.luke.sales.bean;

import java.math.BigDecimal;

public record SalesPurchaseCreate(
        Long purchase,
        BigDecimal payback
) {
}
