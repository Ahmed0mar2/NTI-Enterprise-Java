package com.training.empmanager.service;

import com.training.empmanager.audit.AuditLogger;
import com.training.empmanager.model.Employee;
import com.training.empmanager.notify.NotificationManager;
import com.training.empmanager.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import java.util.List;

@Service
@DependsOn("notificationManager")
@PropertySource("classpath:application.properties")
public class EmployeeServiceImpl implements EmployeeService {
    private final NotificationManager notificationManager;
    private final EmployeeRepository repository;
    private final EmployeeValidator employeeValidator;
    private final AuditLogger auditLogger;
    @Value("${raise.max-percentage}")
    private double maxRaise;
    @Value("${company.name}")
    private String companyName;

    @Value("${company.currency}")
    private String currency;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository repository, NotificationManager notificationManager, EmployeeValidator employeeValidator, AuditLogger auditLogger) {
        this.repository = repository;
        this.notificationManager = notificationManager;
        this.employeeValidator = employeeValidator;
        this.auditLogger = auditLogger;
    }

    @Override
    public boolean addEmployee(Employee employee) {
        try {
            auditLogger.log();
            employeeValidator.validate(employee);

            if (repository.findById(employee.getId()).isPresent()) {
                return false;
            }

            auditLogger.log();
            repository.saveEmployee(employee);

            notificationManager.sendNotification(
                    "New employee was added: " + employee.getName()
            );

            auditLogger.log();
            return true;

        } catch (Exception e) {
            System.out.println("Failed to add employee: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Employee getEmployeeById(int id) throws InvalidEmployeeException {
        return repository.findById(id).orElseThrow(InvalidEmployeeException::new);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    @Override
    public void giveRaise(int id, double percentage) {
        try {
            auditLogger.log();
            Employee employee = getEmployeeById(id);
            employeeValidator.validate(employee);
            if (percentage > maxRaise) {
                System.out.println("Max raise percentage is: " + maxRaise + "%");
                return;
            }

            employee.setSalary(employee.getSalary() * (1 + percentage / 100));
            notificationManager.sendNotification(
                    "Employee " + employee.getName() +
                            " got a raise with " + percentage + "%"
            );
            auditLogger.log();
        } catch (InvalidEmployeeException e) {
            System.out.println("No such employee exists");
            auditLogger.log();
        }

    }

    @PostConstruct
    public void init() {
        System.out.println("Employee service layer is being initialized...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Employee service layer is being destroyed...");
    }
}
