package com.sunxx.biz.system.dict.domain;

import com.sunxx.audit.AuditMetadata;
import com.sunxx.common.enums.Deleted;
import com.sunxx.common.enums.Fixed;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "sys_dict_type")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class DictType {
    /**
     * 主键id
     */
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 类型编码
     */
    @Column(name = "code")
    private String code;
    /**
     * 类型展示用
     */
    @Column(name = "name")
    private String name;
    /**
     * 排序
     */
    @Column(name = "sort")
    private Integer sort;
    /**
     * 是否删除
     */
    @Convert(converter = Deleted.Convert.class)
    @Column(name = "deleted")
    private Deleted deleted;

    /**
     * 是否固定（0：不固定，1：固定）
     */
    @Convert(converter = Fixed.Convert.class)
    @Column(name = "fixed")
    private Fixed fixed;

    @Embedded
    private AuditMetadata audit;
}
