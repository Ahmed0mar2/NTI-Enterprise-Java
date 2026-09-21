package org.example.web;

import jakarta.validation.Valid;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;
import org.example.model.Task;
import org.example.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public ModelAndView listAll(Model model) {
        ModelAndView result = new ModelAndView("list");
        result.addObject("tasks", service.getAllTasks());
        return result;
    }

    @GetMapping("/{id}")
    public ModelAndView getById(@PathVariable("id") int id, Model model) {
        ModelAndView result = new ModelAndView("id");
        result.addObject("task", service.getTaskByID(id));
        return result;
    }

    @GetMapping(value = "/search")
    public ModelAndView search(@RequestParam(name = "priority", defaultValue = "HIGH") String priority, Model model) {
        ModelAndView result = new ModelAndView("priority");
        result.addObject("prioritytasks", service.getByPriority(priority));
        return result;
    }

    @GetMapping("/new")
    public ModelAndView showCreateForm() {
        ModelAndView result = new ModelAndView("create");
        result.addObject("task", new Task());
        return result;
    }

    @PostMapping
    public String createTask(
            @Valid @ModelAttribute("task") Task task,
            BindingResult result) {

        if (result.hasErrors()) {
            return "create";
        }

        service.createTask(task);

        return "redirect:/tasks";
    }
}
