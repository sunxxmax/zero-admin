package com.sunxx.biz.system.menu.mapper;

import com.sunxx.biz.system.menu.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuMapper extends JpaRepository<Menu, Long> {
}
