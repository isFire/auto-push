package com.zhechengdata.autopush;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

/**
 * @author 清汤白面
 */
@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
@EnableR2dbcRepositories
public class AutoPushApplication {
    public static void main(String[] args) {
        SpringApplication.run(AutoPushApplication.class, args);
    }
}
