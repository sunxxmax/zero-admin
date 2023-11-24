package com.sunxx.biz.luke.purchase.service.impl;

import com.sunxx.biz.luke.purchase.domain.Purchase;
import com.sunxx.common.enums.luke.PurchaseStatus;
import com.sunxx.biz.luke.purchase.mapper.PurchaseMapper;
import com.sunxx.biz.luke.purchase.service.PurchaseService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Resource
    private PurchaseMapper purchaseMapper;

    @Override
    public Page<Purchase> page(Pageable pageable) {
        return purchaseMapper.findAll(pageable);
    }

    @Override
    public void create(Purchase source) {
        source.setStatus(PurchaseStatus.PAID);
        purchaseMapper.save(source);
    }

    @Override
    public List<Purchase> list4Usable() {
        List<PurchaseStatus> list = List.of(PurchaseStatus.PAID, PurchaseStatus.SHIPPED, PurchaseStatus.RECEIVED);
        return purchaseMapper.findAllByStatusIn(list);
    }
}
