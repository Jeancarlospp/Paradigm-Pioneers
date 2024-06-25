
package ec.edu.espe.EDICOMPUCMS.model;

import ec.edu.espe.EDICOMPUCMS.controller.CyberManager;
import ec.edu.espe.EDICOMPUCMS.controller.CyberManager;
import java.util.Scanner;


public class FinancialReportMenu {
     private static CyberManager cyberManager = new CyberManager();

    public static void handleFinancialReport() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n========== Financial Report Menu ==========");
            System.out.println("1. Show usage history");
            System.out.println("2. Exit to main menu");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    cyberManager.showHistory();
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println("\nPress Enter to continue...");
            try { System.in.read(); } catch (Exception e) {}
        }
    }
}
