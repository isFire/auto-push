package com.zhechengdata.autopush.model;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 清汤白面
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PushTask {
    private Long id;
    private String name;
    private List<String> channels;
    private String targetStrategy;
    private LocalDateTime createTime;
    private String status;
    private Long successCount;
    private Long failCount;

}
