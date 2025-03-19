package com.zhechengdata.autopush.controller;

import com.zhechengdata.autopush.dto.PushTaskDto;
import com.zhechengdata.autopush.service.PushTaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import jakarta.validation.Valid;

/**
 * @author 清汤白面
 */
@RestController
@RequestMapping("/api/tasks")
public class PushTaskController {

    private final PushTaskService pushTaskService;

    public PushTaskController(PushTaskService pushTaskService) {
        this.pushTaskService = pushTaskService;
    }

    @GetMapping
    public Flux<PushTaskDto> getAllTasks() {
        return pushTaskService.getAllTasks();
    }

    @GetMapping("/by-status")
    public Flux<PushTaskDto> getTasksByStatus(@RequestParam("status") String status) {
        return pushTaskService.getTasksByStatus(status);
    }

    @GetMapping("/by-id")
    public Mono<PushTaskDto> getTaskById(@RequestParam("id") Long id) {
        return pushTaskService.getTaskById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<PushTaskDto> createTask(@Valid @RequestBody PushTaskDto task) {
        return pushTaskService.createTask(task);
    }

    @PutMapping
    public Mono<PushTaskDto> updateTask(@Valid @RequestBody PushTaskDto task) {
        return pushTaskService.updateTask(task);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteTask(@RequestParam("id") Long id) {
        return pushTaskService.deleteTask(id);
    }

    @PostMapping("/execute")
    public Mono<PushTaskDto> executeTask(@RequestParam("id") Long id) {
        return pushTaskService.executeTask(id);
    }
}
