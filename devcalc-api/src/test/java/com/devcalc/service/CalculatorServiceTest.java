package com.devcalc.service;

import com.devcalc.exception.DivisionByZeroException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorServiceTest {

    CalculatorService service = new CalculatorService();

    @Test
    public void addTest(){
        assertEquals(15, service.add(10 , 5));
        assertEquals(27.8, service.add(13.5, 14.3));
    }

    @Test
    public void subtractTest(){
        assertEquals(5 , service.subtract(10, 5));
        assertEquals(36.4, service.subtract(49.9, 13.5));
    }

    @Test
    public void multiplyTest(){
        assertEquals(50, service.multiply(10, 5));
        assertEquals(29.15, service.multiply(5.3, 5.5));
    }

    @Test
    public void divideTest() throws DivisionByZeroException {
        assertEquals(2, service.divide(10, 5));
        assertEquals(4.5, service.divide(18, 4));
    }

    @Test
    void testSqrtComValorPositivo(){
        CalculatorService service = new CalculatorService();
        double resultado = service.sqrt(16);
        assertEquals(4.0, resultado, 0.0001);
    }

    @Test
    void testSqrtComValorNegativo(){
        CalculatorService service = new CalculatorService();
        assertThrows(IllegalArgumentException.class, () -> {
            service.sqrt(-9);
        });
    }
}
