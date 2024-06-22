
package ec.edu.espe.EDICOMPUCMS.utils;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class JsonManager {

    private static final Gson gson = new Gson();

    public static <T> void saveToFile(String fileName, List<T> list) {
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(list, writer);
        } catch (IOException e) {
            System.out.println("Error saving to file " + fileName + ": " + e.getMessage());
        }
    }

    public static <T> List<T> loadFromFile(String fileName, Type type) {
        try (FileReader reader = new FileReader(fileName)) {
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            System.out.println("Error loading from file " + fileName + ": " + e.getMessage());
            return null;
        }
    }
}

