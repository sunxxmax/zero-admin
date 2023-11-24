package com.sunxx.biz.system.menu.domain;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "sys_menu")
@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 类型
    private String type;

    // 图标
    private String icon;

    // 名称
    private String name;

    // 地址
    private String path;

    // 排序
    private String sort;

    // 状态
    private String status;

    // 父级
    private String parentId;

    // 是否隐藏
    private Boolean hidden;
}
