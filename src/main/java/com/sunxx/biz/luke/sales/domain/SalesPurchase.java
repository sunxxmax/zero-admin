package com.sunxx.biz.luke.sales.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sunxx.biz.luke.purchase.domain.Purchase;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "biz_lk_sales_purchase")
@Entity
public class SalesPurchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 出货 id
     */
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "sales_id")
    private Sales sales;
    /**
     * 订单 id
     */
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;
    /**
     * 出货价格
     */
    @Column(name = "payback")
    private BigDecimal payback;
}
