package ec.edu.espe.EDICOMPUCMS.controller;

import ec.edu.espe.EDICOMPUCMS.model.Computer;
import ec.edu.espe.EDICOMPUCMS.model.History;
import ec.edu.espe.EDICOMPUCMS.model.Tariff;

import java.time.Duration;
import java.time.Instant;
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
        Computer computer = getComputerById(id);
        if (computer != null && !computer.isActive()) {
            computer.start();
        }
    }

    public double stopComputer(int id) {
        Computer computer = getComputerById(id);
        if (computer != null && computer.isActive()) {
            computer.stop();
            double cost = computer.calculateCost();
            History entry = new History(computer.getId(), computer.getStartTime(), computer.getEndTime(), cost);
            history.add(entry);
            return cost;
        }
        return 0.0;
    }

    public boolean isComputerActive(int id) {
        Computer computer = getComputerById(id);
        return computer != null && computer.isActive();
    }

    public String getComputerActiveTime(int id) {
        Computer computer = getComputerById(id);
        if (computer != null) {
            Duration duration = computer.getActiveDuration();
            long hours = duration.toHours();
            long minutes = duration.toMinutesPart();
            long seconds = duration.toSecondsPart();
            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        }
        return "00:00:00";
    }

    public double getComputerCost(int id) {
        Computer computer = getComputerById(id);
        if (computer != null) {
            return computer.calculateCost();
        }
        return 0.0;
    }

    private Computer getComputerById(int id) {
        return computers.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<History> getHistory() {
        return new ArrayList<>(history);
    }
}