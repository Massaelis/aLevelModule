package com.prodius.module;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class ProductVariables {

    private ProductType type;
    private String series;
    private String screenType;
    private int price;
}