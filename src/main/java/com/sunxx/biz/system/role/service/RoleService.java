package com.sunxx.biz.system.role.service;

import com.sunxx.biz.system.role.domain.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RoleService {

    Optional<Role> findById(Long id);

    Page<Role> page(Pageable pageable);

    void create(Role source);

    void update(Role source);

    void delete(Long id);
}
