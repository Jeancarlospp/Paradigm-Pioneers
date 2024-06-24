package ec.edu.espe.EDICOMPUCMS.model;

import java.time.Duration;

public class Tariff {
    private double ratePerHour;
    private double minimumCharge;

    public Tariff(double ratePerHour, double minimumCharge) {
        this.ratePerHour = ratePerHour;
        this.minimumCharge = minimumCharge;
    }

    public double calculateCost(Duration duration) {
        double hours = duration.toMinutes() / 60.0;
        double cost = hours * ratePerHour;
        return Math.max(cost, minimumCharge);
    }

    public double getRatePerHour() {
        return ratePerHour;
    }

    public void setRatePerHour(double ratePerHour) {
        this.ratePerHour = ratePerHour;
    }

    public double getMinimumCharge() {
        return minimumCharge;
    }

    public void setMinimumCharge(double minimumCharge) {
        this.minimumCharge = minimumCharge;
    }
}
