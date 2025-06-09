package com.devcalc.service;

import com.devcalc.exception.DivisionByZeroException;

public class CalculatorService {

    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) throws DivisionByZeroException {
        if(b == 0 ) throw new DivisionByZeroException("O divisor não pode ser zero");
        return a / b;
    }

    public double sqrt(double x){
        if(x < 0){
            throw new IllegalArgumentException("Número negativo não possui raiz quadadra real.");
        }
        return Math.sqrt(x);
    }
}
