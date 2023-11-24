package com.sunxx.biz.system.log.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data

@Entity
@Table(name = "sys_login_log")
public class LoginLog {

    @Id
    @GeneratedValue
    private Long id;

    // 用户名
    @Column
    private String username;

    // IP
    @Column
    private String ip;

    // 登录地址
    @Column
    private String address;

    // 浏览器
    @Column
    private String browser;

    // 操作系统
    @Column
    private String os;

    // 状态
    @Column
    private String state;

    // 操作日志
    @Column
    private String message;

    // 创建时间
    @Column
    private LocalDateTime time;
}
