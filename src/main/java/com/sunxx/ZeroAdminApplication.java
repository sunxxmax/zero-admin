package com.sunxx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@SpringBootApplication
public class ZeroAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZeroAdminApplication.class, args);
    }

}
