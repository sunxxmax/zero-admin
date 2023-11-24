package com.sunxx.audit;

import com.sunxx.biz.system.user.domain.User;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.AccessType;

import java.time.LocalDateTime;

/**
 * 审计实体类
 * <p>
 * `@AccessType(AccessType.Type.FIELD) 注解具体作用是什么？`
 */
@Data
@AccessType(AccessType.Type.FIELD)
@Embeddable
public class AuditMetadata {

    @CreatedBy
    @OneToOne
    @JoinColumn(name = "created_by", updatable = false)
    private User createdBy;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedBy
    @OneToOne
    @JoinColumn(name = "last_modified_by")
    private User lastModifiedBy;

    @LastModifiedDate
    @Column(name = "last_modified_at")
    private LocalDateTime lastModifiedAt;
}
