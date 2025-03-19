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
@Table("channel_configs")
public class ChannelConfig {
    @Id
    private Long id;

    @Column("name")
    private String name;

    @Column("type")
    private String type;

	/**
	 * 以JSON格式存储的配置数据
	 */
    @Column("config_data")
    private String configData;

    @Column("create_time")
    private LocalDateTime createTime;

    @Column("update_time")
    private LocalDateTime updateTime;

    @Column("enabled")
    private boolean enabled;
}
