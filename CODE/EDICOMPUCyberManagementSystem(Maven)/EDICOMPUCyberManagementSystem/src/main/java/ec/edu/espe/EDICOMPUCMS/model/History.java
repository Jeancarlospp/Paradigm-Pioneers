package ec.edu.espe.EDICOMPUCMS.model;

import java.time.Duration;
import java.time.Instant;

public class History {
    private int computerId;
    private Instant startTime;
    private Instant endTime;
    private Duration duration;
    private double cost;

    public History(int computerId, Instant startTime, Instant endTime, double cost) {
        this.computerId = computerId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = Duration.between(startTime, endTime);
        this.cost = cost;
    }

    public int getComputerId() {
        return computerId;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        return "Computer " + computerId + ": " + hours + " hours " + minutes + " minutes, Cost: $" + cost;
    }
}
