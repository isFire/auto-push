package com.zhechengdata.autopush.service;

import com.zhechengdata.autopush.dto.ChannelConfigDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ChannelConfigService {
    Flux<ChannelConfigDto> getAllChannelConfigs();
    Flux<ChannelConfigDto> getChannelConfigsByType(String type);
    Mono<ChannelConfigDto> getChannelConfigById(Long id);
    Mono<ChannelConfigDto> createChannelConfig(ChannelConfigDto config);
    Mono<ChannelConfigDto> updateChannelConfig(ChannelConfigDto config);
    Mono<Void> deleteChannelConfig(Long id);
    Mono<Boolean> testChannelConfig(Long id, String testParams);
}
