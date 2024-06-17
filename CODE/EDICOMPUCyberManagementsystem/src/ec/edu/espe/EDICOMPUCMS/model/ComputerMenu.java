
package ec.edu.espe.EDICOMPUCMS.model;


import java.util.Scanner;

public class ComputerMenu {
    private static CyberManager cyberManager = new CyberManager();

    public static void handleComputers() {
        Scanner scanner = new Scanner(System.in);
        CyberManager cyberManager = new CyberManager();

        while (true) {
            System.out.println("1. Start a computer");
            System.out.println("2. Stop a computer");
            System.out.println("3. Show computer status");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter computer ID to start:");
                    int startId = scanner.nextInt();
                    cyberManager.startComputer(startId);
                    break;
                case 2:
                    System.out.println("Enter computer ID to stop:");
                    int stopId = scanner.nextInt();
                    cyberManager.stopComputer(stopId);
                    break;
                case 3:
                    cyberManager.showComputerStatus();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
                 }
              }
        
        }
    }
