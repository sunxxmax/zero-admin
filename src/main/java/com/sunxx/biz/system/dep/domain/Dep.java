package com.sunxx.biz.system.dep.domain;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "sys_dep")
public class Dep {

    @Id
    @GeneratedValue
    private Long id;

    // 部门名称
    @Column
    private String name;

    // 排序
    @Column
    private Integer sort;

//    // 负责人
//    @ManyToOne
//    @Column
//    private User head;

    // 状态
    @Column
    private String state;

//    // 创建信息
//    @Column
//    @OneToOne
//    private User create;
}
