package ec.edu.espe.EDICOMPUCMS.model;

/**
 *
 * @author Andrea Raura,Paradigm Pioneers Squad, DCCO-ESPE
 */

import java.util.ArrayList;
import java.util.List;

public class GeneralReport {
    private static int reportCounter = 0;
    private int reportID;
    private List<Customer> customers;
    private List<Computer> computers;
    private double totalEarnings;

    public GeneralReport() {
        this.reportID = ++reportCounter;
        this.customers = new ArrayList<>();
        this.computers = new ArrayList<>();
        this.totalEarnings = 0.0;
    }

    public int getReportID() {
        return reportID;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Computer> getComputers() {
        return computers;
    }

    public void setComputers(List<Computer> computers) {
        this.computers = computers;
    }

    public double getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(double totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    public void addCustomer(Customer customer) {
        this.customers.add(customer);
    }

    public void addComputer(Computer computer) {
        this.computers.add(computer);
    }

    public void addToEarnings(double earnings) {
        this.totalEarnings += earnings;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("----- Report ").append(reportID).append(" -----\n");

        sb.append("Customers\n");
        for (Customer customer : customers) {
            sb.append(customer.toString()).append("\n");
        }
        sb.append("\n");

        sb.append("Computers\n");
        for (Computer computer : computers) {
            sb.append(computer.toString()).append("\n");
        }
        sb.append("\n");

        sb.append("Total Earnings: ").append(totalEarnings).append("\n");

        return sb.toString();
    }
}
