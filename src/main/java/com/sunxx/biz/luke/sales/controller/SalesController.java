package com.sunxx.biz.luke.sales.controller;

import com.sunxx.biz.luke.sales.bean.SalesCreate;
import com.sunxx.biz.luke.sales.bean.SalesPurchaseUpdate;
import com.sunxx.biz.luke.sales.domain.Sales;
import com.sunxx.biz.luke.sales.service.SalesService;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/sales")
public class SalesController {

    @Resource
    private SalesService salesService;

    /**
     * 分页列表
     */
    @GetMapping(value = "/page")
    public Page<Sales> page(Pageable pageable) {
        return salesService.page(pageable);
    }

    @PostMapping(value = "/create")
    public void create(@Validated @RequestBody SalesCreate param) {
        salesService.create(param);
    }

    @GetMapping(value = "/detail")
    public Sales detail(@Validated @NotNull Long id) {
        return salesService.detail(id);
    }

    @PostMapping(value = "/finished")
    public void finished(@Validated @NotNull Long id) {
        salesService.finished(id);
    }

    @PostMapping(value = "/payback")
    public void payback(@Validated @RequestBody SalesPurchaseUpdate param) {
        salesService.payback(param.id(), param.payback());
    }
}
