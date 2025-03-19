package com.zhechengdata.autopush.repository;

import com.zhechengdata.autopush.entity.ChannelConfig;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * @author 清汤白面
 */
@Repository
public interface ChannelConfigRepository extends ReactiveCrudRepository<ChannelConfig, Long> {

    Flux<ChannelConfig> findByType(String type);

    @Query("SELECT * FROM channel_configs WHERE type = :type AND enabled = :enabled")
    Flux<ChannelConfig> findByTypeAndEnabled(String type, boolean enabled);
}
