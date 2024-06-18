package ec.edu.espe.EDICOMPUCMS.model;

import java.util.ArrayList;
import java.util.List;

public class CyberManager {
    private List<Computer> computers;

    public CyberManager() {
        computers = new ArrayList<>();
        
        for (int i = 1; i <= 10; i++) {
            computers.add(new Computer(i));
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
                return;
            }
        }
        System.out.println("Computer " + id + " is already inactive or does not exist.");
    }

    public void showComputerStatus() {
        for (Computer computer : computers) {
            System.out.println("Computer " + computer.getId() + " is " +
                               (computer.isActive() ? "active" : "inactive"));
            if(computer.isActive()){
                System.out.println(" (Active duration : " + computer.getActiveDuration().toMinutes() + " minutes)");
            } else if (computer.getActiveDuration().toMinutes() > 0) {
                System.out.println(" (Last active duration: " + computer.getActiveDuration().toMinutes()+ "minutes )");
            } else{
                System.out.println();
            }
        }
    }
}