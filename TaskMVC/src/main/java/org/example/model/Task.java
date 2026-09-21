package org.example.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class Task {
    @NotNull(message = "Id cannot be null")
    private int id;
    @NotBlank(message = "Tasks should have a title")
    private String title;
    @NotNull(message = "Tasks should be assigned status")
    private boolean completed;
    @NotNull(message = "Tasks should be assigned priority")
    private Priority priority;

    public Task() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
