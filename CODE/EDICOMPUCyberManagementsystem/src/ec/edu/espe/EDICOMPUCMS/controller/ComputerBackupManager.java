package ec.edu.espe.EDICOMPUCMS.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ComputerBackupManager {
    private static final String FILE_NAME = "computer_backup.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void backupComputerStatuses() {
        Map<String, String> computerStatuses = generateComputerStatuses();
        saveComputerStatuses(computerStatuses);
    }

    private static Map<String, String> generateComputerStatuses() {
        Map<String, String> computerStatuses = new LinkedHashMap<>();
        for (int i = 1; i <= 10; i++) {
            computerStatuses.put("computer " + i, "available");
        }
        return computerStatuses;
    }

    private static void saveComputerStatuses(Map<String, String> computerStatuses) {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            gson.toJson(computerStatuses, writer);
            // Omitir el mensaje de respaldo completado
        } catch (IOException e) {
            System.out.println("Error saving computer statuses to file " + FILE_NAME + ": " + e.getMessage());
        }
    }
}