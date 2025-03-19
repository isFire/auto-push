package com.zhechengdata.autopush.service.impl;

import com.zhechengdata.autopush.model.StatisticsData;
import com.zhechengdata.autopush.service.StatisticsService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 清汤白面
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Override
    public Mono<StatisticsData> getDashboardStatistics() {
        // 模拟仪表盘统计数据
        StatisticsData statisticsData = new StatisticsData(
                156789L,
                142365L,
                14424L,
                2845L,
                generateChannelDistribution(),
                generateDailyStatistics()
        );

        return Mono.just(statisticsData);
    }

    @Override
    public Mono<Map<String, Map<String, Long>>> getDailyStatistics() {
        return Mono.just(generateDailyStatistics());
    }

    @Override
    public Mono<Map<String, Double>> getChannelDistribution() {
        return Mono.just(generateChannelDistribution());
    }

    private Map<String, Double> generateChannelDistribution() {
        Map<String, Double> distribution = new HashMap<>();
        distribution.put("邮箱", 35.0);
        distribution.put("企业微信", 25.0);
        distribution.put("HTTP", 20.0);
        distribution.put("飞书", 15.0);
        distribution.put("Webhook", 5.0);
        return distribution;
    }

    private Map<String, Map<String, Long>> generateDailyStatistics() {
        Map<String, Map<String, Long>> dailyStats = new HashMap<>();

        // 每天的统计数据，包含成功和失败数量
        String[] weekdays = {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
        Long[] successCounts = {150L, 232L, 226L, 218L, 145L, 148L, 250L};
        Long[] failCounts = {20L, 28L, 25L, 24L, 22L, 23L, 30L};

        for (int i = 0; i < weekdays.length; i++) {
            Map<String, Long> dayStat = new HashMap<>();
            dayStat.put("success", successCounts[i]);
            dayStat.put("fail", failCounts[i]);
            dailyStats.put(weekdays[i], dayStat);
        }

        return dailyStats;
    }
}
