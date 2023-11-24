package com.sunxx.biz.system.dep.service;

import com.sunxx.biz.system.dep.domain.Dep;
import org.springframework.data.domain.Page;

public interface DepService {

    Page<Dep> list();

    void add(Dep dep);
}
