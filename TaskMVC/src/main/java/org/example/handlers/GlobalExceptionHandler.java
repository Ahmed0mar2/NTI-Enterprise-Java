package org.example.handlers;

import org.example.exceptions.InvalidTaskException;
import org.example.exceptions.NoSuchTaskException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchTaskException.class)
    public String handleNotFound(NoSuchTaskException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "error/404";
    }

    @ExceptionHandler(InvalidTaskException.class)
    public String handleInvalidData(InvalidTaskException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "error/400";
    }
}
