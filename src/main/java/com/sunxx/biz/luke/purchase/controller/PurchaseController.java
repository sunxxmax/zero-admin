package com.sunxx.biz.luke.purchase.controller;


import com.sunxx.biz.luke.purchase.domain.Purchase;
import com.sunxx.biz.luke.purchase.bean.PurchaseCreate;
import com.sunxx.biz.luke.purchase.mapper.PurchaseMapstruct;
import com.sunxx.biz.luke.purchase.service.PurchaseService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/purchase")
public class PurchaseController {

    @Resource
    private PurchaseService purchaseService;
    @Resource
    private PurchaseMapstruct purchaseMapstruct;

    /**
     * 分页列表
     */
    @GetMapping(value = "/page")
    public Page<Purchase> page(Pageable pageable) {
        return purchaseService.page(pageable);
    }

    @PostMapping(value = "/create")
    public void create(@Valid @RequestBody PurchaseCreate param) {
        Purchase purchase = purchaseMapstruct.create2Entity(param);
        purchaseService.create(purchase);
    }

    @GetMapping(value = "/list/usable")
    public List<Purchase> list4Usable() {
        return purchaseService.list4Usable();
    }
}
