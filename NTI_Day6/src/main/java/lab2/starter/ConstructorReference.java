package lab2.starter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// Exercise 2.4 — Constructor Reference Challenge
//
// TODO 1: Create a Function<String, Employee> called "employeeFactory" using a
//         CONSTRUCTOR REFERENCE (hint: Employee::new)
// TODO 2: Loop over "names" and use employeeFactory to build a List<Employee>
// TODO 3: Print the resulting list

public class ConstructorReference {

    static class Employee {
        private final String name;

        public Employee(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Employee(" + name + ")";
        }
    }

    public static void main(String[] args) {
        List<String> names = List.of("Sara", "Omar", "Lina");

        // TODO 1: define employeeFactory
        Function<String, Employee> employeeFactory = Employee::new;

        // TODO 2 & 3: build the List<Employee> and print it

        List<Employee> employees = names.stream().map(employeeFactory).toList();
        employees.forEach(System.out::println);
    }
}
