package com.example.demo.exception;

public class MonthlyFeeNotSetException extends RuntimeException {
    public MonthlyFeeNotSetException(String message) {
        super(message);
    }
}