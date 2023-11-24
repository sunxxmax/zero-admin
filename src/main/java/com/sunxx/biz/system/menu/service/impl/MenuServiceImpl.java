package com.sunxx.biz.system.menu.service.impl;

import com.sunxx.biz.system.menu.domain.Menu;
import com.sunxx.biz.system.menu.mapper.MenuMapper;
import com.sunxx.biz.system.menu.service.MenuService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    @Override
    public Optional<Menu> findById(Long id) {
        return menuMapper.findById(id);
    }

    @Override
    public Page<Menu> page(PageRequest pageRequest) {
        return menuMapper.findAll(pageRequest);
    }

    @Override
    public void create(Menu source) {
        menuMapper.save(source);
    }

    @Override
    public void update(Menu source) {
        menuMapper.save(source);
    }

    @Override
    public void delete(Long id) {
        Menu target = menuMapper.findById(id).orElseThrow(() -> new RuntimeException("数据不存在"));
        target.setStatus("");

        menuMapper.save(target);
    }
}
