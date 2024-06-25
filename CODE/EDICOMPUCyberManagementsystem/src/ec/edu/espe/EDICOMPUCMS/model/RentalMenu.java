package ec.edu.espe.EDICOMPUCMS.model;

import ec.edu.espe.EDICOMPUCMS.controller.CustomerManager;
import ec.edu.espe.EDICOMPUCMS.controller.RentalManager;

import java.util.List;
import java.util.Scanner;

public class RentalMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final RentalManager rentalManager = new RentalManager();
    private static final CustomerManager customerManager = new CustomerManager();

    public static void showRentalMenu() {
        int option;
        do {
            System.out.println("\n=== Rental Management System ===");
            System.out.println("1. Add Existing Customer");
            System.out.println("2. Start Computer Rental");
            System.out.println("3. End Computer Rental");
            System.out.println("4. Show Rentals");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    addExistingCustomer();
                    break;
                case 2:
                    startRental();
                    break;
                case 3:
                    endRental();
                    break;
                case 4:
                    rentalManager.showRentals();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (option != 5);
    }

    private static void addExistingCustomer() {
        List<Customer> customers = customerManager.getCustomers();
        if (!customers.isEmpty()) {
            System.out.println("Select a customer to add:");
            for (int i = 0; i < customers.size(); i++) {
                System.out.println((i + 1) + ". " + customers.get(i));
            }
            int customerIndex = scanner.nextInt() - 1;
            if (customerIndex >= 0 && customerIndex < customers.size()) {
                Customer customer = customers.get(customerIndex);
                rentalManager.addCustomer(customer);
                System.out.println("Customer added successfully!");
            } else {
                System.out.println("Invalid selection.");
            }
        } else {
            System.out.println("No customers found in the JSON file.");
        }
    }

    private static void startRental() {
        System.out.print("Enter customer ID: ");
        String customerID = scanner.next();
        System.out.print("Enter computer ID (1-10): ");
        int computerID = scanner.nextInt();

        rentalManager.startRental(customerID, computerID);
    }

    private static void endRental() {
        System.out.print("Enter rental ID: ");
        int rentalID = scanner.nextInt();
        rentalManager.endRental(rentalID);
    }
}
