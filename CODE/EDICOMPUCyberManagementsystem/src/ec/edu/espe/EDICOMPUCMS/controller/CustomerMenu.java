/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.EDICOMPUCMS.controller;

import com.google.gson.reflect.TypeToken;
import ec.edu.espe.EDICOMPUCMS.model.Customer;
import ec.edu.espe.EDICOMPUCMS.utils.JsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Sjean
 */

public class CustomerMenu {
    private List<Customer> customers;
    private static final String FILE_PATH = "customers.json";

    public CustomerMenu() {
        customers = new ArrayList<>();
        loadCustomers();  // Cargar los clientes al inicializar el menú
    }

    public void handleCustomers() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n========== Customer Management Menu ==========");
            System.out.println("1. Add Customer");
            System.out.println("2. Remove Customer");
            System.out.println("3. Show Customers");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addCustomer(scanner);
                    break;
                case 2:
                    removeCustomer(scanner);
                    break;
                case 3:
                    showCustomers();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    saveCustomers();  // Guardar los clientes antes de salir
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addCustomer(Scanner scanner) {
        System.out.print("Enter customer ID: ");
        String id = scanner.next();
        System.out.print("Enter customer name: ");
        String name = scanner.next();
        System.out.print("Enter customer last name: ");
        String lastName = scanner.next();
        System.out.print("Enter customer address: ");
        String address = scanner.next();
        System.out.print("Enter customer phone: ");
        String phone = scanner.next();
        System.out.print("Enter customer email: ");
        String email = scanner.next();

        Customer customer = new Customer(id, name, lastName, address, phone, email);
        customers.add(customer);
        saveCustomers();  // Guardar el cliente después de agregarlo
        System.out.println("Customer added successfully.");
    }

    private void removeCustomer(Scanner scanner) {
        System.out.print("Enter customer ID to remove: ");
        String id = scanner.next();
        customers.removeIf(customer -> customer.getId().equals(id));
        saveCustomers();  // Guardar los clientes después de eliminar uno
        System.out.println("Customer removed successfully.");
    }

    private void showCustomers() {
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    private void saveCustomers() {
        JsonUtil.saveToJson(FILE_PATH, customers);
    }

    private void loadCustomers() {
        List<Customer> loadedCustomers = JsonUtil.readFromJson(FILE_PATH, new TypeToken<List<Customer>>() {}.getType());
        if (loadedCustomers != null) {
            customers.addAll(loadedCustomers);
        }
    }
}