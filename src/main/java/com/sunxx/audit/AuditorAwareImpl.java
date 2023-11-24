package com.sunxx.audit;

import com.sunxx.biz.system.user.domain.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * 审计功能
 *
 * @author sunxx
 * @since 2020-12-8 11:37:05
 */
@EnableJpaAuditing
@Configuration
public class AuditorAwareImpl implements AuditorAware<User> {


    @Override
    public Optional<User> getCurrentAuditor() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .filter(authentication -> authentication.getPrincipal() instanceof User)
                .map(Authentication::getPrincipal)
                .map(User.class::cast);
    }
}
