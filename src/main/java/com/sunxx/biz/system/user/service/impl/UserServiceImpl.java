package com.sunxx.biz.system.user.service.impl;

import com.sunxx.biz.system.user.domain.User;
import com.sunxx.common.enums.account.AccountEnabled;
import com.sunxx.biz.system.user.mapper.UserMapper;
import com.sunxx.biz.system.user.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    @Resource
    private UserMapper userMapper;

    @Override
    public Optional<User> findById(Long id) {
        return userMapper.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public Page<User> page(Pageable pageable) {
        return userMapper.findAll(pageable);
    }

    @Override
    public void create(User user) {
        userMapper.save(user);
    }

    @Override
    public void update(User target) {
        userMapper.save(target);
    }

    @Override
    public void delete(Long id) {
        User target = userMapper.findById(id).orElseThrow(() -> new RuntimeException("数据不存在"));
        target.setAccountEnabled(AccountEnabled.DISABLE);

        userMapper.save(target);
    }
}
