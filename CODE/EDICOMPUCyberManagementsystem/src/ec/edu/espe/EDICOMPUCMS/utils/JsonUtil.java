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
<<<<<<< HEAD
=======
import java.time.Instant;
>>>>>>> c5beda7e217edd8fffcf33a829bc5d067edfd558
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
<<<<<<< HEAD
    private static final String CUSTOMERS_FILE = "customers.json";
    private static final String COMPUTERS_FILE = "computers.json";
    private static final String GENERAL_REPORT_FILE = "generalReport.json";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

=======
    private static final String DATA_FOLDER = "data";
    private static final String CUSTOMERS_FILE = DATA_FOLDER + File.separator + "customers.json";
    private static final String COMPUTERS_FILE = DATA_FOLDER + File.separator + "computers.json";
    private static final String GENERAL_REPORT_FILE = DATA_FOLDER + File.separator + "generalReport.json";
    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(Instant.class, new InstantAdapter())
            .setPrettyPrinting()
            .create();

    static {
        File dataFolder = new File(DATA_FOLDER);
        if (!dataFolder.exists()) {
            dataFolder.mkdirs(); // Crear la carpeta si no existe
        }
    }

    // Save customers to JSON file
>>>>>>> c5beda7e217edd8fffcf33a829bc5d067edfd558
    public static void saveCustomersToJson(List<Customer> customers) {
        try (Writer writer = new FileWriter(CUSTOMERS_FILE)) {
            GSON.toJson(customers, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

<<<<<<< HEAD
=======
    // Read customers from JSON file
>>>>>>> c5beda7e217edd8fffcf33a829bc5d067edfd558
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
<<<<<<< HEAD
=======
            // File not found, return an empty list
>>>>>>> c5beda7e217edd8fffcf33a829bc5d067edfd558
        } catch (IOException | JsonSyntaxException e) {
            e.printStackTrace();
        }
        return customers;
    }

<<<<<<< HEAD
=======
    // Save computers to JSON file
>>>>>>> c5beda7e217edd8fffcf33a829bc5d067edfd558
    public static void saveComputersToJson(List<Computer> computers) {
        try (Writer writer = new FileWriter(COMPUTERS_FILE)) {
            GSON.toJson(computers, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

<<<<<<< HEAD
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
=======
    // Add a computer to the JSON file
    public static void addComputerToJson(Computer computer) {
        List<Computer> computers = readComputersFromJson();
        computers.add(computer);
        saveComputersToJson(computers);
    }

    // Read computers from JSON file
    public static List<Computer> readComputersFromJson() {
        List<Computer> computers = new ArrayList<>();
        File file = new File(COMPUTERS_FILE);
        if (file.exists() && file.length() > 0) {
            try (Reader reader = new FileReader(COMPUTERS_FILE)) {
                Computer[] computersArray = GSON.fromJson(reader, Computer[].class);
                if (computersArray != null) {
                    for (Computer computer : computersArray) {
                        computers.add(computer);
                    }
                }
            } catch (IOException | JsonSyntaxException e) {
                e.printStackTrace();
            }
>>>>>>> c5beda7e217edd8fffcf33a829bc5d067edfd558
        }
        return computers;
    }

<<<<<<< HEAD
=======
    // Save general report to JSON file
>>>>>>> c5beda7e217edd8fffcf33a829bc5d067edfd558
    public static void saveGeneralReportToJson(GeneralReport generalReport) {
        try (Writer writer = new FileWriter(GENERAL_REPORT_FILE)) {
            GSON.toJson(generalReport, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

<<<<<<< HEAD
=======
    // Read general report from JSON file
>>>>>>> c5beda7e217edd8fffcf33a829bc5d067edfd558
    public static GeneralReport readGeneralReportFromJson() {
        GeneralReport generalReport = null;
        try (Reader reader = new FileReader(GENERAL_REPORT_FILE)) {
            generalReport = GSON.fromJson(reader, GeneralReport.class);
        } catch (FileNotFoundException e) {
<<<<<<< HEAD
=======
            // File not found, return null
>>>>>>> c5beda7e217edd8fffcf33a829bc5d067edfd558
        } catch (IOException | JsonSyntaxException e) {
            e.printStackTrace();
        }
        return generalReport;
    }
}