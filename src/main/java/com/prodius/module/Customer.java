package com.prodius.module;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Customer {
    private final String id;
    private String email;
    private int age;

    public Customer() {
        this.id = UUID.randomUUID().toString();
    }
}