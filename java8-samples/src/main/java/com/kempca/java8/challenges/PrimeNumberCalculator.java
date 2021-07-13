package com.kempca.java8.challenges;

import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Create a function that finds how many prime numbers there are, up to the given integer.
 */
public class PrimeNumberCalculator {

    private final static PrimePredicate primePredicate = new PrimePredicate();

    public static long calculate(int max) {
        return IntStream.range(0, max + 1).mapToObj(Integer::valueOf)
                .filter(primePredicate)
                .count();
    }

    private static class PrimePredicate implements Predicate<Integer> {

        @Override
        public boolean test(Integer integer) {
            if (integer % 2 == 0) {
                return false;
            }
            for (int i=3; i * i <= integer; i += 2) {
                if (integer % i==0) {
                    return false;
                }
            }
            return true;
        }

    }
}
