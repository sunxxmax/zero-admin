package com.sunxx.biz.luke.partner.service.impl;

import com.sunxx.biz.luke.partner.domain.Partner;
import com.sunxx.common.enums.luke.PartnerStatus;
import com.sunxx.biz.luke.partner.mapper.PartnerMapper;
import com.sunxx.biz.luke.partner.service.PartnerService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartnerServiceImpl implements PartnerService {

    @Resource
    private PartnerMapper partnerMapper;

    @Override
    public List<Partner> list() {
        Example<Partner> example = Example.of(Partner.builder().status(PartnerStatus.ENABLED).build());
        return partnerMapper.findAll(example);
    }

    @Override
    public Page<Partner> page(Pageable pageable) {
        return partnerMapper.findAll(pageable);
    }

    @Override
    public void create(Partner source) {
        source.setStatus(PartnerStatus.ENABLED);
        partnerMapper.save(source);
    }
}
