package com.sunxx.security.authorize;

import com.sunxx.biz.system.user.bean.LoginRequest;
import com.sunxx.biz.system.user.bean.LoginResponse;
import com.sunxx.biz.system.user.bean.RegisterRequest;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
public class AuthenticationController {

    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private UserDetailsServiceCustomize userDetailsServiceCustomize;

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest param) {
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(param.username(), param.password());
        // 传递用户密码给到SpringSecurity执行校验，如果校验失败，会进入BadCredentialsException
        Authentication authentication = authenticationManager.authenticate(authenticationRequest);
        // 将用户信息存入上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);

        LoginResponse loginResponse = userDetailsServiceCustomize.login(param.username());
        return ok(loginResponse);
    }

    @PostMapping(value = "/logout")
    public void logout(LoginRequest param) {
        log.info("登出成功");
    }

    @PostMapping(value = "/register")
    public void register(@Validated @RequestBody RegisterRequest param) {
        if (!param.password().equals(param.pwd())) {
            throw new ServiceException("两次密码不一致");
        }
        userDetailsServiceCustomize.register(param.username(), param.password());
    }
}
