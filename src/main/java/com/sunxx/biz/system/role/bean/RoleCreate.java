package com.sunxx.biz.system.role.bean;

import com.sunxx.biz.system.role.domain.Role;
import com.sunxx.common.enums.Fixed;

public record RoleCreate(String code, String name, Integer sort, String desc) {

    public Role create() {
        return Role.builder()
                .code(code)
                .name(name)
                .sort(sort)
                .desc(desc)
                .fixed(Fixed.NO)
                .build();
    }
}
