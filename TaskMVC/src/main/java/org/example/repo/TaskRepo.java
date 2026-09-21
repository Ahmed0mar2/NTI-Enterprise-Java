package org.example.repo;

import org.example.model.Priority;
import org.example.model.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TaskRepo {
    private static final List<Task> tasks = new ArrayList<>();

    public List<Task> getAll() {
        return new ArrayList<>(tasks);
    }

    public Optional<Task> getById(int id) {
        return tasks.stream().
                filter(t -> t.getId() == id).
                findFirst();
    }

    public List<Task> getByPriority(String Priority) {
        String filter = Priority.toLowerCase().trim();
        return tasks.stream()
                .filter(t -> t.getPriority().equals(filter)).
                collect(Collectors.toList());
    }

    public Task createTask(Task task){
        tasks.add(task);
        return task;
    }

}
