package com.sunxx.biz.system.user.bean;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserUpdate(
        @NotNull(message = "参数错误")
        Long id,
        @NotBlank(message = "用户名不能为空")
        String username,
        @NotBlank(message = "密码不能为空")
        String password) {

}
