package ec.edu.espe.EDICOMPUCMS.controller;

import ec.edu.espe.EDICOMPUCMS.model.Computer;
import ec.edu.espe.EDICOMPUCMS.model.History;
import ec.edu.espe.EDICOMPUCMS.model.Tariff;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CyberManager {
    private List<Computer> computers;
    private Tariff tariff;
    private List<History> history;

    public CyberManager() {
        tariff = new Tariff(1.00, 0.15);  // 1 dólar por hora, tarifa mínima 15 centavos
        computers = new ArrayList<>();
        history = new ArrayList<>();
        
        for (int i = 1; i <= 10; i++) {
            computers.add(new Computer(i, tariff));
        }
    }

    public void startComputer(int id) {
        for (Computer computer : computers) {
            if (computer.getId() == id && !computer.isActive()) {
                computer.start();
                System.out.println("Computer " + id + " is now active.");
                return;
            }
        }
        System.out.println("Computer " + id + " is already active or does not exist.");
    }

    public void stopComputer(int id) {
        for (Computer computer : computers) {
            if (computer.getId() == id && computer.isActive()) {
                computer.stop();
                System.out.println("Computer " + id + " has been stopped.");
                
                // Registrar el historial de uso
                double cost = computer.calculateCost();
                History entry = new History(computer.getId(), computer.getStartTime(), computer.getEndTime(), cost);
                history.add(entry);
                return;
            }
        }
        System.out.println("Computer " + id + " is already inactive or does not exist.");
    }

    public void showComputerStatus() {
        for (Computer computer : computers) {
            System.out.print("Computer " + computer.getId() + " is " +
                             (computer.isActive() ? "active" : "inactive"));
            if (computer.isActive()) {
                Duration duration = computer.getActiveDuration();
                long hours = duration.toHours();
                long minutes = duration.toMinutes() % 60;
                System.out.printf(" (Active duration: %d hours %d minutes)%n", hours, minutes);
            } else if (computer.getActiveDuration().toMinutes() > 0) {
                Duration duration = computer.getActiveDuration();
                long hours = duration.toHours();
                long minutes = duration.toMinutes() % 60;
                double cost = computer.calculateCost();
                System.out.printf(" (Last active duration: %d hours %d minutes, Cost: $%.2f)%n", hours, minutes, cost);
            } else {
                System.out.println();
            }
        }
    }
    
    public void showHistory() {
        System.out.println("\n========== Financial Report ==========");
        for (History entry : history) {
            System.out.println(entry);
        }
    }
}
