package com.sunxx.biz.system.user.bean;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
        @NotBlank(message = "用户名不能为空")
        String username,
        @NotBlank(message = "密码不能为空")
        String password,
        @NotBlank(message = "确认密码不能为空")
        String pwd) {
}
