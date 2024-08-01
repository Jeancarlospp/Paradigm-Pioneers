package ec.edu.espe.EDICOMPUCMS.controller;

import ec.edu.espe.EDICOMPUCMS.model.Computer;
import ec.edu.espe.EDICOMPUCMS.model.History;
import ec.edu.espe.EDICOMPUCMS.model.Tariff;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class CyberManager {
    private List<Runnable> historyUpdateListeners = new ArrayList<>();
    private List<Computer> computers;
    private Tariff tariff;
    private List<History> history;

    public CyberManager() {
        tariff = new Tariff(1.00, 0.15);  // 1 dólar por hora, tarifa mínima 15 centavos
        computers = new ArrayList<>();
        history = new ArrayList<>();
        initializeComputers();
    }

    private void initializeComputers() {
        for (int i = 1; i <= 10; i++) {
            computers.add(new Computer(i, tariff));
        }
    }

    public void addHistoryUpdateListener(Runnable listener) {
        historyUpdateListeners.add(listener);
    }

    private void notifyHistoryUpdateListeners() {
        historyUpdateListeners.forEach(Runnable::run);
    }

    public void startComputer(int id) {
        Computer computer = getComputer(id);
        if (computer != null && !computer.isActive()) {
            computer.start();
        }
    }

    public double stopComputer(int id) {
        Computer computer = getComputer(id);
        if (computer != null && computer.isActive()) {
            computer.stop();
            double cost = computer.calculateCost();
            addHistoryEntry(computer, cost);
            notifyHistoryUpdateListeners();
            return cost;
        }
        return 0.0;
    }

    private void addHistoryEntry(Computer computer, double cost) {
        History entry = new History(computer.getId(), computer.getStartTime(), computer.getEndTime(), cost);
        history.add(entry);
    }

    public boolean isComputerActive(int id) {
        Computer computer = getComputer(id);
        return computer != null && computer.isActive();
    }

    public String getComputerActiveTime(int id) {
        Computer computer = getComputer(id);
        return (computer != null) ? formatDuration(computer.getActiveDuration()) : "00:00:00";
    }

    private String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public double getComputerCost(int id) {
        Computer computer = getComputer(id);
        return (computer != null) ? computer.calculateCost() : 0.0;
    }

    private Computer getComputer(int id) {
        return computers.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<History> getHistory() {
        return new ArrayList<>(history);
    }
}