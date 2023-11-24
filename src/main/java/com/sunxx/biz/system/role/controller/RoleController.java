package com.sunxx.biz.system.role.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/role")
@RestController
public class RoleController {

    /**
     * 分页查询
     */
    @GetMapping(value = "/page")
    public void page() {

    }

    /**
     * 创建
     */
    @PostMapping(value = "/create")
    public void create() {

    }

    /**
     * 更新
     */
    @PostMapping(value = "/update")
    public void update() {

    }

    /**
     * 删除
     */
    @PostMapping(value = "/delete")
    public void delete() {

    }

}
