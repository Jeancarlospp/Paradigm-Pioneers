package ec.edu.espe.EDICOMPUCMS.controller;

import ec.edu.espe.EDICOMPUCMS.model.Computer;
import ec.edu.espe.EDICOMPUCMS.model.GeneralReport;
import ec.edu.espe.EDICOMPUCMS.utils.JsonUtil;
import java.util.List;
import java.util.Scanner;

public class ComputerMenu {
    private final List<Computer> computers;
    private final GeneralReport generalReport;

    public ComputerMenu(List<Computer> computers, GeneralReport generalReport) {
        this.computers = computers;
        this.generalReport = generalReport;
    }

    public void handleComputers() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            MainMenu.clearScreen();
            System.out.println("\n========== Computer Management Menu ==========");
            System.out.println("1. Add Computer");
            System.out.println("2. Start a computer");
            System.out.println("3. Stop a computer");
            System.out.println("4. Show computer status");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    addComputer();
                    break;
                case 2:
                    startComputer();
                    break;
                case 3:
                    stopComputer();
                    break;
                case 4:
                    showComputerStatus();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
            System.out.println("\nPress Enter to continue...");
            try {
                System.in.read();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void addComputer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter computer ID: ");
        int id = scanner.nextInt();
        Computer computer = new Computer(id);
        computers.add(computer);
        JsonUtil.addComputerToJson(computer);
        System.out.println("Computer added successfully!");
    }

    private void startComputer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter computer ID to start: ");
        int id = scanner.nextInt();
        for (Computer computer : computers) {
            if (computer.getId() == id) {
                computer.start();
                JsonUtil.saveComputersToJson(computers); // Save the updated list to JSON
                System.out.println("Computer " + id + " is now active.\n");
                return;
            }
        }
        System.out.println("Computer not found.\n");
    }

    private void stopComputer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter computer ID to stop: ");
        int id = scanner.nextInt();
        for (Computer computer : computers) {
            if (computer.getId() == id) {
                computer.stop();
                JsonUtil.saveComputersToJson(computers); // Save the updated list to JSON
                System.out.println("Computer " + id + " is now inactive.\n");
                return;
            }
        }
        System.out.println("Computer not found.\n");
    }

    private void showComputerStatus() {
        System.out.println("\n========== Computer Status ==========");
        for (Computer computer : computers) {
            System.out.println(computer);
        }
    }
}