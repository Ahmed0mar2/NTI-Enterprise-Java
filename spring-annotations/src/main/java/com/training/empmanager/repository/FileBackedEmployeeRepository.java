package com.training.empmanager.repository;

import com.training.empmanager.model.Employee;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Profile("prod")
@Repository
public class FileBackedEmployeeRepository implements EmployeeRepository {

    private static final Path file = Paths.get("employees.txt");

    @Override
    public void saveEmployee(Employee employee) throws IOException {
        String line = employee.getId() + ","
                + employee.getName() + ","
                + employee.getDepartment() + ","
                + employee.getSalary()
                + System.lineSeparator();
        Files.writeString(file, line, StandardOpenOption.CREATE, StandardOpenOption.APPEND);

    }

    @Override
    public Optional<Employee> findById(int id) {
        return findAll().stream().filter(e -> e.getId() == id).findFirst();

    }

    @Override
    public List<Employee> findAll() {
        try {
            if (!Files.exists(file)) {
                return new ArrayList<>();
            }

            return Files.readAllLines(file)
                    .stream()
                    .map(this::parseEmployee)
                    .toList();

        } catch (IOException e) {
            throw new RuntimeException("Could not read employee file", e);
        }
    }

    private Employee parseEmployee(String line) {
        String[] parts = line.split(",");

        int id = Integer.parseInt(parts[0]);
        String name = parts[1];
        String department = parts[2];
        double salary = Double.parseDouble(parts[3]);

        return new Employee(id, name, department, salary);
    }
}
