package com.sunxx.biz.system.user.service;

import com.sunxx.biz.system.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    Page<User> page(Pageable pageable);

    void create(User source);

    void update(User source);

    void delete(Long id);
}
