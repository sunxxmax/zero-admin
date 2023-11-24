package com.sunxx.biz.system.user.controller;

import com.sunxx.biz.system.user.domain.User;
import com.sunxx.biz.system.user.bean.UserCreate;
import com.sunxx.biz.system.user.bean.UserDelete;
import com.sunxx.biz.system.user.bean.UserUpdate;
import com.sunxx.biz.system.user.mapper.UserMapstruct;
import com.sunxx.biz.system.user.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户
 */
@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private UserMapstruct userMapstruct;

    /**
     * 分页列表
     */
    @GetMapping(value = "/page")
    public Page<User> page(Pageable pageable) {
        return userService.page(pageable);
    }

    /**
     * 新增
     */
    @PostMapping(value = "/create")
    public void create(@Validated @RequestBody UserCreate param) {
        User source = userMapstruct.userCreate2User(param);
        userService.create(source);
    }

    /**
     * 修改
     */
    @PostMapping(value = "/update")
    public void update(@Validated @RequestBody UserUpdate param) {
        User target = userService.findById(param.id()).orElseThrow(() -> new RuntimeException("数据不存在"));
        userMapstruct.userUpdate2User(param, target);
        userService.update(target);
    }

    /**
     * 删除
     */
    @PostMapping(value = "/delete")
    public void delete(@Validated @RequestBody UserDelete param) {
        userService.delete(param.id());
    }
}
