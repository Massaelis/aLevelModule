package com.prodius.exception;

import lombok.ToString;

@ToString
public class IncorrectString extends RuntimeException {

    private static final String IncorrectString = "Incorrect string";

    public IncorrectString(){
        super(IncorrectString);
    }
}