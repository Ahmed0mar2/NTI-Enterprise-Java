package com.training.empmanager;

import com.training.empmanager.audit.AuditLogger;
import com.training.empmanager.config.AppConfig;
import com.training.empmanager.model.Employee;
import com.training.empmanager.service.EmployeeService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext();

        context.getEnvironment().setActiveProfiles("dev");
        context.register(AppConfig.class);
        context.refresh();

        EmployeeService employeeService =
                context.getBean(EmployeeService.class);

        System.out.println("Adding employee");

        boolean added = employeeService.addEmployee(
                new Employee(1, "Mahmoud", "HR", 20000)
        );

        System.out.println("Employee added: " + added);


        System.out.println("Adding invalid employee");

        boolean invalidAdded = employeeService.addEmployee(
                new Employee(2, "", "HR", -5000)
        );

        System.out.println("Employee added: " + invalidAdded);


        employeeService.giveRaise(1, 10);


        employeeService.giveRaise(1, 50);


        AuditLogger logger1 = context.getBean(AuditLogger.class);
        logger1.log();

        AuditLogger logger2 = context.getBean(AuditLogger.class);
        logger2.log();

        AuditLogger logger3 = context.getBean(AuditLogger.class);
        logger3.log();


        System.out.println("\n=== All employees ===");

        employeeService.getAllEmployees()
                .forEach(System.out::println);


        System.out.println("\n=== Closing application ===");

        context.close();
    }
}