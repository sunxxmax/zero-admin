package com.sunxx.biz.system.dict.service;

import com.sunxx.biz.system.dict.domain.Dict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DictService {

    Page<Dict> page(Pageable pageable);

    List<Dict> list4Type(Long typeId);
}
