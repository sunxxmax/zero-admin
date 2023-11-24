package com.sunxx.biz.system.dict.service;

import com.sunxx.biz.system.dict.domain.DictType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DictTypeService {

    Page<DictType> page(Pageable pageable);
}
