package org.example.task_tracker_springboot.controller;

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

        if (task.getTitle() == null || task.getTitle().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        if (tasks.size() >= maxTasks) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Long id = idGenerator.getAndIncrement();

        task.setId(id);
        tasks.put(id, task);

        URI location = URI.create("/api/tasks/" + id);

        return ResponseEntity.created(location).body(task);
    }

    @GetMapping
    public ResponseEntity<Collection<Task>> getByCompletion(
            @RequestParam(name = "completed", required = false) Boolean completed,
            @RequestParam(name = "limit", required = false) Integer size) {

        int effectiveLimit = size == null ? pageSize : size;

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

        if (!tasks.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }

        if (task.getTitle() == null || task.getTitle().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        task.setId(id);
        tasks.put(id, task);

        return ResponseEntity.ok(task);
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<Task> markCompleted(@PathVariable Long id) {
        Task task = tasks.get(id);
        if (task != null) {
            task.setCompleted(true);
            return ResponseEntity.ok().body(task);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        if (!tasks.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        tasks.remove(id);
        return ResponseEntity.noContent().build();

    }
}
