package com.sunxx.biz.system.role.mapper;

import com.sunxx.biz.system.role.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleMapper extends JpaRepository<Role, Long> {
}
