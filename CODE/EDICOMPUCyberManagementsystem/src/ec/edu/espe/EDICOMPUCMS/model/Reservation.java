
package ec.edu.espe.EDICOMPUCMS.model;

import java.util.Date;


public class Reservation {
    private int reservationID;
    private Customer customer;
    private Date reservationDate;
    private Date reservationTime;

    public Reservation(int reservationID, Customer customer, Date reservationDate, Date reservationTime) {
        this.reservationID = reservationID;
        this.customer = customer;
        this.reservationDate = reservationDate;
        this.reservationTime = reservationTime;
    }

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Date getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(Date reservationTime) {
        this.reservationTime = reservationTime;
    }

    @Override
    public String toString() {
        return "Reservation{" + "reservationID=" + reservationID + ", customer=" + customer + ", reservationDate=" + reservationDate + ", reservationTime=" + reservationTime + '}';
    }
}
