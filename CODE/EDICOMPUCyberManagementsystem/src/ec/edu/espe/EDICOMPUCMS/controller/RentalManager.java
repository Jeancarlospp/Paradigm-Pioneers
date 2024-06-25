package ec.edu.espe.EDICOMPUCMS.controller;
/**
 *
 * @author Andrea Raura,Paradigm Pioneers Squad, DCCO-ESPE
 */
import ec.edu.espe.EDICOMPUCMS.model.Computer;
import ec.edu.espe.EDICOMPUCMS.model.Customer;
import ec.edu.espe.EDICOMPUCMS.model.Rental;
import ec.edu.espe.EDICOMPUCMS.model.Tariff;

import java.util.ArrayList;
import java.util.List;

public class RentalManager {
    private List<Rental> rentals;
    private List<Customer> customers;
    private List<Computer> computers;
    private int nextRentalID;

    public RentalManager() {
        rentals = new ArrayList<>();
        customers = new ArrayList<>();
        computers = new ArrayList<>();
        nextRentalID = 1;
        // Initialize computers with a default tariff
        Tariff defaultTariff = new Tariff(1.0, 0.15); // $1.0 per hour, $0.15 minimum charge
        for (int i = 1; i <= 10; i++) {
            computers.add(new Computer(i, defaultTariff));
        }
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void startRental(String customerID, int computerID) {
        Customer customer = findCustomerById(customerID);
        Computer computer = findComputerById(computerID);

        if (customer != null && computer != null && !computer.isActive()) {
            computer.start();
            Rental rental = new Rental(nextRentalID++, customer, computer);
            rentals.add(rental);
            System.out.println("Computer rented successfully!");
        } else {
            System.out.println("Error: Invalid customer/computer ID or computer is already active.");
        }
    }

    public void endRental(int rentalID) {
        Rental rental = findRentalById(rentalID);
        if (rental != null) {
            rental.getComputer().stop();
            double totalCost = rental.getComputer().calculateCost();
            System.out.println("Rental ended. Total payment: $" + totalCost);
        } else {
            System.out.println("Error: Rental ID not found.");
        }
    }

    public void showRentals() {
        for (Rental rental : rentals) {
            System.out.println(rental);
        }
    }

    private Customer findCustomerById(String id) {
        for (Customer customer : customers) {
            if (customer.getId().equals(id)) {
                return customer;
            }
        }
        return null;
    }

    private Computer findComputerById(int id) {
        for (Computer computer : computers) {
            if (computer.getId() == id) {
                return computer;
            }
        }
        return null;
    }

    private Rental findRentalById(int id) {
        for (Rental rental : rentals) {
            if (rental.getRentalID() == id) {
                return rental;
            }
        }
        return null;
    }
}

