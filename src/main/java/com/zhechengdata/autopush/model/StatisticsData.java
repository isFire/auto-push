package com.zhechengdata.autopush.model;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 清汤白面
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsData {
    private Long totalPushCount;
    private Long successPushCount;
    private Long failedPushCount;
    private Long activeUserCount;
    private Map<String, Double> channelDistribution;
    private Map<String, Map<String, Long>> dailyStatistics;


}
