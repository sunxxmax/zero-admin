package com.sunxx.biz.luke.partner.mapper;

import com.sunxx.biz.luke.partner.domain.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerMapper  extends JpaRepository<Partner, Long> {
}
