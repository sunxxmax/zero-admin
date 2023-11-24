package com.sunxx.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.security.auth.login.CredentialExpiredException;
import java.io.IOException;

/**
 * 认证处理器：用于处理未认证的用户尝试访问受保护资源时抛出的异常。
 * 典型的异常是 org.springframework.security.core.AuthenticationException，表示用户尚未进行认证。
 * 当用户尝试访问需要认证的资源但尚未进行认证时，AuthenticationEntryPoint 负责发送一个认证请求，通常是重定向到登录页面或返回一个 HTTP 401 未认证的响应。
 * 示例实现是 LoginUrlAuthenticationEntryPoint，它将用户重定向到登录页面。
 *
 * @author sunxx
 */
@Slf4j
@Component
public class AuthenticationEntryPointCustomize implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(401);
        response.getWriter().write(authException.getLocalizedMessage());
        response.getWriter().flush();
    }
}
