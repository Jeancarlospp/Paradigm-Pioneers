
package ec.edu.espe.EDICOMPUCMS.model;

import java.util.Date;

public class Invoice {
     private int invoiceID;
    private Customer customer;
    private Date date;
    private double amount;

    public Invoice(int invoiceID, Customer customer, Date date, double amount) {
        this.invoiceID = invoiceID;
        this.customer = customer;
        this.date = date;
        this.amount = amount;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Invoice{" + "invoiceID=" + invoiceID + ", customer=" + customer + ", date=" + date + ", amount=" + amount + '}';
    }
    
}
