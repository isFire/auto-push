package com.zhechengdata.autopush.service;

import com.zhechengdata.autopush.dto.PushTaskDto;
import com.zhechengdata.autopush.model.PushTask;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author 清汤白面
 */
public interface PushTaskService {
    Flux<PushTaskDto> getAllTasks();
    Flux<PushTaskDto> getTasksByStatus(String status);
    Mono<PushTaskDto> getTaskById(Long id);
    Mono<PushTaskDto> createTask(PushTaskDto task);
    Mono<PushTaskDto> updateTask(PushTaskDto task);

	Mono<PushTaskDto> createTask(PushTask pushTask);

	Mono<PushTaskDto> updateTask(PushTask pushTask);

	Mono<Void> deleteTask(Long id);
    Mono<PushTaskDto> executeTask(Long id);
}
