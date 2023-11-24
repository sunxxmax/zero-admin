package com.sunxx.biz.system.dict.service.impl;

import com.sunxx.biz.system.dict.domain.DictType;
import com.sunxx.biz.system.dict.mapper.DictTypeMapper;
import com.sunxx.biz.system.dict.service.DictTypeService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DictTypeServiceImpl implements DictTypeService {

    @Resource
    private DictTypeMapper dictTypeMapper;

    @Override
    public Page<DictType> page(Pageable pageable) {
        return dictTypeMapper.findAll(pageable);
    }

}
