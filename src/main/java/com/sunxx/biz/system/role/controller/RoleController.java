package com.sunxx.biz.system.role.controller;

import com.sunxx.biz.system.role.bean.RoleCreate;
import com.sunxx.biz.system.role.bean.RoleUpdate;
import com.sunxx.biz.system.role.domain.Role;
import com.sunxx.biz.system.role.service.RoleService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/role")
@RestController
public class RoleController {

    @Resource
    private RoleService roleService;

    /**
     * 分页查询
     */
    @GetMapping(value = "/page")
    public Page<Role> page(Pageable pageable) {
        return roleService.page(pageable);
    }

    /**
     * 创建
     */
    @PostMapping(value = "/create")
    public void create(@Valid @RequestBody RoleCreate param) {
        roleService.create(param.create());
    }

    /**
     * 更新
     */
    @PostMapping(value = "/update")
    public void update(@Valid @RequestBody RoleUpdate param) {

    }

    /**
     * 删除
     */
    @PostMapping(value = "/delete/{id}")
    public void delete(@PathVariable(value = "id") Long id) {
        roleService.delete(id);
    }

}
