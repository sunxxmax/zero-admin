package com.sunxx.biz.luke.sales.mapper;

import com.sunxx.biz.luke.sales.domain.SalesPurchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesPurchaseMapper extends JpaRepository<SalesPurchase, Long> {
}
