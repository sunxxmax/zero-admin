package com.sunxx.biz.system.dep.mapper;


import com.sunxx.biz.system.dep.domain.Dep;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepMapper extends JpaRepository<Dep, Long> {
}
