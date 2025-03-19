package com.zhechengdata.autopush.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author 清汤白面
 */
@Data
public class ChannelConfigDto {
    private Long id;
    private String name;
    private String type;
    private Map<String, Object> configData;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private boolean enabled;
}
