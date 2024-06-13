
package ec.edu.espe.EDICOMPUCMS.model;

import java.util.Scanner;


import java.util.Scanner;

public class MainMenu {
    public static void showMainMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            clearScreen();
            System.out.println("\n\n\t\tWelcome to the system");
            System.out.println("\t\tSelect the option you want to perform: ");
            System.out.println("\t\t1. Customers");
            System.out.println("\t\t2. Computers");
            System.out.println("\t\t3. Computer rental");
            System.out.println("\t\t4. Computer reservation");
            System.out.println("\t\t5. Financial report");
            System.out.println("\t\t6. Exit");
            System.out.print("\t\tSelect an option: ");

            int option = scanner.nextInt();

            clearScreen();
            switch (option) {
                case 1:
                    System.out.println("Customers");
                    // Call method or class for Customers
                    break;
                case 2:
                    ComputerMenu.handleComputers();
                    break;
                case 3:
                    System.out.println("Computer rental");
                    // Call method or class for Computer rental
                    break;
                case 4:
                    System.out.println("Computer reservation");
                    // Call method or class for Computer reservation
                    break;
                case 5:
                    System.out.println("Financial report");
                    // Call method or class for Financial report
                    break;
                case 6:
                    System.out.println("Exiting the system.");
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
            System.out.println("\nPress Enter to continue...");
            try { System.in.read(); } catch (Exception e) {}
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}