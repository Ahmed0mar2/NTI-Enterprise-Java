package com.training.empmanager.repository;

import com.training.empmanager.model.Employee;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Profile("dev")
@Repository
public class InMemoryEmployeeRepository implements EmployeeRepository {
    private static final List<Employee> employees = new ArrayList<>();

    @Override
    public void saveEmployee(Employee employee) {
        employees.add(employee);
    }

    @Override
    public Optional<Employee> findById(int id) {
        return employees.stream().
                filter(e -> e.getId() == id).
                findFirst();
    }

    @Override
    public List<Employee> findAll() {
        return new ArrayList<>(employees);
    }
}
