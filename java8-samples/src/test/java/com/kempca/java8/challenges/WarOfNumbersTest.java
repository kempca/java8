package com.kempca.java8.challenges;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarOfNumbersTest {

    @Test
    void calculateOddsWin() {

        int result = WarOfNumbers.calculate(new Integer[]{2, 8, 7, 5});

        assertEquals(2, result);
    }

    @Test
    void calculateEvensWin() {

        int result = WarOfNumbers.calculate(new Integer[]{2, 16, 7, 5});

        assertEquals(6, result);
    }

    @Test
    void calculateWithNoEvens() {

        int result = WarOfNumbers.calculate(new Integer[]{7, 5});

        assertEquals(12, result);
    }

    @Test
    void calculateWithNoOdds() {

        int result = WarOfNumbers.calculate(new Integer[]{2, 8});

        assertEquals(10, result);
    }
}