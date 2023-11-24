package com.sunxx.biz.luke.sales.service;

import com.sunxx.biz.luke.sales.domain.Sales;
import com.sunxx.biz.luke.sales.bean.SalesCreate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface SalesService {

    Page<Sales> page(Pageable pageable);

    void create(SalesCreate param);

    Sales detail(Long id);

    void finished(Long id);

    void payback(Long id, BigDecimal payback);
}
