package com.kempca.java8.challenges;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * There's a great war between the even and odd numbers.
 * Many numbers already lost their life in this war and it's your task to end this.
 * You have to determine which group sums larger: the even, or the odd. The larger group wins.
 *
 * Create a function that takes an array of integers,
 * sums the even and odd numbers separately,
 * then returns the difference between sum of even and odd numbers.
 */
public class WarOfNumbers {

    public static int calculate(Integer[] numbers) {
        Map<NumberType, List<Integer>> numbersSplitByType = Stream.of(numbers)
                .collect(Collectors.groupingBy(number -> number % 2 == 0 ? NumberType.EVEN : NumberType.ODD));


        int evenTotal = numbersSplitByType.getOrDefault(NumberType.EVEN, new ArrayList<>()).stream()
                .reduce(0, Integer::sum);
        int oddTotal = numbersSplitByType.getOrDefault(NumberType.ODD, new ArrayList<>()).stream()
                .reduce(0, Integer::sum);

        return Math.abs(evenTotal - oddTotal);
    }

    private enum NumberType {
        EVEN,
        ODD
    }
}
