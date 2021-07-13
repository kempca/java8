package com.kempca.java8.samples;

import com.kempca.java8.model.Customer;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollectionSortingTest {

    @Test
    void sort() {
        List<Integer> integers = Arrays.asList(9, 5, 3, 8);

        integers.sort(Comparator.naturalOrder());

        assertEquals(Arrays.asList(3, 5, 8, 9), integers);
    }

    @Test
    void sortdWithCustomComparator() {
        Customer customer1 = new Customer("Bob", "Smith");
        Customer customer2 = new Customer("Alex", "Dabbel");
        Customer customer3 = new Customer("Gracie", "Hudson");
        Customer customer4 = new Customer("Alicia", "McDonald");
        Customer customer5 = new Customer("Alex", "Hanson");

        List<Customer> customers = Arrays.asList(customer1, customer2, customer3, customer4, customer5);

        customers.sort(Comparator.comparing(Customer::getFirstName).thenComparing(Customer::getLastName));

        assertEquals(Arrays.asList(customer2, customer5, customer4, customer1, customer3), customers);
    }

    @Test
    void sortWithNamedComparator() {
        Customer customer1 = new Customer("Bob");
        Customer customer2 = new Customer("Alex");
        Customer customer3 = new Customer("Gracie");
        Customer customer4 = new Customer("Alicia");

        List<Customer> customers = Arrays.asList(customer1, customer2, customer3, customer4);

        customers.sort(new CustomerFirstNameComparator());

        assertEquals(Arrays.asList(customer2, customer4, customer1, customer3), customers);
    }

    @Test
    void sortWithNamedComparatorReversed() {
        Customer customer1 = new Customer("Bob");
        Customer customer2 = new Customer("Alex");
        Customer customer3 = new Customer("Gracie");
        Customer customer4 = new Customer("Alicia");

        List<Customer> customers = Arrays.asList(customer1, customer2, customer3, customer4);

        customers.sort(new CustomerFirstNameComparator().reversed());

        assertEquals(Arrays.asList(customer3, customer1, customer4, customer2), customers);
    }

    private static class CustomerFirstNameComparator implements Comparator<Customer> {

        @Override
        public int compare(Customer customer1, Customer customer2) {
            return customer1.getFirstName().compareTo(customer2.getFirstName());
        }
    }

}
