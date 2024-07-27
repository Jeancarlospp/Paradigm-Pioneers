/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ec.edu.espe.EDICOMPUCMS.controller;

import ec.edu.espe.EDICOMPUCMS.model.History;
import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author KENNED
 */
public class CyberManagerTest {
    
    private CyberManager instance;

    @BeforeAll
    public static void setUpClass() {
        System.out.println("Inicio de tests de CyberManager");
    }

    @AfterAll
    public static void tearDownClass() {
        System.out.println("Fin de tests de CyberManager");
    }

    @BeforeEach
    public void setUp() {
        instance = new CyberManager();
    }

    @AfterEach
    public void tearDown() {
        instance = null;
    }

    @Test
    public void testAddHistoryUpdateListener_nullListener() {
        System.out.println("addHistoryUpdateListener - nullListener");
        Runnable listener = null;
        instance.addHistoryUpdateListener(listener);
        assertTrue(true);  // Verificar que no se arroja una excepción
    }

    @Test
    public void testAddHistoryUpdateListener_validListener() {
        System.out.println("addHistoryUpdateListener - validListener");
        Runnable listener = () -> System.out.println("Listener ejecutado");
        instance.addHistoryUpdateListener(listener);
        assertTrue(true);  // Verificar que no se arroja una excepción
    }

    @Test
    public void testAddHistoryUpdateListener_multipleListeners() {
        System.out.println("addHistoryUpdateListener - multipleListeners");
        Runnable listener1 = () -> System.out.println("Listener 1 ejecutado");
        Runnable listener2 = () -> System.out.println("Listener 2 ejecutado");
        instance.addHistoryUpdateListener(listener1);
        instance.addHistoryUpdateListener(listener2);
        assertTrue(true);  // Verificar que no se arroja una excepción
    }

    @Test
    public void testAddHistoryUpdateListener_execution() {
        System.out.println("addHistoryUpdateListener - execution");
        final boolean[] executed = {false};
        Runnable listener = () -> executed[0] = true;
        instance.addHistoryUpdateListener(listener);
        instance.startComputer(1);
        instance.stopComputer(1);
        assertTrue(executed[0]);
    }

    @Test
    public void testAddHistoryUpdateListener_executionOrder() {
        System.out.println("addHistoryUpdateListener - executionOrder");
        List<String> executionOrder = new ArrayList<>();
        Runnable listener1 = () -> executionOrder.add("Listener 1");
        Runnable listener2 = () -> executionOrder.add("Listener 2");
        instance.addHistoryUpdateListener(listener1);
        instance.addHistoryUpdateListener(listener2);
        instance.startComputer(1);
        instance.stopComputer(1);
        assertEquals(List.of("Listener 1", "Listener 2"), executionOrder);
    }

    @Test
    public void testStartComputer_validId() {
        System.out.println("startComputer - validId");
        int id = 1;
        instance.startComputer(id);
        assertTrue(instance.isComputerActive(id));
    }

    @Test
    public void testStartComputer_invalidId() {
        System.out.println("startComputer - invalidId");
        int id = 11;
        instance.startComputer(id);
        assertFalse(instance.isComputerActive(id));
    }

    @Test
    public void testStartComputer_alreadyActive() {
        System.out.println("startComputer - alreadyActive");
        int id = 1;
        instance.startComputer(id);
        instance.startComputer(id);
        assertTrue(instance.isComputerActive(id));
    }

    @Test
    public void testStartComputer_allComputers() {
        System.out.println("startComputer - allComputers");
        for (int id = 1; id <= 10; id++) {
            instance.startComputer(id);
            assertTrue(instance.isComputerActive(id));
        }
    }

    @Test
    public void testStartComputer_alternateComputers() {
        System.out.println("startComputer - alternateComputers");
        for (int id = 1; id <= 10; id += 2) {
            instance.startComputer(id);
            assertTrue(instance.isComputerActive(id));
        }
        for (int id = 2; id <= 10; id += 2) {
            assertFalse(instance.isComputerActive(id));
        }
    }

    @Test
    public void testStopComputer_validId() {
        System.out.println("stopComputer - validId");
        int id = 1;
        instance.startComputer(id);
        double cost = instance.stopComputer(id);
        assertFalse(instance.isComputerActive(id));
        assertTrue(cost > 0);
    }

    @Test
    public void testStopComputer_invalidId() {
        System.out.println("stopComputer - invalidId");
        int id = 11;
        double cost = instance.stopComputer(id);
        assertEquals(0.0, cost);
    }

    @Test
    public void testStopComputer_notStarted() {
        System.out.println("stopComputer - notStarted");
        int id = 1;
        double cost = instance.stopComputer(id);
        assertEquals(0.0, cost);
    }

    @Test
    public void testStopComputer_multipleStops() {
        System.out.println("stopComputer - multipleStops");
        int id = 1;
        instance.startComputer(id);
        instance.stopComputer(id);
        double cost = instance.stopComputer(id);
        assertEquals(0.0, cost);
    }

    @Test
    public void testStopComputer_alternateComputers() {
        System.out.println("stopComputer - alternateComputers");
        for (int id = 1; id <= 10; id += 2) {
            instance.startComputer(id);
        }
        for (int id = 1; id <= 10; id += 2) {
            double cost = instance.stopComputer(id);
            assertTrue(cost > 0);
        }
    }

    @Test
    public void testIsComputerActive_active() {
        System.out.println("isComputerActive - active");
        int id = 1;
        instance.startComputer(id);
        assertTrue(instance.isComputerActive(id));
    }

    @Test
    public void testIsComputerActive_inactive() {
        System.out.println("isComputerActive - inactive");
        int id = 1;
        assertFalse(instance.isComputerActive(id));
    }

    @Test
    public void testIsComputerActive_invalidId() {
        System.out.println("isComputerActive - invalidId");
        int id = 11;
        assertFalse(instance.isComputerActive(id));
    }

    @Test
    public void testIsComputerActive_afterStop() {
        System.out.println("isComputerActive - afterStop");
        int id = 1;
        instance.startComputer(id);
        instance.stopComputer(id);
        assertFalse(instance.isComputerActive(id));
    }

    @Test
    public void testIsComputerActive_multipleComputers() {
        System.out.println("isComputerActive - multipleComputers");
        for (int id = 1; id <= 5; id++) {
            instance.startComputer(id);
        }
        for (int id = 1; id <= 5; id++) {
            assertTrue(instance.isComputerActive(id));
        }
        for (int id = 6; id <= 10; id++) {
            assertFalse(instance.isComputerActive(id));
        }
    }
}