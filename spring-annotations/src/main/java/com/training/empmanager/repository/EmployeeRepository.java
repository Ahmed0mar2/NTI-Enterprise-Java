package com.training.empmanager.repository;

import com.training.empmanager.model.Employee;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository {
    void saveEmployee(Employee employee) throws IOException;

    Optional<Employee> findById(int id);

    List<Employee> findAll();
}
