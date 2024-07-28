/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ec.edu.espe.EDICOMPUCMS.controller;

import ec.edu.espe.EDICOMPUCMS.model.Customer;
import java.util.List;
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
public class CustomerManagerTest {
    
    public CustomerManagerTest() {
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
     * Test of addCustomer method, of class CustomerManager.
     */
    @Test
public void testAddCustomer() {
    CustomerManager instance = new CustomerManager();
    
    // Test 1: Add a valid customer
    Customer customer1 = new Customer("C001", "John Doe", "123 Main St", "555-1234", "john@example.com");
    instance.addCustomer(customer1);
    assertTrue(instance.getCustomers().stream().anyMatch(c -> c.getId().equals("C001")));
    
    // Test 2: Add another valid customer
    Customer customer2 = new Customer("C002", "Jane Smith", "456 Elm St", "555-5678", "jane@example.com");
    instance.addCustomer(customer2);
    assertTrue(instance.getCustomers().stream().anyMatch(c -> c.getId().equals("C002")));
    
    // Test 3: Add a customer with null ID (should throw exception)
    Customer customer3 = new Customer(null, "Invalid Customer", "789 Oak St", "555-9012", "invalid@example.com");
    assertThrows(IllegalArgumentException.class, () -> instance.addCustomer(customer3));
    
    // Test 4: Add a customer with empty ID (should throw exception)
    Customer customer4 = new Customer("", "Empty ID Customer", "321 Pine St", "555-3456", "empty@example.com");
    assertThrows(IllegalArgumentException.class, () -> instance.addCustomer(customer4));
    
    // Test 5: Add a customer with null name (should throw exception)
    Customer customer5 = new Customer("C005", null, "654 Birch St", "555-7890", "null@example.com");
    assertThrows(IllegalArgumentException.class, () -> instance.addCustomer(customer5));
    
    // Test 6: Add a customer with empty name
    Customer customer6 = new Customer("C006", "", "987 Cedar St", "555-2345", "empty@example.com");
    instance.addCustomer(customer6);
    assertTrue(instance.getCustomers().stream().anyMatch(c -> c.getId().equals("C006")));
    
    // Test 7: Add a duplicate customer (should throw exception)
    Customer customer7 = new Customer("C001", "Duplicate John Doe", "111 Duplicate St", "555-1111", "duplicate@example.com");
    assertThrows(IllegalArgumentException.class, () -> instance.addCustomer(customer7));
    
    // Test 8: Add a customer with very long values
    Customer customer8 = new Customer("C008", "A".repeat(1000), "B".repeat(1000), "C".repeat(1000), "D".repeat(1000));
    instance.addCustomer(customer8);
    assertTrue(instance.getCustomers().stream().anyMatch(c -> c.getId().equals("C008")));
    
    // Test 9: Add a customer with special characters
    Customer customer9 = new Customer("C009", "John O'Doe", "123 Main St.", "555-CALL", "john+doe@example.com");
    instance.addCustomer(customer9);
    assertTrue(instance.getCustomers().stream().anyMatch(c -> c.getId().equals("C009")));
    
    // Test 10: Add a null customer (should throw exception)
    assertThrows(NullPointerException.class, () -> instance.addCustomer(null));
}

    /**
     * Test of removeCustomer method, of class CustomerManager.
     */
    @Test
public void testRemoveCustomer() {
    CustomerManager instance = new CustomerManager();
    
    // Setup: Add some customers
    instance.addCustomer(new Customer("C001", "John Doe", "123 Main St", "555-1234", "john@example.com"));
    instance.addCustomer(new Customer("C002", "Jane Smith", "456 Elm St", "555-5678", "jane@example.com"));
    
    // Test 1: Remove an existing customer
    instance.removeCustomer("C001");
    assertFalse(instance.getCustomers().stream().anyMatch(c -> c.getId().equals("C001")));
    
    // Test 2: Remove a non-existent customer
    instance.removeCustomer("C999");
    assertEquals(1, instance.getCustomers().size());
    
    // Test 3: Remove with null ID (should throw exception)
    assertThrows(IllegalArgumentException.class, () -> instance.removeCustomer(null));
    
    // Test 4: Remove with empty ID
    instance.removeCustomer("");
    assertEquals(1, instance.getCustomers().size());
    
    // Test 5: Remove the last customer
    instance.removeCustomer("C002");
    assertTrue(instance.getCustomers().isEmpty());
    
    // Test 6: Remove from an empty list
    instance.removeCustomer("C003");
    assertTrue(instance.getCustomers().isEmpty());
    
    // Test 7: Remove with very long ID
    instance.addCustomer(new Customer("C" + "0".repeat(1000), "Long ID", "Address", "Phone", "email@example.com"));
    instance.removeCustomer("C" + "0".repeat(1000));
    assertTrue(instance.getCustomers().isEmpty());
    
    // Test 8: Remove multiple times
    instance.addCustomer(new Customer("C003", "Test", "Address", "Phone", "email@example.com"));
    instance.removeCustomer("C003");
    instance.removeCustomer("C003");
    assertTrue(instance.getCustomers().isEmpty());
    
    // Test 9: Remove with special characters in ID
    instance.addCustomer(new Customer("C#004", "Special", "Address", "Phone", "email@example.com"));
    instance.removeCustomer("C#004");
    assertTrue(instance.getCustomers().isEmpty());
    
    // Test 10: Attempt to remove all customers (if such method exists)
    instance.addCustomer(new Customer("C005", "Test1", "Address1", "Phone1", "email1@example.com"));
    instance.addCustomer(new Customer("C006", "Test2", "Address2", "Phone2", "email2@example.com"));
    instance.removeCustomer("C005");
    instance.removeCustomer("C006");
    assertTrue(instance.getCustomers().isEmpty());
}

    /**
     * Test of getCustomers method, of class CustomerManager.
     */
    @Test
public void testGetCustomers() {
    CustomerManager instance = new CustomerManager();
    
    // Test 1: Get customers from empty list
    assertTrue(instance.getCustomers().isEmpty());
    
    // Test 2: Get customers after adding one
    instance.addCustomer(new Customer("C001", "John Doe", "123 Main St", "555-1234", "john@example.com"));
    assertEquals(1, instance.getCustomers().size());
    
    // Test 3: Get customers after adding multiple
    instance.addCustomer(new Customer("C002", "Jane Smith", "456 Elm St", "555-5678", "jane@example.com"));
    instance.addCustomer(new Customer("C003", "Bob Johnson", "789 Oak St", "555-9012", "bob@example.com"));
    assertEquals(3, instance.getCustomers().size());
    
    // Test 4: Verify customer details
    Customer retrievedCustomer = instance.getCustomers().stream().filter(c -> c.getId().equals("C001")).findFirst().orElse(null);
    assertNotNull(retrievedCustomer);
    assertEquals("John Doe", retrievedCustomer.getName());
    
    // Test 5: Get customers after removing one
    instance.removeCustomer("C002");
    assertEquals(2, instance.getCustomers().size());
    
    // Test 6: Verify list order (if order is maintained)
    List<Customer> customers = instance.getCustomers();
    assertEquals("C001", customers.get(0).getId());
    assertEquals("C003", customers.get(1).getId());
    
    // Test 7: Get customers after updating one
    Customer updatedCustomer = new Customer("C001", "John Updated", "New Address", "New Phone", "new@example.com");
    instance.updateCustomer(updatedCustomer);
    retrievedCustomer = instance.getCustomers().stream().filter(c -> c.getId().equals("C001")).findFirst().orElse(null);
    assertNotNull(retrievedCustomer);
    assertEquals("John Updated", retrievedCustomer.getName());
    
    // Test 8: Get customers after adding customer with very long values
    instance.addCustomer(new Customer("C004", "A".repeat(1000), "B".repeat(1000), "C".repeat(1000), "D".repeat(1000)));
    assertEquals(3, instance.getCustomers().size());
    
    // Test 9: Get customers after adding and removing multiple times
    for (int i = 5; i <= 10; i++) {
        instance.addCustomer(new Customer("C00" + i, "Name" + i, "Address" + i, "Phone" + i, "email" + i + "@example.com"));
    }
    for (int i = 5; i <= 7; i++) {
        instance.removeCustomer("C00" + i);
    }
    assertEquals(6, instance.getCustomers().size());
    
    // Test 10: Verify that getCustomers returns a new list (not a reference to internal list)
    List<Customer> customerList1 = instance.getCustomers();
    List<Customer> customerList2 = instance.getCustomers();
    assertNotSame(customerList1, customerList2);
}

    /**
     * Test of updateCustomer method, of class CustomerManager.
     */
    @Test
public void testUpdateCustomer() {
    CustomerManager instance = new CustomerManager();
    
    // Setup: Add a customer
    Customer originalCustomer = new Customer("C001", "John Doe", "123 Main St", "555-1234", "john@example.com");
    instance.addCustomer(originalCustomer);
    
    // Test 1: Update an existing customer
    Customer updatedCustomer = new Customer("C001", "John Updated", "456 Elm St", "555-5678", "john.updated@example.com");
    instance.updateCustomer(updatedCustomer);
    Customer retrievedCustomer = instance.getCustomers().stream().filter(c -> c.getId().equals("C001")).findFirst().orElse(null);
    assertNotNull(retrievedCustomer);
    assertEquals("John Updated", retrievedCustomer.getName());
    assertEquals("456 Elm St", retrievedCustomer.getAddress());
    
    // Test 2: Update a non-existent customer (should add new customer)
    Customer newCustomer = new Customer("C002", "Jane Smith", "789 Oak St", "555-9012", "jane@example.com");
    instance.updateCustomer(newCustomer);
    assertEquals(2, instance.getCustomers().size());
    
    // Test 3: Update with null customer (should throw exception)
    assertThrows(NullPointerException.class, () -> instance.updateCustomer(null));
    
    // Test 4: Update with null ID (should throw exception)
    Customer nullIdCustomer = new Customer(null, "Invalid", "Address", "Phone", "email@example.com");
    assertThrows(IllegalArgumentException.class, () -> instance.updateCustomer(nullIdCustomer));
    
    // Test 5: Update with empty ID
    Customer emptyIdCustomer = new Customer("", "Empty ID", "Address", "Phone", "email@example.com");
    instance.updateCustomer(emptyIdCustomer);
    assertEquals(3, instance.getCustomers().size());
    
    // Test 6: Update with null name
    Customer nullNameCustomer = new Customer("C001", null, "Address", "Phone", "email@example.com");
    instance.updateCustomer(nullNameCustomer);
    retrievedCustomer = instance.getCustomers().stream().filter(c -> c.getId().equals("C001")).findFirst().orElse(null);
    assertNotNull(retrievedCustomer);
    assertNull(retrievedCustomer.getName());
    
    // Test 7: Update with very long values
    Customer longValuesCustomer = new Customer("C001", "A".repeat(1000), "B".repeat(1000), "C".repeat(1000), "D".repeat(1000));
    instance.updateCustomer(longValuesCustomer);
    retrievedCustomer = instance.getCustomers().stream().filter(c -> c.getId().equals("C001")).findFirst().orElse(null);
    assertNotNull(retrievedCustomer);
    assertEquals(1000, retrievedCustomer.getName().length());
    
    // Test 8: Update multiple times
    for (int i = 1; i <= 10; i++) {
        instance.updateCustomer(new Customer("C001", "Name" + i, "Address" + i, "Phone" + i, "email" + i + "@example.com"));
    }
    retrievedCustomer = instance.getCustomers().stream().filter(c -> c.getId().equals("C001")).findFirst().orElse(null);
    assertNotNull(retrievedCustomer);
    assertEquals("Name10", retrievedCustomer.getName());
    
    // Test 9: Update with special characters
    Customer specialCharsCustomer = new Customer("C001", "John O'Doe", "123 Main St.", "555-CALL", "john+doe@example.com");
    instance.updateCustomer(specialCharsCustomer);
    retrievedCustomer = instance.getCustomers().stream().filter(c -> c.getId().equals("C001")).findFirst().orElse(null);
    assertNotNull(retrievedCustomer);
    assertEquals("John O'Doe", retrievedCustomer.getName());
    
    // Test 10: Update after removing
    instance.removeCustomer("C001");
    instance.updateCustomer(new Customer("C001", "John Revived", "Address", "Phone", "email@example.com"));
    assertEquals(3, instance.getCustomers().size());
    retrievedCustomer = instance.getCustomers().stream().filter(c -> c.getId().equals("C001")).findFirst().orElse(null);
    assertNotNull(retrievedCustomer);
    assertEquals("John Revived", retrievedCustomer.getName());
}
    
}
