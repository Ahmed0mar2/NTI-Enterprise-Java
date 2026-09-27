package com.training.empmanager.service;

import com.training.empmanager.model.Employee;

import java.util.List;


public interface EmployeeService {

    boolean addEmployee(Employee employee);

    Employee getEmployeeById(int id);

    List<Employee> getAllEmployees();

    void giveRaise(int id, double percentage);

}
