package com.sunxx.biz.luke.partner.service;

import com.sunxx.biz.luke.partner.domain.Partner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PartnerService {

    List<Partner> list();

    Page<Partner> page(Pageable pageable);

    void create(Partner source);
}
