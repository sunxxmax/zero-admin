package com.sunxx.biz.luke.purchase.mapper;

import com.sunxx.biz.luke.purchase.bean.PurchaseCreate;
import com.sunxx.biz.luke.purchase.domain.Purchase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PurchaseMapstruct {

    @Mappings({
            @Mapping(target = "no", source = "no"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "num", source = "num"),
            @Mapping(target = "price", source = "price"),
            @Mapping(target = "payable", source = "payable"),
            @Mapping(target = "paid", source = "paid"),
            @Mapping(target = "buyer.id", source = "buyer"),
            @Mapping(target = "channel", source = "channel"),
            @Mapping(target = "buyAt", source = "buyAt"),
            @Mapping(target = "transport", source = "transport")
    })
    Purchase create2Entity(PurchaseCreate source);
}
