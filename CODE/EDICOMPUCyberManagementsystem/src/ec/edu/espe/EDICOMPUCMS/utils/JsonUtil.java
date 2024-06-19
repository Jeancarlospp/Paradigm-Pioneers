/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.EDICOMPUCMS.utils;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.FileReader;
import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

/**
 *
 * @author Leidy Saraguro,Paradigm Pioneers Squad, DCCO-ESPE
 */
public class JsonUtil {
    private static final Gson gson = new Gson();

    public static <T> void saveToJson(String filename, List<T> list) {
        try (Writer writer = new FileWriter(filename)) {
            gson.toJson(list, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> List<T> readFromJson(String filename, Type type) {
        List<T> data = null;
        try (Reader reader = new FileReader(filename)) {
            data = gson.fromJson(reader, type);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
            createJsonFile(filename);
            // Attempt to read again after creating the file
            try (Reader reader = new FileReader(filename)) {
                data = gson.fromJson(reader, type);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private static void createJsonFile(String filename) {
        File file = new File(filename);
        try {
            if (file.createNewFile()) {
                System.out.println("Created new file: " + filename);
                try (Writer writer = new FileWriter(filename)) {
                    writer.write("[]"); // Write an empty JSON array
                }
            } else {
                System.err.println("Failed to create file: " + filename);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}