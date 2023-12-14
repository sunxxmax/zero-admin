package com.sunxx.biz.system.role.domain;

import com.sunxx.audit.AuditMetadata;
import com.sunxx.common.enums.Fixed;
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

    /**
     * 编码
     */
    @Column(name = "code")
    private String code;
    /**
     * 名称
     */
    @Column(name = "name")
    private String name;
    /**
     * 排序
     */
    @Column(name = "sort")
    private Integer sort;
    /**
     * 状态
     */
    @Column(name = "status")
    private Integer status;
    /**
     * 描述
     */
    @Column(name = "desc")
    private String desc;

    /**
     * 是否可变
     */
    @Column(name = "fixed")
    @Convert(converter = Fixed.Convert.class)
    private Fixed fixed;

    @Embedded
    @Builder.Default
    private AuditMetadata auditMetadata = new AuditMetadata();
}
