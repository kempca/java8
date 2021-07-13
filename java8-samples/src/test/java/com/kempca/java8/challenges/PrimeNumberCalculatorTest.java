package com.kempca.java8.challenges;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrimeNumberCalculatorTest {

    @Test
    void calculate() {
        assertEquals(4, PrimeNumberCalculator.calculate(10));
        assertEquals(8, PrimeNumberCalculator.calculate(20));
        assertEquals(10, PrimeNumberCalculator.calculate(30));
    }
}