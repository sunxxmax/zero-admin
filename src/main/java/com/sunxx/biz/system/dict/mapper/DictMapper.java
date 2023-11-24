package com.sunxx.biz.system.dict.mapper;

import com.sunxx.biz.system.dict.domain.Dict;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DictMapper extends JpaRepository<Dict, Long> {
}
