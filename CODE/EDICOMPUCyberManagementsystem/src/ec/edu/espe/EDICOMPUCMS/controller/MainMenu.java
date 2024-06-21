package ec.edu.espe.EDICOMPUCMS.controller;

import ec.edu.espe.EDICOMPUCMS.model.Computer;
import ec.edu.espe.EDICOMPUCMS.model.Customer;
import ec.edu.espe.EDICOMPUCMS.model.GeneralReport;
import ec.edu.espe.EDICOMPUCMS.utils.JsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Customer> customers = new ArrayList<>();
    private static final List<Computer> computers = new ArrayList<>();
    private static GeneralReport generalReport = new GeneralReport();

    public static void showMainMenu() {
        // Cargar datos desde JSON
        loadInitialData();

        while (true) {
            clearScreen();
            System.out.println("\n========== Welcome to the system ==========");
            System.out.println("\tSelect the option you want to perform ");
            System.out.println("1. Customers");
            System.out.println("2. Computers");
            System.out.println("3. Computer rental");
            System.out.println("4. Computer reservation");
            System.out.println("5. General report");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");

            int option = getIntegerInput();

            clearScreen();
            switch (option) {
                case 1:
                    CustomerMenu customerMenu = new CustomerMenu(customers, computers, generalReport);
                    customerMenu.handleCustomers();
                    break;
                case 2:
                    ComputerMenu computerMenu = new ComputerMenu(computers, generalReport);
                    computerMenu.handleComputers();
                    break;
                case 3:
                    System.out.println("Computer rental");
                    // Implementación del alquiler de computadoras
                    break;
                case 4:
                    System.out.println("Computer reservation");
                    // Implementación de la reservación de computadoras
                    break;
                case 5:
                    System.out.println("General report");
                    System.out.println(generalReport);
                    break;
                case 6:
                    System.out.println("Exiting the system.");
                    saveDataToJson();
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
            System.out.println("\nPress Enter to continue...");
            waitForEnter();
        }
    }

    private static void loadInitialData() {
        // Cargar datos desde JSON
        List<Customer> loadedCustomers = JsonUtil.readCustomersFromJson();
        List<Computer> loadedComputers = JsonUtil.readComputersFromJson();
        GeneralReport loadedGeneralReport = JsonUtil.readGeneralReportFromJson();

        if (loadedCustomers != null) {
            customers.addAll(loadedCustomers);
        }

        if (loadedComputers != null) {
            computers.addAll(loadedComputers);
        }

        if (loadedGeneralReport != null) {
            generalReport = loadedGeneralReport;
        }
    }

    private static void saveDataToJson() {
        JsonUtil.saveCustomersToJson(customers);
        JsonUtil.saveComputersToJson(computers);
        JsonUtil.saveGeneralReportToJson(generalReport);
    }

    static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static int getIntegerInput() {
        int option = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                option = Integer.parseInt(scanner.nextLine());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
        return option;
    }

    private static void waitForEnter() {
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
