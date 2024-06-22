package ec.edu.espe.EDICOMPUCMS.controller;

import ec.edu.espe.EDICOMPUCMS.model.Customer;
import java.util.Scanner;

public class CustomerMenu {
    public static void customerMenu(){
        CustomerManager customerManager = new CustomerManager();
        Scanner scanner = new Scanner(System.in);
        String option;

        do {
            System.out.println("1. Add Customer");
            System.out.println("2. Remove Customer");
            System.out.println("3. Show Customers");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            option = scanner.nextLine();

            switch (option) {
                case "1":
                    String id;
                    do {
                        System.out.print("ID (10 digits): ");
                        id = scanner.nextLine();
                        if (!isValidId(id)) {
                            System.out.println("Invalid ID. Please enter a 10-digit ID.");
                        }
                    } while (!isValidId(id));

                    System.out.print("Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Address: ");
                    String address = scanner.nextLine();

                    String phone;
                    do {
                        System.out.print("Phone (10 digits): ");
                        phone = scanner.nextLine();
                        if (!isValidPhone(phone)) {
                            System.out.println("Invalid phone number. Please enter a 10-digit phone number.");
                        }
                    } while (!isValidPhone(phone));

                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    Customer customer = new Customer(id, name, address, phone, email);
                    customerManager.addCustomer(customer);
                    break;

                case "2":
                    System.out.print("Client ID to be removed: ");
                    String removeId = scanner.nextLine();
                    customerManager.removeCustomer(removeId);
                    break;

                case "3":
                    customerManager.showCustomers();
                    break;

                case "4":
                    System.out.println("Exit...");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        } while (!option.equals("4"));
    }

    private static boolean isValidId(String id) {
        return id.matches("\\d{10}");
    }

    private static boolean isValidPhone(String phone) {
        return phone.matches("\\d{10}");
    }
}
