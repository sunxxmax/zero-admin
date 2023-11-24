package com.sunxx.biz.system.menu.service;

import com.sunxx.biz.system.menu.domain.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface MenuService {

    Optional<Menu> findById(Long id);

    Page<Menu> page(PageRequest pageRequest);

    void create(Menu source);

    void update(Menu source);

    void delete(Long id);
}
