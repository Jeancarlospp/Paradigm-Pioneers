/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.EDICOMPUCMS.model;

/**
 *
 * @author KENNED
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ComputerMenu {
    private List<Computer> computers;
    private Scanner scanner;

    public ComputerMenu() {
        computers = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void addComputer() {
        System.out.print("Ingrese ID: ");
        int id = scanner.nextInt();
        System.out.print("Ingrese estado: ");
        String status = scanner.next();
        System.out.print("Ingrese el uso del tiempo: ");
        int timeUsage = scanner.nextInt();
        
        Computer computer = new Computer(id, status, timeUsage);
        computers.add(computer);
        System.out.println("Computadora agregada exitosamente.");
    }

    public void deleteComputer() {
        System.out.print("Ingrese el ID de la computadora a eliminar: ");
        int id = scanner.nextInt();
        Computer computerToRemove = null;

        for (Computer computer : computers) {
            if (computer.getId() == id) {
                computerToRemove = computer;
                break;
            }
        }

        if (computerToRemove != null) {
            computers.remove(computerToRemove);
            System.out.println("Computadora eliminada exitosamente.");
        } else {
            System.out.println("Computadora no encontrada.");
        }
    }

    public void searchComputer() {
        System.out.print("Ingrese el ID de la computadora para buscar: ");
        int id = scanner.nextInt();
        
        for (Computer computer : computers) {
            if (computer.getId() == id) {
                System.out.println("Computadora encontrada: " + computer);
                return;
            }
        }
        
        System.out.println("Computadora no encontrada.");
    }

    public void listComputers() {
        if (computers.isEmpty()) {
            System.out.println("No hay computadoras disponibles.");
        } else {
            for (Computer computer : computers) {
                System.out.println(computer);
            }
        }
    }

    public static void main(String[] args) {
        ComputerMenu menu = new ComputerMenu();
        Scanner scanner = new Scanner(System.in);
        int choice;
        
        do {
            System.out.println("1. Agregar computadora");
            System.out.println("2. Eliminar computadora");
            System.out.println("3. Buscar computadora");
            System.out.println("4. Listar computadoras");
            System.out.println("5. Salir");
            System.out.print("Ingrese su opcion: ");
            choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    menu.addComputer();
                    break;
                case 2:
                    menu.deleteComputer();
                    break;
                case 3:
                    menu.searchComputer();
                    break;
                case 4:
                    menu.listComputers();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Elección no válida. Inténtalo de nuevo.");
            }
        } while (choice != 5);

        scanner.close();
    }
}

