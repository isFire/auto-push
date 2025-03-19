package com.zhechengdata.autopush.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhechengdata.autopush.dto.ChannelConfigDto;
import com.zhechengdata.autopush.entity.ChannelConfig;
import com.zhechengdata.autopush.exception.ResourceNotFoundException;
import com.zhechengdata.autopush.repository.ChannelConfigRepository;
import com.zhechengdata.autopush.service.ChannelConfigService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author 清汤白面
 */
@Service
public class ChannelConfigServiceImpl implements ChannelConfigService {

    private final ChannelConfigRepository channelConfigRepository;
    private final ObjectMapper objectMapper;

    public ChannelConfigServiceImpl(ChannelConfigRepository channelConfigRepository, ObjectMapper objectMapper) {
        this.channelConfigRepository = channelConfigRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public Flux<ChannelConfigDto> getAllChannelConfigs() {
        return channelConfigRepository.findAll()
                .map(this::convertToDto);
    }

    @Override
    public Flux<ChannelConfigDto> getChannelConfigsByType(String type) {
        return channelConfigRepository.findByType(type)
                .map(this::convertToDto);
    }

    @Override
    public Mono<ChannelConfigDto> getChannelConfigById(Long id) {
        return channelConfigRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("渠道配置不存在: " + id)))
                .map(this::convertToDto);
    }

    @Override
    public Mono<ChannelConfigDto> createChannelConfig(ChannelConfigDto configDto) {
        ChannelConfig config = convertToEntity(configDto);
        config.setCreateTime(LocalDateTime.now());
        config.setUpdateTime(LocalDateTime.now());
        config.setEnabled(true);

        return channelConfigRepository.save(config)
                .map(this::convertToDto);
    }

    @Override
    public Mono<ChannelConfigDto> updateChannelConfig(ChannelConfigDto configDto) {
        if (configDto.getId() == null) {
            return Mono.error(new IllegalArgumentException("更新配置时ID不能为空"));
        }

        return channelConfigRepository.findById(configDto.getId())
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("渠道配置不存在: " + configDto.getId())))
                .flatMap(existingConfig -> {
                    ChannelConfig updatedConfig = convertToEntity(configDto);
                    updatedConfig.setId(existingConfig.getId());
                    updatedConfig.setCreateTime(existingConfig.getCreateTime());
                    updatedConfig.setUpdateTime(LocalDateTime.now());
                    return channelConfigRepository.save(updatedConfig);
                })
                .map(this::convertToDto);
    }

    @Override
    public Mono<Void> deleteChannelConfig(Long id) {
        return channelConfigRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("渠道配置不存在: " + id)))
                .flatMap(channelConfigRepository::delete);
    }

    @Override
    public Mono<Boolean> testChannelConfig(Long id, String testParams) {
        return channelConfigRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("渠道配置不存在: " + id)))
                .flatMap(config -> {
                    // 根据不同渠道类型实现测试逻辑
                    try {
                        Map<String, Object> configData = objectMapper.readValue(config.getConfigData(), Map.class);
                        Map<String, Object> testParamsMap = objectMapper.readValue(testParams, Map.class);

                        switch (config.getType()) {
                            case "email":
                                return testEmailConfig(configData, testParamsMap);
                            case "wecom":
                                return testWecomConfig(configData, testParamsMap);
                            // 其他渠道类型的测试实现
                            default:
                                return Mono.just(false);
                        }
                    } catch (JsonProcessingException e) {
                        return Mono.error(new RuntimeException("配置数据格式错误", e));
                    }
                });
    }

    private ChannelConfigDto convertToDto(ChannelConfig entity) {
        ChannelConfigDto dto = new ChannelConfigDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setType(entity.getType());
        dto.setCreateTime(entity.getCreateTime());
        dto.setUpdateTime(entity.getUpdateTime());
        dto.setEnabled(entity.isEnabled());

        try {
            dto.setConfigData(objectMapper.readValue(entity.getConfigData(), Map.class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("配置数据格式错误", e);
        }

        return dto;
    }

    private ChannelConfig convertToEntity(ChannelConfigDto dto) {
        ChannelConfig entity = new ChannelConfig();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setType(dto.getType());
        entity.setCreateTime(dto.getCreateTime());
        entity.setUpdateTime(dto.getUpdateTime());
        entity.setEnabled(dto.isEnabled());

        try {
            entity.setConfigData(objectMapper.writeValueAsString(dto.getConfigData()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("配置数据格式错误", e);
        }

        return entity;
    }

    private Mono<Boolean> testEmailConfig(Map<String, Object> configData, Map<String, Object> testParams) {
        // 实现邮件渠道测试逻辑
        return Mono.just(true);
    }

    private Mono<Boolean> testWecomConfig(Map<String, Object> configData, Map<String, Object> testParams) {
        // 实现企业微信渠道测试逻辑
        return Mono.just(true);
    }
}
