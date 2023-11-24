package com.sunxx.biz.system.role.service.impl;

import com.sunxx.biz.system.role.domain.Role;
import com.sunxx.biz.system.role.mapper.RoleMapper;
import com.sunxx.biz.system.role.service.RoleService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public Optional<Role> findById(Long id) {
        return roleMapper.findById(id);
    }

    @Override
    public Page<Role> page(PageRequest pageRequest) {
        return roleMapper.findAll(pageRequest);
    }

    @Override
    public void create(Role source) {
        roleMapper.save(source);
    }

    @Override
    public void update(Role source) {
        roleMapper.save(source);
    }

    @Override
    public void delete(Long id) {
        Role target = roleMapper.findById(id).orElseThrow(() -> new RuntimeException("数据不存在"));
        target.setStatus(1);

        roleMapper.save(target);
    }
}
