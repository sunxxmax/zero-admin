package com.sunxx.security.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sunxx.util.JwtUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

/**
 * 每次请求的 Security 过滤类。执行jwt有效性检查，如果失败，不设置 SecurityContextHolder 信息，会进入 AuthenticationEntryPoint
 */
@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private static final String BEARER_PREFIX = "Bearer ";
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        // 从 HTTP 请求中获取 token
        String token = this.getTokenFromHttpRequest(request);

        // 验证 token 是否存在
        if (!StringUtils.hasText(token)) {
            // 放行请求
            chain.doFilter(request, response);
            return;
        }

        // 验证 token 是否有效
        try {
            DecodedJWT decodedJWT = JwtUtils.verify(token);
            String username = decodedJWT.getSubject();
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // 手动组装一个认证对象
            UsernamePasswordAuthenticationToken authenticated = UsernamePasswordAuthenticationToken
                    .authenticated(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
            // 将认证信息存入 Spring 安全上下文中
            SecurityContextHolder.getContext().setAuthentication(authenticated);
        } catch (JWTVerificationException e) {
            authenticationEntryPoint.commence(request, response, new BadCredentialsException("无效凭证，请重新登录"));
            return;
        }

        // 放行请求
        chain.doFilter(request, response);
    }

    private String getTokenFromHttpRequest(HttpServletRequest request) {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(authorization) && authorization.startsWith(BEARER_PREFIX)) {
            return authorization.substring(BEARER_PREFIX.length());
        }
        return null;
    }
}
