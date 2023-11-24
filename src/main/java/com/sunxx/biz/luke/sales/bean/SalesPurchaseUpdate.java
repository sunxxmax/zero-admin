package com.sunxx.biz.luke.sales.bean;

import java.math.BigDecimal;

public record SalesPurchaseUpdate(
        Long id,
        BigDecimal payback
) {
}
