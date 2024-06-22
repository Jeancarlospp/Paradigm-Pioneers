
package ec.edu.espe.EDICOMPUCMS.model;

import java.util.Date;


public class Fees {
    
    private int feeID;
    private Customer customer;
    private Rental rental;
    private Reservation reservation;
    private double totalAmount;
    private Date dueDate;
    
     public Fees(int feeID, Customer customer, Rental rental, Reservation reservation, double totalAmount, Date dueDate) {
        this.feeID = feeID;
        this.customer = customer;
        this.rental = rental;
        this.reservation = reservation;
        this.totalAmount = totalAmount;
        this.dueDate = dueDate;
    }

    public int getFeeID() {
        return feeID;
    }

    public void setFeeID(int feeID) {
        this.feeID = feeID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Fees{" + "feeID=" + feeID + ", customer=" + customer + ", rental=" + rental + ", reservation=" + reservation + ", totalAmount=" + totalAmount + ", dueDate=" + dueDate + '}';
    }
}
