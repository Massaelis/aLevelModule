package com.prodius.module;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class Television extends ProductVariables {

    private int diagonal;
    private String country;

    public Television() {
        setType(ProductType.TELEVISION);
    }
}