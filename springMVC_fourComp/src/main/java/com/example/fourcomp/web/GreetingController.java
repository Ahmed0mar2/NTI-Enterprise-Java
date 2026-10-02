package com.example.fourcomp.web;

import com.example.fourcomp.service.GreetingService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class GreetingController extends AbstractController {

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
                                                 HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("hello");
        mav.addObject("message", "Here the message");
        mav.addObject("name", greetingService.greet("Hamada"));
        return mav;
    }
}
