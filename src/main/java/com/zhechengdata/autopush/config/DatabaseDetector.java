
package com.zhechengdata.autopush.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author 清汤白面
 */
@Component
public class DatabaseDetector {
    private static final Logger log = LoggerFactory.getLogger(DatabaseDetector.class);

    private final Environment environment;

    public DatabaseDetector(Environment environment) {
        this.environment = environment;
    }

    @EventListener(ApplicationStartedEvent.class)
    public void detectDatabaseOnStartup() {
        String url = environment.getProperty("spring.r2dbc.url");
        log.info("当前使用的数据库连接URL: {}", url);

        if (url != null) {
            if (url.contains("postgresql")) {
                log.info("检测到PostgreSQL数据库");
            } else if (url.contains("mysql")) {
                log.info("检测到MySQL数据库");
            } else {
                log.warn("未能识别的数据库类型: {}", url);
            }
        } else {
            log.warn("未配置数据库连接URL");
        }
    }
}
