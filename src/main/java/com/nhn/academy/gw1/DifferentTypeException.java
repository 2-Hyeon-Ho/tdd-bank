package com.nhn.academy.gw1;

public class DifferentTypeException extends RuntimeException {
    public DifferentTypeException() {
        super("Type is different");
    }
}
