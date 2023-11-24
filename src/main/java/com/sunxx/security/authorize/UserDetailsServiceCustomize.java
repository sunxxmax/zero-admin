package com.sunxx.security.authorize;

import com.sunxx.biz.system.user.bean.LoginResponse;
import com.sunxx.biz.system.user.domain.User;
import com.sunxx.biz.system.user.service.UserService;
import com.sunxx.exception.BizException;
import com.sunxx.util.JwtUtils;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserDetailsServiceCustomize implements UserDetailsService {

    @Resource
    private UserService userService;

    public LoginResponse login(String username) {
        // 如果验证通过了，从返回的authentication里获得完整的UserDetails信息
        String token = JwtUtils.generate(username);
        return new LoginResponse(username, token);
    }

    public void logout() {

    }

    public void register(String username, String password) {
        userService.findByUsername(username).ifPresentOrElse(user -> {
                    throw new BizException("用户已存在");
                }, () -> {
                    String newPwd = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password);
                    User user = User.buildNewUser(username, newPwd);
                    userService.create(user);
                }
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!StringUtils.hasText(username)) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return userService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("用户不存在"));
    }
}
