package ec.edu.espe.EDICOMPUCMS.controller;

<<<<<<< HEAD
import ec.edu.espe.EDICOMPUCMS.model.Computer;
import ec.edu.espe.EDICOMPUCMS.model.GeneralReport;
import ec.edu.espe.EDICOMPUCMS.utils.JsonUtil;
import java.util.List;
=======

>>>>>>> c5beda7e217edd8fffcf33a829bc5d067edfd558
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
            System.out.println("1. Start a computer");
            System.out.println("2. Stop a computer");
            System.out.println("3. Show computer status");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    startComputer();
                    break;
                case 2:
                    stopComputer();
                    break;
                case 3:
                    showComputerStatus();
                    break;
                case 4:
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

    private void startComputer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter computer ID to start: ");
        int id = scanner.nextInt();
        for (Computer computer : computers) {
            if (computer.getId() == id) {
                computer.setActive(true);
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
                computer.setActive(false);
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