package com.sunxx.biz.luke.sales.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sunxx.audit.AuditMetadata;
import com.sunxx.biz.system.dict.domain.Dict;
import com.sunxx.common.enums.luke.SalesStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.sunxx.common.enums.luke.SalesStatus.FINISHED;
import static com.sunxx.common.enums.luke.SalesStatus.PAYBACK;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "biz_lk_sales")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 出售单号
     */
    @Column(name = "no")
    private String no;
    /**
     * 损耗
     */
    @Column(name = "loss")
    private BigDecimal loss;
    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;
    /**
     * 档口
     */
    @Column(name = "stall")
    private String stall;
    /**
     * 出货单状态
     */
    @Column(name = "status")
    @Convert(converter = SalesStatus.Convert.class)
    private SalesStatus status;
    /**
     * 出货集合
     */
    @JsonManagedReference
    @OneToMany(mappedBy = "sales", cascade = CascadeType.ALL)
    @Builder.Default
    private Set<SalesPurchase> items = new HashSet<>();
    /**
     * 总实付
     */
    @Transient
    private BigDecimal totalPid;
    /**
     * 总回款
     */
    @Transient
    private BigDecimal totalPayback;

    @Transient
    private BigDecimal totalRevenue;
    /**
     * 审计
     */
    @Embedded
    @Builder.Default
    private AuditMetadata auditMetadata = new AuditMetadata();

    public BigDecimal getTotalPid() {
        if (CollectionUtils.isEmpty(items)) {
            return BigDecimal.ZERO;
        }
        return items.stream()
                .map(item -> Optional.ofNullable(item.getPurchase().getPaid()).orElse(BigDecimal.ZERO))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalPayback() {
        if (CollectionUtils.isEmpty(items)) {
            return BigDecimal.ZERO;
        }
        return items.stream()
                .map(item -> Optional.ofNullable(item.getPayback()).orElse(BigDecimal.ZERO))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalRevenue() {
        return List.of(PAYBACK, FINISHED).contains(status) ? getTotalPayback().subtract(getTotalPid()) : BigDecimal.ZERO;
    }
}
