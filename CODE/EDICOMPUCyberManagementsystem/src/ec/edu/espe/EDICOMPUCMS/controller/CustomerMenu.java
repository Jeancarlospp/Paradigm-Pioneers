

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
            System.out.print("Selec an option: ");
            option = scanner.nextLine();

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
                    break;
                case "3":
                    customerManager.showCustomers();
                    break;
                case "4":
                    System.out.println("Exit...");
                    break;
                default:
                    System.out.println("Invalid Option");
                    break;
            }
        } while (!option.equals("4"));
    }
    

            
