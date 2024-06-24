package ec.edu.espe.EDICOMPUCMS.controller;



import java.util.Scanner;

public class MainMenu {
    public static void showMainMenu() {
        ComputerBackupManager.backupComputerStatuses();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            clearScreen();
            System.out.println("\n========== Welcome to the system ==========");
            System.out.println("\tSelect the option you want to perform ");
            System.out.println("1. Customers");
            System.out.println("2. Computer rental");
            System.out.println("3. Computer reservation");
            System.out.println("4. Financial report");
            System.out.println("5. Exit ");

            System.out.print("Select an option: ");

            int option = scanner.nextInt();

            clearScreen();
            switch (option) {
                case 1:
                    CustomerMenu.customerMenu();
                    // Call method or class for Customers
                    break;
                case 2:
                    ComputerMenu.handleComputers();
                    break;
                case 3:
                    System.out.println("Computer reservation");
                     // Call method or class for Computer reservation
                    break;
                case 4:
                    System.out.println("Financial report");
                    // Call method or class for Financial report
                    break;
                case 5:
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