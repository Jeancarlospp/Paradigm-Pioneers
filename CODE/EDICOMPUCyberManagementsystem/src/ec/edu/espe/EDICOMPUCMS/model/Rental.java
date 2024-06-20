
package ec.edu.espe.EDICOMPUCMS.model;

import java.util.Date;

public class Rental {
    private int rentalID;
    private Customer customer;
    private Computer computer;
    private Date startRental;
    private Date endRental;
    private double rate;

    public Rental(int rentalID, Customer customer, Computer computer, Date startRental, Date endRental, double rate) {
        this.rentalID = rentalID;
        this.customer = customer;
        this.computer = computer;
        this.startRental = startRental;
        this.endRental = endRental;
        this.rate = rate;
    }

    public int getRentalID() {
        return rentalID;
    }

    public void setRentalID(int rentalID) {
        this.rentalID = rentalID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public Date getStartRental() {
        return startRental;
    }

    public void setStartRental(Date startRental) {
        this.startRental = startRental;
    }

    public Date getEndRental() {
        return endRental;
    }

    public void setEndRental(Date endRental) {
        this.endRental = endRental;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Rental{" + "rentalID=" + rentalID + ", customer=" + customer + ", computer=" + computer + ", startRental=" + startRental + ", endRental=" + endRental + ", rate=" + rate + '}';
    }
}
