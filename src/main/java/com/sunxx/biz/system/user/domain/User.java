package com.sunxx.biz.system.user.domain;

import com.sunxx.common.enums.account.AccountEnabled;
import com.sunxx.common.enums.account.AccountLocked;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;


/**
 * 用户
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "sys_user")
@Entity
public class User implements UserDetails {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 用户名
     */
    @Column(name = "username")
    private String username;
    /**
     * 用户密码
     */
    @Column(name = "password")
    private String password;
    /**
     * 账号是否被锁定
     */
    @Convert(converter = AccountLocked.Convert.class)
    @Column(name = "account_locked")
    private AccountLocked accountLocked;
    /**
     * 账号是否可用
     */
    @Convert(converter = AccountEnabled.Convert.class)
    @Column(name = "account_enabled")
    private AccountEnabled accountEnabled;
    /**
     * 账号过期时间
     */
    @Column(name = "account_expired_time")
    private LocalDateTime accountExpiredTime;
    /**
     * 凭证过期时间
     */
    @Column(name = "credentials_expired_time")
    private LocalDateTime credentialsExpiredTime;

    public static User buildNewUser(String username, String password) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime plusDate = now.plusYears(1);
        return User.builder()
                .username(username)
                .password(password)
                .accountEnabled(AccountEnabled.ENABLED)
                .accountLocked(AccountLocked.UNLOCKED)
                .accountExpiredTime(plusDate)
                .credentialsExpiredTime(plusDate)
                .build();
    }

    /**
     * 权限
     *
     * @return 集合
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * 账号是否过期
     *
     * @return true:未过期  false:过期的
     */
    @Override
    public boolean isAccountNonExpired() {
        return Optional.ofNullable(accountExpiredTime).map(e -> e.isAfter(LocalDateTime.now())).orElse(false);
    }

    /**
     * 账号是否被锁定
     *
     * @return true:未锁定  false:锁定的
     */
    @Override
    public boolean isAccountNonLocked() {
        return Optional.ofNullable(accountLocked).map(locked -> locked == AccountLocked.UNLOCKED).orElse(false);
    }

    /**
     * 用户凭证是否过期
     *
     * @return true:未过期  false:过期的
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return Optional.ofNullable(credentialsExpiredTime).map(e -> e.isAfter(LocalDateTime.now())).orElse(false);
    }

    /**
     * 账号是否可用
     *
     * @return true:可用  false:不可用
     */
    @Override
    public boolean isEnabled() {
        return Optional.ofNullable(accountEnabled).map(enabled -> enabled == AccountEnabled.ENABLED).orElse(false);
    }
}
