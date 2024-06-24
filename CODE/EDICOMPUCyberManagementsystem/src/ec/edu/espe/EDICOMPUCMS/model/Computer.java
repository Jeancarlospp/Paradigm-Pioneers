package ec.edu.espe.EDICOMPUCMS.model;

import java.time.Duration;
import java.time.Instant;

public class Computer {
    private int id;
    private boolean isActive;
    private Instant startTime;
    private Instant endTime;
    private Tariff tariff;

    public Computer(int id, Tariff tariff) {
        this.id = id;
        this.isActive = false;
        this.tariff = tariff;
    }

    public int getId() {
        return id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void start() {
        this.isActive = true;
        this.startTime = Instant.now();
        this.endTime = null;
    }

    public void stop() {
        this.isActive = false;
        this.endTime = Instant.now();
    }

    public Duration getActiveDuration() {
        if (startTime == null) {
            return Duration.ZERO;
        }

        if (isActive) {
            return Duration.between(startTime, Instant.now());
        } else {
            return Duration.between(startTime, endTime);
        }
    }

    public double calculateCost() {
        return tariff.calculateCost(getActiveDuration());
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }
    
    public Instant getStartTime() {
        return startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return "Computer{" + "id=" + id + ", isActive=" + isActive + ", startTime=" + startTime + ", endTime=" + endTime + '}';
    }
}
