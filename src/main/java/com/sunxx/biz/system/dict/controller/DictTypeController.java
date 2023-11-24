package com.sunxx.biz.system.dict.controller;

import com.sunxx.biz.system.dict.domain.DictType;
import com.sunxx.biz.system.dict.service.DictTypeService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/dict/type")
public class DictTypeController {

    @Resource
    private DictTypeService dictTypeService;

    @PostMapping(value = "/page")
    public Page<DictType> page(Pageable pageable) {
        return dictTypeService.page(pageable);
    }
}
