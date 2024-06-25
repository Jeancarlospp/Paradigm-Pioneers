package ec.edu.espe.EDICOMPUCMS.controller;

import ec.edu.espe.EDICOMPUCMS.model.ComputerMenu;
import ec.edu.espe.EDICOMPUCMS.model.CustomerMenu;
import ec.edu.espe.EDICOMPUCMS.model.RentalMenu;
import java.io.IOException;
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
            System.out.println("2. Cyber Management");
            System.out.println("3. Computer rental");
            System.out.println("4. Exit ");

            System.out.print("Select an option: ");

            int option = scanner.nextInt();

            clearScreen();
            switch (option) {
                case 1:
                    CustomerMenu.customerMenu();
                    break;
                case 2:
                    ComputerMenu.handleComputers();
                    break;
                case 3:
                    System.out.println("Computer rental");
                     RentalMenu.showRentalMenu();
                    break;
                case 4:
                    System.out.println("Exiting the system.");
             
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
            System.out.println("\nPress Enter to continue...");
            try { System.in.read(); } catch (IOException e) {}
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}