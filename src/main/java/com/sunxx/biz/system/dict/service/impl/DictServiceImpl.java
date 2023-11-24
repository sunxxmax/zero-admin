package com.sunxx.biz.system.dict.service.impl;

import com.sunxx.biz.system.dict.domain.Dict;
import com.sunxx.biz.system.dict.domain.DictType;
import com.sunxx.biz.system.dict.mapper.DictMapper;
import com.sunxx.biz.system.dict.service.DictService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DictServiceImpl implements DictService {

    @Resource
    private DictMapper dictMapper;

    @Override
    public Page<Dict> page(Pageable pageable) {
        return dictMapper.findAll(pageable);
    }

    @Override
    public List<Dict> list4Type(Long typeId) {
        Example<Dict> example = Example.of(Dict.builder().type(DictType.builder().id(typeId).build()).build());
        return dictMapper.findAll(example);
    }
}
