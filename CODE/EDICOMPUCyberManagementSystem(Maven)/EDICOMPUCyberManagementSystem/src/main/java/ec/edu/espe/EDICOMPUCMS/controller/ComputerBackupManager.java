package ec.edu.espe.EDICOMPUCMS.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leidy Saraguro,Paradigm Pioneers Squad, DCCO-ESPE
 */

public class ComputerBackupManager {
    private static final Logger LOGGER = Logger.getLogger(ComputerBackupManager.class.getName());
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void backupComputerStatuses(String fileName) {
        Map<String, String> computerStatuses = generateStatuses();
        writeStatusesToFile(computerStatuses, fileName);
    }

    private static Map<String, String> generateStatuses() {
        Map<String, String> statuses = new LinkedHashMap<>();
        for (int i = 1; i <= 10; i++) {
            statuses.put("computer " + i, "available");
        }
        return Collections.unmodifiableMap(statuses);
    }

    private static void writeStatusesToFile(Map<String, String> statuses, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(statuses, writer);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error saving computer statuses to file " + fileName, e);
        }
    }
}