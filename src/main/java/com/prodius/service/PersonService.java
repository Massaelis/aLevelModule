package com.prodius.service;

import com.prodius.module.Customer;

import java.util.Random;

public class PersonService {
    private Random RANDOM = new Random();

    public Customer createPerson(){
        Customer customer = new Customer();
        customer.setEmail(RANDOM.nextInt(1000000) + "@icloud.com");
        customer.setAge(RANDOM.nextInt(1, 91));
        return customer;
    }
}