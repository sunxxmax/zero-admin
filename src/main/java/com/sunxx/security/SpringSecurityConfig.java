package com.sunxx.security;

import com.sunxx.security.filter.JwtAuthenticationFilter;
import com.sunxx.security.handler.AccessDeniedHandlerCustomize;
import com.sunxx.security.handler.AuthenticationEntryPointCustomize;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.RequestCacheConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Resource
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Resource
    private AccessDeniedHandlerCustomize accessDeniedHandlerCustomize;
    @Resource
    private AuthenticationEntryPointCustomize authenticationEntryPointCustomize;

    private static final String[] URL_WHITELIST = {"/api/**", "/register", "/login", "/logout", "/captcha", "/favicon.ico","/notify/**"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // 对 http 进行授权认证
                .authorizeHttpRequests(authorize -> authorize
                        // 登录不需要认证
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        // 定义不需要认证的资源路径(如：/api)
                        .requestMatchers(URL_WHITELIST).permitAll()
                        // 对 http 进行授权认证
                        .anyRequest().authenticated()
                )
                // 前后端分离架构不需要csrf保护
                .csrf(AbstractHttpConfigurer::disable)
                // 关闭 CSRF 跨域漏洞防御
                .cors(corsConfigurer -> corsConfigurer.configurationSource(request -> {
                    var cors = new CorsConfiguration();
                    cors.setAllowCredentials(true);
                    cors.addAllowedOriginPattern("*");
                    cors.addAllowedHeader("*");
                    cors.addAllowedMethod("*");
                    return cors;
                }))
                // 禁用basic明文验证
                .httpBasic(AbstractHttpConfigurer::disable)
                // 前后端分离是无状态的，不需要 session 了，直接禁用。
                .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 禁用默认登录页
                .formLogin(AbstractHttpConfigurer::disable)
                // 禁用默认登出页
                .logout(AbstractHttpConfigurer::disable)
                // 禁用缓存：缓存请求地址，如果不禁用，会导致前端页面跳转后，后端依然会返回缓存的页面
                .requestCache(RequestCacheConfigurer::disable)
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        // 认证处理器
                        .authenticationEntryPoint(authenticationEntryPointCustomize)
                        // 访问被拒绝处理器
                        .accessDeniedHandler(accessDeniedHandlerCustomize)
                )
                // 加我们自定义的过滤器，替代 UsernamePasswordAuthenticationFilter
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * 将 AuthenticationManager 给 spring 管理
     *
     * @param passwordEncoder    有参构造器参数
     * @param userDetailsService 有参构造器参数
     * @return AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setHideUserNotFoundExceptions(false);
        return new ProviderManager(authenticationProvider);
    }
}