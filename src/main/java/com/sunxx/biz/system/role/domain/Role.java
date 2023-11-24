package com.sunxx.biz.system.role.domain;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "sys_role")
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 角色名
    @Column(name = "name")
    private String name;

    // 角色编码
    @Column(name = "code")
    private String code;

    // 排序
    @Column(name = "sort")
    private Integer sort;

    // 状态
    @Column(name = "status")
    private Integer status;

    // 描述
    @Column(name = "description")
    private String description;

    // 不可变
    @Column(name = "immutable")
    private Boolean immutable;
}
