package com.training.empmanager.service;

import com.training.empmanager.model.Employee;


public class EmployeeValidator {
    public void validate(Employee employee) throws InvalidEmployeeException {
        if (employee.getName().isBlank() || employee.getSalary() < 0) {
            throw new InvalidEmployeeException();
        }
    }
}
