package com.sunxx.biz.system.dep.service.impl;

import com.sunxx.biz.system.dep.domain.Dep;
import com.sunxx.biz.system.dep.mapper.DepMapper;
import com.sunxx.biz.system.dep.service.DepService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class DepServiceImpl implements DepService {

    @Resource
    private DepMapper depMapper;

    @Override
    public Optional<Dep> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Page<Dep> page(Pageable pageable) {
        return null;
    }

    @Override
    public void create(Dep source) {

    }

    @Override
    public void update(Dep source) {

    }

    @Override
    public void delete(Long id) {

    }
}
