package com.kempca.java8.sorting;

import com.kempca.java8.model.Customer;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class StreamTest {

    @Test
    void filterEvenIntegers() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> result = numbers.stream()
                .filter(number -> number % 2 == 0)
                .collect(Collectors.toList());

        assertEquals(Arrays.asList(2, 4, 6, 8, 10), result);
    }

    @Test
    void sumEvenIntegers() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        int result = numbers.stream()
                .filter(number -> number % 2 == 0)
                .reduce(0, (subTotal, value) -> subTotal + value);

        assertEquals(30, result);
    }

    @Test
    void groupingBy() {
        Customer customer1 = new Customer("Bob");
        Customer customer2 = new Customer("Bob");
        Customer customer3 = new Customer("Tom");
        List<Customer> customers = Arrays.asList(customer1, customer2, customer3);

        Map<String, List<Customer>> customersByFirstName = customers.stream()
                .collect(Collectors.groupingBy(Customer::getFirstName));

        assertEquals(2, customersByFirstName.get("Bob").size());
        assertEquals(customer1, customersByFirstName.get("Bob").get(0));
        assertEquals(customer2, customersByFirstName.get("Bob").get(1));

        assertEquals(1, customersByFirstName.get("Tom").size());
        assertEquals(customer3, customersByFirstName.get("Tom").get(0));
    }

    @Test
    void average() {
        Customer customer1 = new Customer(30);
        Customer customer2 = new Customer(45);
        List<Customer> customers = Arrays.asList(customer1, customer2);

        Double result = customers.stream()
                .collect(Collectors.averagingInt(Customer::getAge));

        assertEquals(37.5, result);
    }

    @Test
    void findFirstUsingOptional() {
        Customer customer1 = new Customer("Bob");
        Customer customer2 = new Customer("Bob");
        Customer customer3 = new Customer("Tom");
        List<Customer> customers = Arrays.asList(customer1, customer2, customer3);

        Optional<Customer> result = customers.stream()
                .filter(customer -> "Bob".equals(customer.getFirstName()))
                .findFirst();

        assertTrue(result.isPresent());
        assertEquals(customer1, result.get());
    }

    @Test
    void findFirstUsingNull() {
        Customer customer1 = new Customer("Bob");
        Customer customer2 = new Customer("Bob");
        Customer customer3 = new Customer("Tom");
        List<Customer> customers = Arrays.asList(customer1, customer2, customer3);

        Customer result = customers.stream()
                .filter(customer -> "Megan".equals(customer.getFirstName()))
                .findFirst().orElse(null);

        assertNull(result);
    }
}
