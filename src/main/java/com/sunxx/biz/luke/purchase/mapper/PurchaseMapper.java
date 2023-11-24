package com.sunxx.biz.luke.purchase.mapper;

import com.sunxx.biz.luke.purchase.domain.Purchase;
import com.sunxx.common.enums.luke.PurchaseStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseMapper extends JpaRepository<Purchase, Long> {

    List<Purchase> findAllByStatusIn(List<PurchaseStatus> list);
}
