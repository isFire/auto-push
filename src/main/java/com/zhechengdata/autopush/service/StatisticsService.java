package com.zhechengdata.autopush.service;

import com.zhechengdata.autopush.model.StatisticsData;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @author 清汤白面
 */
public interface StatisticsService {

    Mono<StatisticsData> getDashboardStatistics();

    Mono<Map<String, Map<String, Long>>> getDailyStatistics();

    Mono<Map<String, Double>> getChannelDistribution();

}
