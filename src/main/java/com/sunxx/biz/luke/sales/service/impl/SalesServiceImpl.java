package com.sunxx.biz.luke.sales.service.impl;

import com.sunxx.biz.luke.purchase.domain.Purchase;
import com.sunxx.biz.luke.purchase.mapper.PurchaseMapper;
import com.sunxx.biz.luke.sales.bean.SalesCreate;
import com.sunxx.biz.luke.sales.domain.Sales;
import com.sunxx.biz.luke.sales.domain.SalesPurchase;
import com.sunxx.biz.luke.sales.mapper.SalesPurchaseMapper;
import com.sunxx.biz.luke.sales.mapper.SalesMapper;
import com.sunxx.biz.luke.sales.mapper.SalesMapstruct;
import com.sunxx.biz.luke.sales.service.SalesService;
import com.sunxx.common.enums.luke.PurchaseStatus;
import com.sunxx.common.enums.luke.SalesStatus;
import com.sunxx.exception.BizException;
import com.sunxx.util.RandomUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Slf4j
@Service
@Transactional
public class SalesServiceImpl implements SalesService {

    @Resource
    private SalesMapper salesMapper;
    @Resource
    private SalesMapstruct salesMapstruct;
    @Resource
    private PurchaseMapper purchaseMapper;
    @Resource
    private SalesPurchaseMapper salesPurchaseMapper;

    @Override
    public Page<Sales> page(Pageable pageable) {
        return salesMapper.findAll(pageable);
    }

    @Override
    public void create(SalesCreate param) {

        Sales entity = salesMapstruct.create2Entity(param);
        entity.setNo(RandomUtils.generateOrderNumber());

        entity.setStatus(SalesStatus.PAYING);

        Set<SalesPurchase> salesPurchaseSet = new HashSet<>();
        List<Purchase> purchaseList = new ArrayList<>();

        param.items().forEach(create -> {

            Purchase purchase = purchaseMapper.findById(create.purchase()).orElseThrow(() -> new BizException("采购单不存在"));
            purchase.setStatus(PurchaseStatus.FINISHED);
            purchaseList.add(purchase);

            SalesPurchase salesPurchase = SalesPurchase.builder()
                    .purchase(Purchase.builder().id(create.purchase()).build())
                    .payback(create.payback())
                    .sales(entity)
                    .build();
            salesPurchaseSet.add(salesPurchase);
        });
        entity.setItems(salesPurchaseSet);

        purchaseMapper.saveAll(purchaseList);
        salesMapper.saveAndFlush(entity);
    }

    @Override
    public Sales detail(Long id) {
        return salesMapper.findById(id).orElseThrow(() -> new BizException("出货单不存在"));
    }

    @Override
    public void finished(Long id) {
        Sales sales = salesMapper.findById(id).orElseThrow(() -> new BizException("出货单不存在"));
        sales.setStatus(SalesStatus.FINISHED);
        salesMapper.save(sales);
    }

    @Override
    public void payback(Long id, BigDecimal payback) {
        SalesPurchase salesPurchase = salesPurchaseMapper.findById(id).orElseThrow(() -> new BizException("出货单不存在"));
        salesPurchase.setPayback(payback);

        salesPurchaseMapper.save(salesPurchase);

        Sales sales = salesPurchase.getSales();

        if (sales.getItems().stream().allMatch(item -> Objects.nonNull(item.getPayback()))) {
            sales.setStatus(SalesStatus.PAYBACK);
            salesMapper.save(sales);
        }
    }
}
