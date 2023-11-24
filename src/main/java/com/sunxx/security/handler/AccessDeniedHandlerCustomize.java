package com.sunxx.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 访问被拒绝处理器：用于处理已认证的用户尝试访问他们没有权限的资源时抛出的异常。
 * 典型的异常是 org.springframework.security.access.AccessDeniedException，表示用户已认证，但没有足够的权限来访问资源。
 * 当用户已认证但权限不足时，AccessDeniedHandler 负责处理这种情况，通常是返回一个 HTTP 403 权限不足的响应，或者执行其他自定义的处理逻辑，例如记录事件或返回一个自定义错误页面。
 * 示例实现是 AccessDeniedHandlerImpl，它返回一个 HTTP 403 响应。
 *
 * @author sunxx
 */
@Component
public class AccessDeniedHandlerCustomize implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(403);
        response.getWriter().write("无权限");
        response.getWriter().flush();
    }
}
