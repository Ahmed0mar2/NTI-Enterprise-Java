package org.example.service;

import org.example.exceptions.InvalidTaskException;
import org.example.exceptions.NoSuchTaskException;
import org.example.model.Task;
import org.example.repo.TaskRepo;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepo repo;

    public TaskService(TaskRepo repo) {
        this.repo = repo;
    }

    public List<Task> getAllTasks() {
        return repo.getAll();
    }

    public Task getTaskByID(int id) {
        Optional<Task> task = repo.getById(id);
        if (task.isPresent())
            return task.get();
        else
            throw new NoSuchTaskException("Task with id: "+ id + " was not found");
    }

    public List<Task> getByPriority(String priority) {
        String filter = priority.trim().toLowerCase();
        return repo.getByPriority(filter);
    }
    public Task createTask(Task task){
        if(task != null){
            repo.createTask(task);
            return task;
        }
        else
            throw new InvalidTaskException("Invalid Task data");
    }
}
