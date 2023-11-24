package com.sunxx.biz.luke.partner.domain;

import com.sunxx.audit.AuditMetadata;
import com.sunxx.common.enums.luke.PartnerStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 伙伴
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "biz_lk_partner")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "phone")
    private String phone;
    @Column(name = "address")
    private String address;

    @Convert(converter = PartnerStatus.Convert.class)
    @Column(name = "status")
    private PartnerStatus status;

    @Embedded
    @Builder.Default
    private AuditMetadata auditMetadata = new AuditMetadata();
}
