/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ec.edu.espe.EDICOMPUCMS.controller;

import ec.edu.espe.EDICOMPUCMS.model.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author KENNED
 */
public class RentalManagerTest {
    
    public RentalManagerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addCustomer method, of class RentalManager.
     */
    @Test
public void testAddCustomer() {
    RentalManager instance = new RentalManager();
    
    // Test 1: Add a valid customer
    Customer customer1 = new Customer("C001", "John Doe", "123 Main St", "555-1234", "john@example.com");
    instance.addCustomer(customer1);
    
    // Test 2: Add another valid customer
    Customer customer2 = new Customer("C002", "Jane Smith", "456 Elm St", "555-5678", "jane@example.com");
    instance.addCustomer(customer2);
    
    // Test 3: Add a customer with null ID
    Customer customer3 = new Customer(null, "Invalid Customer", "789 Oak St", "555-9012", "invalid@example.com");
    instance.addCustomer(customer3);
    
    // Test 4: Add a customer with empty ID
    Customer customer4 = new Customer("", "Empty ID Customer", "321 Pine St", "555-3456", "empty@example.com");
    instance.addCustomer(customer4);
    
    // Test 5: Add a customer with null name
    Customer customer5 = new Customer("C005", null, "654 Birch St", "555-7890", "null@example.com");
    instance.addCustomer(customer5);
    
    // Test 6: Add a customer with empty name
    Customer customer6 = new Customer("C006", "", "987 Cedar St", "555-2345", "empty@example.com");
    instance.addCustomer(customer6);
    
    // Test 7: Add a duplicate customer
    Customer customer7 = new Customer("C001", "Duplicate John Doe", "111 Duplicate St", "555-1111", "duplicate@example.com");
    instance.addCustomer(customer7);
    
    // Test 8: Add a large number of customers
    for (int i = 0; i < 1000; i++) {
        instance.addCustomer(new Customer("C" + (1000 + i), "Customer " + i, "Address " + i, "555-" + i, "customer" + i + "@example.com"));
    }
    
    // Test 9: Add a customer with very long ID and name
    Customer customer9 = new Customer("C" + "0".repeat(1000), "A".repeat(1000), "Long Address", "555-LONG", "long@example.com");
    instance.addCustomer(customer9);
    
    // Test 10: Add a null customer
    instance.addCustomer(null);
}

    /**
     * Test of startRental method, of class RentalManager.
     */
    @Test
public void testStartRental() {
    RentalManager instance = new RentalManager();
    
    // Add a test customer
    Customer testCustomer = new Customer("C001", "Test Customer", "Test Address", "555-TEST", "test@example.com");
    instance.addCustomer(testCustomer);
    
    // Test 1: Start a valid rental
    instance.startRental("C001", 1);
    
    // Test 2: Try to rent an already rented computer
    instance.startRental("C001", 1);
    
    // Test 3: Try to rent with an invalid customer ID
    instance.startRental("C999", 2);
    
    // Test 4: Try to rent an invalid computer ID
    instance.startRental("C001", 100);
    
    // Test 5: Try to rent with null customer ID
    instance.startRental(null, 3);
    
    // Test 6: Try to rent with empty customer ID
    instance.startRental("", 4);
    
    // Test 7: Try to rent with negative computer ID
    instance.startRental("C001", -1);
    
    // Test 8: Rent multiple computers
    for (int i = 2; i <= 10; i++) {
        instance.startRental("C001", i);
    }
    
    // Test 9: Try to rent when all computers are occupied
    instance.startRental("C001", 1);
    
    // Test 10: Try to rent with very long customer ID
    instance.startRental("C" + "0".repeat(1000), 1);
}

    /**
     * Test of endRental method, of class RentalManager.
     */
    @Test
public void testEndRental() {
    RentalManager instance = new RentalManager();
    
    // Add a test customer and start some rentals
    Customer testCustomer = new Customer("C001", "Test Customer", "Test Address", "555-TEST", "test@example.com");
    instance.addCustomer(testCustomer);
    instance.startRental("C001", 1);
    instance.startRental("C001", 2);
    
    // Test 1: End a valid rental
    instance.endRental(1);
    
    // Test 2: Try to end an already ended rental
    instance.endRental(1);
    
    // Test 3: Try to end a non-existent rental
    instance.endRental(100);
    
    // Test 4: End multiple rentals
    instance.startRental("C001", 3);
    instance.startRental("C001", 4);
    instance.endRental(2);
    instance.endRental(3);
    instance.endRental(4);
    
    // Test 5: Try to end a rental with negative ID
    instance.endRental(-1);
    
    // Test 6: Try to end a rental with zero ID
    instance.endRental(0);
    
    // Test 7: End a rental immediately after starting
    instance.startRental("C001", 5);
    instance.endRental(5);
    
    // Test 8: Start and end multiple rentals in sequence
    for (int i = 6; i <= 10; i++) {
        instance.startRental("C001", i);
        instance.endRental(i);
    }
    
    // Test 9: Try to end all rentals, including non-existent ones
    for (int i = 1; i <= 20; i++) {
        instance.endRental(i);
    }
    
    // Test 10: Try to end a rental with a very large ID
    instance.endRental(Integer.MAX_VALUE);
}

    /**
     * Test of showRentals method, of class RentalManager.
     */
    @Test
public void testShowRentals() {
    RentalManager instance = new RentalManager();
    
    // Test 1: Show rentals when there are no rentals
    instance.showRentals();
    
    // Add a test customer and start some rentals
    Customer testCustomer = new Customer("C001", "Test Customer", "Test Address", "555-TEST", "test@example.com");
    instance.addCustomer(testCustomer);
    
    // Test 2: Show rentals with one active rental
    instance.startRental("C001", 1);
    instance.showRentals();
    
    // Test 3: Show rentals with multiple active rentals
    instance.startRental("C001", 2);
    instance.startRental("C001", 3);
    instance.showRentals();
    
    // Test 4: Show rentals after ending a rental
    instance.endRental(2);
    instance.showRentals();
    
    // Test 5: Show rentals with a mix of active and ended rentals
    instance.startRental("C001", 4);
    instance.endRental(1);
    instance.showRentals();
    
    // Test 6: Show rentals after ending all rentals
    instance.endRental(3);
    instance.endRental(4);
    instance.showRentals();
    
    // Test 7: Show rentals after starting and immediately ending rentals
    instance.startRental("C001", 5);
    instance.endRental(5);
    instance.showRentals();
    
    // Test 8: Show rentals with a large number of rentals
    for (int i = 1; i <= 100; i++) {
        instance.startRental("C001", i % 10 + 1);
        if (i % 2 == 0) {
            instance.endRental(i);
        }
    }
    instance.showRentals();
    
    // Test 9: Show rentals after clearing all rentals (if such method exists)
    // Assume there's a clearRentals() method
    // instance.clearRentals();
    instance.showRentals();
    
    // Test 10: Show rentals after adding a rental with very long customer name
    Customer longNameCustomer = new Customer("C002", "A".repeat(1000), "Long Name Address", "555-LONG", "longname@example.com");
    instance.addCustomer(longNameCustomer);
    instance.startRental("C002", 1);
    instance.showRentals();
}
    
}
