package org.example.task_tracker_springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.task_tracker_springboot.domain.Task;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@RestController
@RequestMapping("/api/tasks")
public class ProductController {

    private static final Map<Long, Task> tasks = new HashMap<>();
    private static final AtomicLong idGenerator = new AtomicLong();
    @Value("${tasktracker.default-page-size}")
    private int pageSize;
    @Value("${tasktracker.max-tasks}")
    private int maxTasks;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        log.info("Creating task with title: {}", task.getTitle());
        if (task.getTitle() == null || task.getTitle().isBlank()) {
            log.error("Task creation rejected: title is missing");
            return ResponseEntity.badRequest().build();
        }

        if (tasks.size() >= maxTasks) {
            log.error("Task creation rejected: maximum task limit of {} reached", maxTasks);
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Long id = idGenerator.getAndIncrement();

        task.setId(id);
        tasks.put(id, task);

        URI location = URI.create("/api/tasks/" + id);
        log.info("Task created successfully with id: {}", id);
        return ResponseEntity.created(location).body(task);
    }

    @GetMapping
    public ResponseEntity<Collection<Task>> getByCompletion(
            @RequestParam(name = "completed", required = false) Boolean completed,
            @RequestParam(name = "limit", required = false) Integer size) {

        int effectiveLimit = size == null ? pageSize : size;
        log.info("Fetching tasks. completed={}, limit={}", completed, effectiveLimit);
        return ResponseEntity.ok(
                tasks.values()
                        .stream()
                        .filter(t -> completed == null || t.isCompleted() == completed)
                        .limit(effectiveLimit)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getById(@PathVariable Long id) {
        Task task = tasks.get(id);
        return task == null ? ResponseEntity.notFound().build() : ResponseEntity.ok().body(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(
            @PathVariable Long id,
            @RequestBody Task task) {
        log.info("Updating task with id: {}", id);
        if (!tasks.containsKey(id)) {
            log.error("Cannot update task. Task not found with id: {}", id);
            return ResponseEntity.notFound().build();
        }

        if (task.getTitle() == null || task.getTitle().isBlank()) {
            log.error("Cannot update task {}. Title is missing", id);
            return ResponseEntity.badRequest().build();
        }

        task.setId(id);
        tasks.put(id, task);
        log.info("Task updated successfully with id: {}", id);
        return ResponseEntity.ok(task);
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<Task> markCompleted(@PathVariable Long id) {
        log.info("Marking task {} as completed", id);
        Task task = tasks.get(id);
        if (task != null) {
            task.setCompleted(true);
            log.info("Task {} marked as completed", id);
            return ResponseEntity.ok().body(task);
        }
        log.error("Cannot complete task. Task not found with id: {}", id);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        log.info("Deleting task with id: {}", id);
        if (!tasks.containsKey(id)) {
            log.error("Cannot delete task. Task not found with id: {}", id);
            return ResponseEntity.notFound().build();
        }
        tasks.remove(id);
        log.info("Task {} deleted successfully", id);
        return ResponseEntity.noContent().build();

    }
}
