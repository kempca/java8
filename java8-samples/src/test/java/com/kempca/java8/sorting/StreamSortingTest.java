package com.kempca.java8.sorting;

import com.kempca.java8.model.Customer;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class StreamSortingTest {

    @Test
    void sorted() {
        List<Integer> unsortedList = Arrays.asList(9, 5, 3, 8);

        List<Integer> result = unsortedList.stream()
                .sorted()
                .collect(Collectors.toList());

        assertEquals(Arrays.asList(3, 5, 8, 9), result);
    }

    @Test
    void sortedReversed() {
        List<Integer> unsortedList = Arrays.asList(9, 5, 3, 8);

        List<Integer> result = unsortedList.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        assertEquals(Arrays.asList(9, 8, 5, 3), result);
    }

    @Test
    void sortedWithCustomComparator() {
        Customer customer1 = new Customer("Bob", "Smith");
        Customer customer2 = new Customer("Alex", "Dabbel");
        Customer customer3 = new Customer("Gracie", "Hudson");
        Customer customer4 = new Customer("Alicia", "McDonald");
        Customer customer5 = new Customer("Alex", "Hanson");

        List<Customer> unsortedList = Arrays.asList(customer1, customer2, customer3, customer4, customer5);

        List<Customer> result = unsortedList.stream()
                .sorted(Comparator.comparing(Customer::getFirstName).thenComparing(Customer::getLastName))
                .collect(Collectors.toList());

        assertEquals(Arrays.asList(customer2, customer5, customer4, customer1, customer3), result);
    }

    @Test
    void sortedWithLambdaComparator() {
        Customer customer1 = new Customer("Bob", "Smith");
        Customer customer2 = new Customer("Alex", "Dabbel");
        Customer customer3 = new Customer("Gracie", "Hudson");
        Customer customer4 = new Customer("Alicia", "McDonald");
        Customer customer5 = new Customer("Alex", "Hanson");

        List<Customer> unsortedList = Arrays.asList(customer1, customer2, customer3, customer4, customer5);

        List<Customer> result = unsortedList.stream()
                .sorted((c1, c2) -> {
                    if (c1.getFirstName().equals(c2.getFirstName())) {
                        return c1.getLastName().compareTo(c2.getLastName());
                    } {
                        return c1.getFirstName().compareTo(c2.getFirstName());
                    }
                })
                .collect(Collectors.toList());

        assertEquals(Arrays.asList(customer2, customer5, customer4, customer1, customer3), result);
    }

    @Test
    void sortedWithNamedComparator() {
        Customer customer1 = new Customer("Bob");
        Customer customer2 = new Customer("Alex");
        Customer customer3 = new Customer("Gracie");
        Customer customer4 = new Customer("Alicia");

        List<Customer> unsortedList = Arrays.asList(customer1, customer2, customer3, customer4);

        List<Customer> result = unsortedList.stream()
                .sorted(new CustomerFirstNameComparator())
                .collect(Collectors.toList());

        assertEquals(Arrays.asList(customer2, customer4, customer1, customer3), result);
    }

    @Test
    void sortedWithNamedComparatorReversed() {
        Customer customer1 = new Customer("Bob");
        Customer customer2 = new Customer("Alex");
        Customer customer3 = new Customer("Gracie");
        Customer customer4 = new Customer("Alicia");

        List<Customer> unsortedList = Arrays.asList(customer1, customer2, customer3, customer4);

        List<Customer> result = unsortedList.stream()
                .sorted(new CustomerFirstNameComparator().reversed())
                .collect(Collectors.toList());;

        assertEquals(Arrays.asList(customer3, customer1, customer4, customer2), result);
    }

    private static class CustomerFirstNameComparator implements Comparator<Customer> {

        @Override
        public int compare(Customer customer1, Customer customer2) {
            return customer1.getFirstName().compareTo(customer2.getFirstName());
        }
    }


}