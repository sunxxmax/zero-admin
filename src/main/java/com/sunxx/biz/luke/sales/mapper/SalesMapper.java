package com.sunxx.biz.luke.sales.mapper;

import com.sunxx.biz.luke.sales.domain.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesMapper extends JpaRepository<Sales, Long> {
}
