package com.sunxx.biz.system.dep.service;

import com.sunxx.biz.system.dep.domain.Dep;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface DepService {

    Optional<Dep> findById(Long id);

    Page<Dep> page(Pageable pageable);

    void create(Dep source);

    void update(Dep source);

    void delete(Long id);
}
