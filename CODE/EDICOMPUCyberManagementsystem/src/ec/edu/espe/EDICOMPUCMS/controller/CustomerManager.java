package ec.edu.espe.EDICOMPUCMS.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ec.edu.espe.EDICOMPUCMS.model.Customer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CustomerManager {
    private List<Customer> customers;
    private static final String FILE_NAME = "customers.json";

    public CustomerManager() {
        customers = new ArrayList<>();
        loadCustomers();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
        saveCustomers();
    }

    public void removeCustomer(String id) {
        customers.removeIf(customer -> customer.getId().equals(id));
        saveCustomers();
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    private void loadCustomers() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (FileReader reader = new FileReader(FILE_NAME)) {
                Gson gson = new Gson();
                Type customerListType = new TypeToken<ArrayList<Customer>>() {}.getType();
                customers = gson.fromJson(reader, customerListType);
                if (customers == null) {
                    customers = new ArrayList<>();
                }
            } catch (IOException e) {
                System.out.println("Error loading customer file " + e.getMessage());
            }
        } else {
            customers = new ArrayList<>();
        }
    }

    private void saveCustomers() {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(customers, writer);
        } catch (IOException e) {
            System.out.println("Error saving customer file: " + e.getMessage());
        }
    }

    public void showCustomers() {
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }
}
