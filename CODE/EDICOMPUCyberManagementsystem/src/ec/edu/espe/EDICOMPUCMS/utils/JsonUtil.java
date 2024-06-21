package ec.edu.espe.EDICOMPUCMS.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import ec.edu.espe.EDICOMPUCMS.model.Computer;
import ec.edu.espe.EDICOMPUCMS.model.Customer;
import ec.edu.espe.EDICOMPUCMS.model.GeneralReport;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
    private static final String CUSTOMERS_FILE = "customers.json";
    private static final String COMPUTERS_FILE = "computers.json";
    private static final String GENERAL_REPORT_FILE = "generalReport.json";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void saveCustomersToJson(List<Customer> customers) {
        try (Writer writer = new FileWriter(CUSTOMERS_FILE)) {
            GSON.toJson(customers, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Customer> readCustomersFromJson() {
        List<Customer> customers = new ArrayList<>();
        try (Reader reader = new FileReader(CUSTOMERS_FILE)) {
            Customer[] customersArray = GSON.fromJson(reader, Customer[].class);
            if (customersArray != null) {
                for (Customer customer : customersArray) {
                    customers.add(customer);
                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException | JsonSyntaxException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public static void saveComputersToJson(List<Computer> computers) {
        try (Writer writer = new FileWriter(COMPUTERS_FILE)) {
            GSON.toJson(computers, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Computer> readComputersFromJson() {
        List<Computer> computers = new ArrayList<>();
        try (Reader reader = new FileReader(COMPUTERS_FILE)) {
            Computer[] computersArray = GSON.fromJson(reader, Computer[].class);
            if (computersArray != null) {
                for (Computer computer : computersArray) {
                    computers.add(computer);
                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException | JsonSyntaxException e) {
            e.printStackTrace();
        }
        return computers;
    }

    public static void saveGeneralReportToJson(GeneralReport generalReport) {
        try (Writer writer = new FileWriter(GENERAL_REPORT_FILE)) {
            GSON.toJson(generalReport, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GeneralReport readGeneralReportFromJson() {
        GeneralReport generalReport = null;
        try (Reader reader = new FileReader(GENERAL_REPORT_FILE)) {
            generalReport = GSON.fromJson(reader, GeneralReport.class);
        } catch (FileNotFoundException e) {
        } catch (IOException | JsonSyntaxException e) {
            e.printStackTrace();
        }
        return generalReport;
    }
}