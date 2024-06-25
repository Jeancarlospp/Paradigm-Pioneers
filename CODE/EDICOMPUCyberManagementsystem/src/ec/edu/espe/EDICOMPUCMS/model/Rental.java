package ec.edu.espe.EDICOMPUCMS.model;

public class Rental {
    private int rentalID;
    private Customer customer;
    private Computer computer;

    public Rental(int rentalID, Customer customer, Computer computer) {
        this.rentalID = rentalID;
        this.customer = customer;
        this.computer = computer;
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

    @Override
    public String toString() {
        return "Rental{" + "rentalID=" + rentalID + ", customer=" + customer + ", computer=" + computer + '}';
    }
}
