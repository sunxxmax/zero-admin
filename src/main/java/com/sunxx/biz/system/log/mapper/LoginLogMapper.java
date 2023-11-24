package com.sunxx.biz.system.log.mapper;

import com.sunxx.biz.system.log.domain.LoginLog;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LoginLogMapper extends JpaRepository<LoginLog, Long> {

}
