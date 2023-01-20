package com.prodius.module;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class Telephone extends ProductVariables {

    private String model;

    public Telephone() {
        setType(ProductType.TELEPHONE);
    }
}