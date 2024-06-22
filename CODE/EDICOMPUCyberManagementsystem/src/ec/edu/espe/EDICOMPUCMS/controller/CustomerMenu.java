<<<<<<< HEAD
package ec.edu.espe.EDICOMPUCMS.controller;
/**
 *
 * @author Andrea Raura,Paradigm Pioneers Squad, DCCO-ESPE
 */
import ec.edu.espe.EDICOMPUCMS.model.Computer;
import ec.edu.espe.EDICOMPUCMS.model.Customer;
import ec.edu.espe.EDICOMPUCMS.model.GeneralReport;
import ec.edu.espe.EDICOMPUCMS.utils.JsonUtil;

import java.util.List;
import java.util.Scanner;

public class CustomerMenu {
    private List<Customer> customers;
    private final List<Computer> computers;
    private final GeneralReport generalReport;
    private final Scanner scanner = new Scanner(System.in);

    public CustomerMenu(List<Customer> customers, List<Computer> computers, GeneralReport generalReport) {
        this.customers = customers;
        this.computers = computers;
        this.generalReport = generalReport;
        this.customers = JsonUtil.readCustomersFromJson();
    }

    public void handleCustomers() {
        while (true) {
            clearScreen();
            System.out.println("\n========== Customer Menu ==========");
=======


package ec.edu.espe.EDICOMPUCMS.controller;

import ec.edu.espe.EDICOMPUCMS.model.Customer;
import java.util.Scanner;


public class CustomerMenu {
    public static void customerMenu(){
        CustomerManager customerManager = new CustomerManager();
        Scanner scanner = new Scanner(System.in);
        String option;

        do {

>>>>>>> c5beda7e217edd8fffcf33a829bc5d067edfd558
            System.out.println("1. Add Customer");
            System.out.println("2. Remove Customer");
            System.out.println("3. Show Customers");
            System.out.println("4. Exit");
            System.out.print("Selec an option: ");
            option = scanner.nextLine();

<<<<<<< HEAD
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline character left by nextInt()

            clearScreen();
            switch (option) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    removeCustomer();
=======
            switch (option) {
                case "1":
                    System.out.print("ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Address: ");
                    String address = scanner.nextLine();
                    System.out.print("Phone: ");
                    int phone = Integer.parseInt(scanner.nextLine());
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    Customer customer = new Customer(id, name, address, phone, email);
                    customerManager.addCustomer(customer);
                    break;
                case "2":
                    System.out.print("Client ID to be removed ");
                    String removeId = scanner.nextLine();
                    customerManager.removeCustomer(removeId);
>>>>>>> c5beda7e217edd8fffcf33a829bc5d067edfd558
                    break;
                case "3":
                    customerManager.showCustomers();
                    break;
                case "4":
                    System.out.println("Exit...");
                    break;
<<<<<<< HEAD
                case 4:
                    System.out.println("Exiting Customer Menu.");
                    // Save customers to JSON when exiting
                    JsonUtil.saveCustomersToJson(customers);
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
            System.out.println("\nPress Enter to continue...");
            try {
                System.in.read();
            } catch (Exception e) {
                e.printStackTrace();
=======
                default:
                    System.out.println("Invalid Option");
                    break;
>>>>>>> c5beda7e217edd8fffcf33a829bc5d067edfd558
            }
        } while (!option.equals("4"));
    }
}

<<<<<<< HEAD
    private void addCustomer() {
        System.out.print("Enter customer ID: ");
        String customerId = scanner.nextLine();

        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();

        System.out.print("Enter customer address: ");
        String address = scanner.nextLine();

        int phone = 0;
        boolean validPhone = false;
        while (!validPhone) {
            try {
                System.out.print("Enter customer phone number: ");
                phone = Integer.parseInt(scanner.nextLine());
                validPhone = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid phone number format. Please enter a valid number.");
            }
        }

        System.out.print("Enter customer email: ");
        String email = scanner.nextLine();

        Customer customer = new Customer(customerId, name, address, phone, email);
        customers.add(customer);

        // Save customers to JSON after adding
        JsonUtil.saveCustomersToJson(customers);

        System.out.println("Customer added successfully!");
    }

    private void removeCustomer() {
        System.out.print("Enter customer ID to remove: ");
        String customerId = scanner.nextLine();

        boolean removed = customers.removeIf(customer -> customer.getId().equals(customerId));

        if (removed) {
            // Save customers to JSON after removing
            JsonUtil.saveCustomersToJson(customers);
            System.out.println("Customer removed successfully!");
        } else {
            System.out.println("Customer not found with ID " + customerId);
        }
    }

    private void showCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers registered yet.");
        } else {
            System.out.println("========== Customer List ==========");
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        }
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
=======
            
>>>>>>> c5beda7e217edd8fffcf33a829bc5d067edfd558
