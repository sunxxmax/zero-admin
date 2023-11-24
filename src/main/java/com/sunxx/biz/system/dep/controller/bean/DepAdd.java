package com.sunxx.biz.system.dep.controller.bean;


import com.sunxx.biz.system.dep.domain.Dep;

public record DepAdd(
        String name,
        Integer sort) {

    public Dep covert() {

        return Dep.builder()
                .name(name)
                .sort(sort)
                .state("")
//                .head(null)
                .build();
    }
}
