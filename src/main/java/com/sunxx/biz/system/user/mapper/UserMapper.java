package com.sunxx.biz.system.user.mapper;

import com.sunxx.biz.system.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserMapper extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
