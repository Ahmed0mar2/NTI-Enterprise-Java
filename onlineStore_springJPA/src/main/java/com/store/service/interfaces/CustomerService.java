package com.store.service.interfaces;

import com.store.model.Address;
import com.store.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer register(String name, String email, Address shippingAddress);
    Customer findById(Long id);
    List<Customer> findAll();
}
