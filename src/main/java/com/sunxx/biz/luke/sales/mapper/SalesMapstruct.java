package com.sunxx.biz.luke.sales.mapper;

import com.sunxx.biz.luke.sales.domain.Sales;
import com.sunxx.biz.luke.sales.domain.SalesPurchase;
import com.sunxx.biz.luke.sales.bean.SalesCreate;
import com.sunxx.biz.luke.sales.bean.SalesPurchaseCreate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SalesMapstruct {

    @Mappings({
            @Mapping(target = "remark", source = "remark"),
            @Mapping(target = "loss", source = "loss"),
            @Mapping(target = "stall", source = "stall")
    })
    Sales create2Entity(SalesCreate source);

    @Mappings({
            @Mapping(target = "purchase.id", source = "purchase"),
            @Mapping(target = "payback", source = "payback")
    })
    SalesPurchase create2Entity(SalesPurchaseCreate source);
}
