package com.sunxx.biz.luke.partner.mapper;

import com.sunxx.biz.luke.partner.domain.Partner;
import com.sunxx.biz.luke.partner.bean.PartnerCreate;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PartnerMapstruct {

    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "phone", target = "phone"),
            @Mapping(source = "address", target = "address"),
    })
    Partner create2Entity(PartnerCreate source);
}
