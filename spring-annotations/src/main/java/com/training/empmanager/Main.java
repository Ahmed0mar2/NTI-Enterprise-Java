package com.training.empmanager;

import com.training.empmanager.config.AppConfig;
import com.training.empmanager.model.Employee;
import com.training.empmanager.service.EmployeeService;
import com.training.empmanager.service.EmployeeServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("dev");
        context.register(AppConfig.class);
        context.refresh();

        EmployeeService employeeService = context.getBean(EmployeeServiceImpl.class);
        employeeService.addEmployee(new Employee(1, "mahmoud", "HR", 20000));
    }
}
