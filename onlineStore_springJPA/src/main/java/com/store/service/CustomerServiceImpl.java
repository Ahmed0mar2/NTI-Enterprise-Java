package com.store.service;

import com.store.exception.CustomerNotFoundException;
import com.store.exception.DuplicateCustomerException;
import com.store.model.Address;
import com.store.model.Customer;
import com.store.repository.interfaces.CustomerRepository;
import com.store.service.interfaces.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public Customer register(String name, String email, Address shippingAddress) {
        if (customerRepository.findByEmail(email).isPresent()) {
            throw new DuplicateCustomerException(email);
        }
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setShippingAddress(shippingAddress);
        return customerRepository.save(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
}
