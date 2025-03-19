package com.zhechengdata.autopush.controller;

import com.zhechengdata.autopush.model.StatisticsData;
import com.zhechengdata.autopush.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @author 清汤白面
 */
@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/dashboard")
    public Mono<StatisticsData> getDashboardStatistics() {
        return statisticsService.getDashboardStatistics();
    }

    @GetMapping("/daily")
    public Mono<Map<String, Map<String, Long>>> getDailyStatistics() {
        return statisticsService.getDailyStatistics();
    }

    @GetMapping("/channel-distribution")
    public Mono<Map<String, Double>> getChannelDistribution() {
        return statisticsService.getChannelDistribution();
    }
}
