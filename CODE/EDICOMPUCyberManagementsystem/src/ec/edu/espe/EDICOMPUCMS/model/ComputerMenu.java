
package ec.edu.espe.EDICOMPUCMS.model;


import java.util.Scanner;

public class ComputerMenu {
    private static ComputerManager computerManager = new ComputerManager(6);

    public static void handleComputers() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            MainMenu.clearScreen();
            System.out.println("\t\tComputers Menu");
            System.out.println("\t\t1. Show all computers");
            System.out.println("\t\t2. Activate a computer");
            System.out.println("\t\t3. Deactivate a computer");
            System.out.println("\t\t4. Return to main menu");
            System.out.print("\t\tSelect an option: ");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    MainMenu.clearScreen();
                    computerManager.showComputerStatus();
                    break;
                case 2:
                    System.out.print("Enter computer ID to activate: ");
                    int activateId = scanner.nextInt();
                    computerManager.activateComputer(activateId);
                    break;
                case 3:
                    System.out.print("Enter computer ID to deactivate: ");
                    int deactivateId = scanner.nextInt();
                    computerManager.deactivateComputer(deactivateId);
                    break;
                case 4:
                    return; // Return to main menu
                default:
                    System.out.println("Invalid option. Please try again.");
            }
            System.out.println("\nPress Enter to continue...");
            try { System.in.read(); } catch (Exception e) {}
        }
    }
}