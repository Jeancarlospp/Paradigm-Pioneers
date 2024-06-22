
package ec.edu.espe.EDICOMPUCMS.model;

import java.util.Date;

public class Payment {
    private int paymentID;
    private Customer customer;
    private double amount;
    private Date paymentDate;

    public Payment(int paymentID, Customer customer, double amount, Date paymentDate) {
        this.paymentID = paymentID;
        this.customer = customer;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return "Payment{" + "paymentID=" + paymentID + ", customer=" + customer + ", amount=" + amount + ", paymentDate=" + paymentDate + '}';
    }
    
}
