package com.zhechengdata.autopush.controller;

import com.zhechengdata.autopush.dto.ChannelConfigDto;
import com.zhechengdata.autopush.request.ChannelConfigTestRequest;
import com.zhechengdata.autopush.result.ApiResponse;
import com.zhechengdata.autopush.service.ChannelConfigService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import jakarta.validation.Valid;

/**
 * @author 清汤白面
 */
@RestController
@RequestMapping("/api/channel-configs")
public class ChannelConfigController {

    private final ChannelConfigService channelConfigService;

    public ChannelConfigController(ChannelConfigService channelConfigService) {
        this.channelConfigService = channelConfigService;
    }

    @GetMapping
    public Flux<ChannelConfigDto> getAllChannelConfigs() {
        return channelConfigService.getAllChannelConfigs();
    }

    @GetMapping("/by-type")
    public Flux<ChannelConfigDto> getChannelConfigsByType(@RequestParam("type") String type) {
        return channelConfigService.getChannelConfigsByType(type);
    }

    @GetMapping("/by-id")
    public Mono<ChannelConfigDto> getChannelConfigById(@RequestParam("id") Long id) {
        return channelConfigService.getChannelConfigById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ChannelConfigDto> createChannelConfig(@Valid @RequestBody ChannelConfigDto channelConfig) {
        return channelConfigService.createChannelConfig(channelConfig);
    }

    @PutMapping
    public Mono<ChannelConfigDto> updateChannelConfig(@Valid @RequestBody ChannelConfigDto channelConfig) {
        return channelConfigService.updateChannelConfig(channelConfig);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteChannelConfig(@RequestParam("id") Long id) {
        return channelConfigService.deleteChannelConfig(id);
    }

    @PostMapping("/test")
    public Mono<ApiResponse> testChannelConfig(@Valid @RequestBody ChannelConfigTestRequest testRequest) {
        return channelConfigService.testChannelConfig(testRequest.getId(), testRequest.getTestParams())
                .map(result -> {
                    if (result) {
                        return new ApiResponse(true, "测试成功", null);
                    } else {
                        return new ApiResponse(false, "测试失败", null);
                    }
                });
    }
}
