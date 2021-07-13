package com.kempca.java8.sorting;

import com.kempca.java8.model.Customer;
import com.kempca.java8.model.Person;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    @Test
    void filterUsingNamedPredicate() {
        Customer customer1 = new Customer("Bob");
        Customer customer2 = new Customer("Bob");
        Customer customer3 = new Customer("Tom");
        List<Customer> customers = Arrays.asList(customer1, customer2, customer3);

        List<Customer> customersNamedTom = customers.stream()
                .filter(new CustomerFirstNamePredicate("Tom"))
                .collect(Collectors.toList());

        assertEquals(1, customersNamedTom.size());
        assertEquals(customer3, customersNamedTom.get(0));
    }

    @Test
    void mapUsingNamedFunction() {
        Customer customer1 = new Customer("Bob");
        Customer customer2 = new Customer("Tom");
        List<Customer> customers = Arrays.asList(customer1, customer2);

        List<Person> peopleNamedTom = customers.stream()
                .filter(new CustomerFirstNamePredicate("Tom"))
                .map(new CustomerToPersonFunction())
                .collect(Collectors.toList());

        assertEquals(1, peopleNamedTom.size());
        assertEquals("Tom", peopleNamedTom.get(0).getFirstName());
    }

    @Test
    void filterAndMapUsingLambdaFunctions() {
        Customer customer1 = new Customer("Bob");
        Customer customer2 = new Customer("Tom");
        List<Customer> customers = Arrays.asList(customer1, customer2);

        List<Person> peopleNamedTom = customers.stream()
                .filter(customer -> customer.getFirstName().equals("Tom"))
                .map(customer -> {
                    return new Person(customer.getFirstName());
                })
                .collect(Collectors.toList());

        assertEquals(1, peopleNamedTom.size());
        assertEquals("Tom", peopleNamedTom.get(0).getFirstName());
    }

    private class CustomerFirstNamePredicate implements Predicate<Customer> {

        private String firstName;

        public CustomerFirstNamePredicate(String firstName) {
            this.firstName = firstName;
        }

        @Override
        public boolean test(Customer customer) {
            return firstName.equals(customer.getFirstName());
        }
    }

    private class CustomerToPersonFunction implements Function<Customer, Person> {

        @Override
        public Person apply(Customer customer) {
            Person person = new Person(customer.getFirstName());
            return person;
        }
    }
}
