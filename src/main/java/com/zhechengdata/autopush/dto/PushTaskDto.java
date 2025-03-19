package com.zhechengdata.autopush.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

/**
 * @author 清汤白面
 */
@Data
public class PushTaskDto {
    private Long id;
    private String name;
    private List<ChannelConfigRef> channelConfigs;
    private String channelStrategy;
    private ContentInfo content;
    private boolean scheduled;
    private LocalDateTime scheduleTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String status;
    private Long successCount;
    private Long failCount;

    @Data
    public static class ChannelConfigRef {
        private Long id;
        private String type;
        private String name;
    }

    @Data
    public static class ContentInfo {
        private String type; // text, template, html
        private String text;
        private String templateId;
        private String templateParams;
        private String html;
    }
}
