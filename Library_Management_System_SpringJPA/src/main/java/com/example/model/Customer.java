package com.example.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Cust")
public class Customer extends Person{
    private String loyaltyService;
}
