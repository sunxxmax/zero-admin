package com.sunxx.biz.luke.sales.bean;

import java.math.BigDecimal;
import java.util.List;

public record SalesCreate(
        BigDecimal loss,
        String stall,
        String remark,
        List<SalesPurchaseCreate> items
) {
}
