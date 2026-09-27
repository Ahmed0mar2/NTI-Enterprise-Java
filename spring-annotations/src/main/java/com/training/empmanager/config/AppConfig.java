package com.training.empmanager.config;

import com.training.empmanager.repository.InMemoryEmployeeRepository;
import com.training.empmanager.service.EmployeeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.training.empmanager")
public class AppConfig {
    /*  @Bean
      public InMemoryEmployeeRepository inMemoryEmployeeRepository() {
          return new InMemoryEmployeeRepository();
      }
  */
    @Bean
    public EmployeeValidator employeeValidator() {
        return new EmployeeValidator();
    }
}