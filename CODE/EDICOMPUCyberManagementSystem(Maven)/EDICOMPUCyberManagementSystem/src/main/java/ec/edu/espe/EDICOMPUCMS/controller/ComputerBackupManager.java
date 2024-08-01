package ec.edu.espe.EDICOMPUCMS.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Leidy Saraguro,Paradigm Pioneers Squad, DCCO-ESPE
 */

public class ComputerBackupManager {
    private static final String FILE_NAME = "computer_backup.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void backupComputerStatuses() {
        Map<String, String> computerStatuses = createStatuses();
        writeStatusesToFile(computerStatuses);
    }

    private static Map<String, String> createStatuses() {
        return generateStatuses();
    }
    
    private static Map<String, String> generateStatuses() {
        Map<String, String> statuses = new LinkedHashMap<>();
        for (int i = 1; i <= 10; i++) {
            statuses.put("computer " + i, "available");
        }
        return statuses;
    }

    private static void writeStatusesToFile(Map<String, String> statuses) {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            gson.toJson(statuses, writer);
        } catch (IOException e) {
            System.out.println("Error saving computer statuses to file " + FILE_NAME + ": " + e.getMessage());
        }
    }
}