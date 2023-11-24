package com.sunxx.biz.luke.purchase.domain;

import com.sunxx.audit.AuditMetadata;
import com.sunxx.biz.luke.partner.domain.Partner;
import com.sunxx.common.enums.luke.PurchaseStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "biz_lk_purchase")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 订单号
     */
    @Column(name = "no")
    private String no;
    /**
     * 商品名称
     */
    @Column(name = "name")
    private String name;
    /**
     * 商品数量
     */
    @Column(name = "num")
    private Integer num;
    /**
     * 商品价格
     */
    @Column(name = "price")
    private BigDecimal price;

    /**
     * 应付金额
     */
    @Column(name = "payable")
    private BigDecimal payable;
    /**
     * 实付金额
     */
    @Column(name = "paid")
    private BigDecimal paid;

    /**
     * 采购人
     */
    @JoinColumn(name = "buyer")
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private Partner buyer;
    /**
     * 渠道
     */
    @Column(name = "channel")
    private String channel;

    /**
     * 订单状态
     */
    @Column(name = "status")
    @Convert(converter = PurchaseStatus.Convert.class)
    private PurchaseStatus status;
    /**
     * 订单时间
     */
    @Column(name = "buy_at")
    private LocalDateTime buyAt;
    /**
     * 运输单号
     */
    @Column(name = "transport")
    private String transport;
    /**
     * 订单地址
     */
    @Column(name = "address")
    private String address;

    @Embedded
    @Builder.Default
    private AuditMetadata auditMetadata = new AuditMetadata();

}
