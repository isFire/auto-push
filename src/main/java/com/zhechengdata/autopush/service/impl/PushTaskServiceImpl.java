package com.zhechengdata.autopush.service.impl;

import com.zhechengdata.autopush.dto.PushTaskDto;
import com.zhechengdata.autopush.model.PushTask;
import com.zhechengdata.autopush.service.PushTaskService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author 清汤白面
 */
@Service
public class PushTaskServiceImpl implements PushTaskService {

    private final ConcurrentHashMap<Long, PushTaskDto> taskStore = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    // 模拟数据初始化
    public PushTaskServiceImpl() {
        // 初始化一些示例任务
        createMockTasks();
    }

    private void createMockTasks() {
        PushTask task1 = new PushTask(
                idGenerator.getAndIncrement(),
                "系统告警通知",
                Arrays.asList("邮箱", "企业微信"),
                "同时使用",
                LocalDateTime.of(2024, 1, 15, 14, 30),
                "成功",
                142365L,
                14424L
        );

        PushTask task2 = new PushTask(
                idGenerator.getAndIncrement(),
                "每日数据报表",
                Arrays.asList("钉钉", "飞书"),
                "自动容错",
                LocalDateTime.of(2024, 1, 15, 15, 0),
                "失败",
                0L,
                100L
        );

        PushTask task3 = new PushTask(
                idGenerator.getAndIncrement(),
                "服务器监控告警",
                List.of("Webhook"),
                "同时使用",
                LocalDateTime.of(2024, 1, 15, 16, 20),
                "成功",
                250L,
                0L
        );

//        taskStore.put(task1.getId(), task1);
//        taskStore.put(task2.getId(), task2);
//        taskStore.put(task3.getId(), task3);
    }

    @Override
    public Flux<PushTaskDto> getAllTasks() {
        return Flux.fromIterable(new ArrayList<>(taskStore.values()));
    }

	@Override
	public Flux<PushTaskDto> getTasksByStatus(String status) {
		return null;
	}

	@Override
    public Mono<PushTaskDto> getTaskById(Long id) {
        return Mono.justOrEmpty(taskStore.get(id));
    }

	@Override
	public Mono<PushTaskDto> createTask(PushTaskDto task) {
		return null;
	}

	@Override
	public Mono<PushTaskDto> updateTask(PushTaskDto task) {
		return null;
	}

	@Override
	public Mono<PushTaskDto> createTask(PushTask pushTask) {
        pushTask.setId(idGenerator.getAndIncrement());
        if (pushTask.getCreateTime() == null) {
            pushTask.setCreateTime(LocalDateTime.now());
        }
        if (pushTask.getStatus() == null) {
            pushTask.setStatus("待执行");
        }
        if (pushTask.getSuccessCount() == null) {
            pushTask.setSuccessCount(0L);
        }
        if (pushTask.getFailCount() == null) {
            pushTask.setFailCount(0L);
        }

        taskStore.put(pushTask.getId(), new PushTaskDto());
        return Mono.just(new PushTaskDto());
    }

    @Override
    public Mono<PushTaskDto> updateTask(PushTask pushTask) {
        return Mono.justOrEmpty(taskStore.get(pushTask.getId()))
                .flatMap(existingTask -> {
                    taskStore.put(pushTask.getId(), new PushTaskDto());
                    return Mono.just(new PushTaskDto());
                });
    }

    @Override
    public Mono<Void> deleteTask(Long id) {
        taskStore.remove(id);
        return Mono.empty();
    }

    @Override
    public Mono<PushTaskDto> executeTask(Long id) {
        return Mono.justOrEmpty(taskStore.get(id))
                .flatMap(task -> {
                    // 模拟执行任务
                    task.setStatus("成功");
                    task.setSuccessCount(task.getSuccessCount() + 100);
                    taskStore.put(id, task);
                    return Mono.empty();
                });
    }
}
