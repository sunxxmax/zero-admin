package com.sunxx.biz.system.dep.service.impl;

import com.sunxx.biz.system.dep.domain.Dep;
import com.sunxx.biz.system.dep.mapper.DepMapper;
import com.sunxx.biz.system.dep.service.DepService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class DepServiceImpl implements DepService {

    @Resource
    private DepMapper depMapper;

    @Override
    public Page<Dep> list() {
        Pageable pageable = PageRequest.of(1, 2);
        Page<Dep> page = depMapper.findAll(pageable);
        return page;
    }

    @Override
    public void add(Dep dep) {

    }
}
