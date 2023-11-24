package com.sunxx.biz.luke.partner.controller;

import com.sunxx.biz.luke.partner.domain.Partner;
import com.sunxx.biz.luke.partner.service.PartnerService;
import com.sunxx.biz.luke.partner.bean.PartnerCreate;
import com.sunxx.biz.luke.partner.mapper.PartnerMapstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Slf4j
@RestController
@RequestMapping(value = "/partner")
public class PartnerController {

    @Resource
    private PartnerService partnerService;
    @Resource
    private PartnerMapstruct partnerMapstruct;

    @GetMapping(value = "/list")
    public List<Partner> list() {
        return partnerService.list();
    }

    /**
     * 分页列表
     */
    @GetMapping(value = "/page")
    public Page<Partner> page(Pageable pageable) {
        return partnerService.page(pageable);
    }

    @PostMapping(value = "/create")
    public void create(@Validated @RequestBody PartnerCreate param) {
        Partner partner = partnerMapstruct.create2Entity(param);
        partnerService.create(partner);
    }

    public void update() {
        log.info("更新成功");
    }
}
