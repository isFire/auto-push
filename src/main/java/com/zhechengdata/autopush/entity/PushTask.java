package com.zhechengdata.autopush.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * @author 清汤白面
 */
@Data
@Table("push_tasks")
public class PushTask {
    @Id
    private Long id;

    @Column("name")
    private String name;

	/**
	 *  以JSON格式存储的渠道配置引用
	 */
    @Column("channel_configs")
    private String channelConfigs;

    @Column("channel_strategy")
    private String channelStrategy;

	/**
	 * text, template, html
	 */
    @Column("content_type")
    private String contentType;

	/**
	 * 以JSON格式存储的内容数据
	 */
    @Column("content_data")
    private String contentData;

    @Column("is_scheduled")
    private boolean scheduled;

    @Column("schedule_time")
    private LocalDateTime scheduleTime;

    @Column("create_time")
    private LocalDateTime createTime;

    @Column("update_time")
    private LocalDateTime updateTime;

    @Column("status")
    private String status;

    @Column("success_count")
    private Long successCount;

    @Column("fail_count")
    private Long failCount;
}
