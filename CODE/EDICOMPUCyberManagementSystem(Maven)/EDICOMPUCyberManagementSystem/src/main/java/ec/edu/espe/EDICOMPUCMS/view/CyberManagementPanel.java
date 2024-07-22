package ec.edu.espe.EDICOMPUCMS.view;

import ec.edu.espe.EDICOMPUCMS.controller.CyberManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class CyberManagementPanel extends JPanel {
    private CyberManager cyberManager;
    private JPanel computerGrid;
    private JTextArea statusArea;
    private Timer timer;

    public CyberManagementPanel() {
        cyberManager = new CyberManager();
        setLayout(new BorderLayout());
        
        // Título
        JLabel titleLabel = new JLabel("Cyber Management", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        // Grid de computadoras
        computerGrid = new JPanel(new GridLayout(2, 5, 10, 10));
        mainPanel.add(computerGrid, BorderLayout.CENTER);

        // Área de estado
        statusArea = new JTextArea(10, 30);
        statusArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(statusArea);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        initializeComputerGrid();
        startTimer();
    }

    private void initializeComputerGrid() {
        for (int i = 1; i <= 10; i++) {
            JPanel computerPanel = createComputerPanel(i);
            computerGrid.add(computerPanel);
        }
    }

    private JPanel createComputerPanel(int id) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.putClientProperty("computerId", id);
        panel.setBorder(BorderFactory.createTitledBorder("PC " + id));

        JLabel statusLabel = new JLabel("Status: Inactive", SwingConstants.CENTER);
        panel.add(statusLabel, BorderLayout.NORTH);

        JLabel timeLabel = new JLabel("Time: 00:00:00", SwingConstants.CENTER);
        panel.add(timeLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton startButton = new JButton("Start");
        JButton stopButton = new JButton("Stop");

        startButton.addActionListener(e -> startComputer(panel, statusLabel, timeLabel, startButton, stopButton));
        stopButton.addActionListener(e -> stopComputer(panel, statusLabel, timeLabel, startButton, stopButton));

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void startComputer(JPanel computerPanel, JLabel statusLabel, JLabel timeLabel, JButton startButton, JButton stopButton) {
        int id = (int) computerPanel.getClientProperty("computerId");
        cyberManager.startComputer(id);
        statusLabel.setText("Status: Active");
        startButton.setEnabled(false);
        stopButton.setEnabled(true);
        updateStatus("Computer " + id + " started.");
    }

    private void stopComputer(JPanel computerPanel, JLabel statusLabel, JLabel timeLabel, JButton startButton, JButton stopButton) {
        int id = (int) computerPanel.getClientProperty("computerId");
        cyberManager.stopComputer(id);
        statusLabel.setText("Status: Inactive");
        timeLabel.setText("Time: 00:00:00");
        startButton.setEnabled(true);
        stopButton.setEnabled(false);
        updateStatus("Computer " + id + " stopped.");
    }

    private void updateStatus(String message) {
        statusArea.append(message + "\n");
        statusArea.setCaretPosition(statusArea.getDocument().getLength());
    }

    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> updateComputerStatus());
            }
        }, 0, 1000);
    }

    private void updateComputerStatus() {
        for (Component component : computerGrid.getComponents()) {
            if (component instanceof JPanel) {
                JPanel computerPanel = (JPanel) component;
                int id = (int) computerPanel.getClientProperty("computerId");
                JLabel statusLabel = (JLabel) computerPanel.getComponent(0);
                JLabel timeLabel = (JLabel) computerPanel.getComponent(1);

                if (cyberManager.isComputerActive(id)) {
                    statusLabel.setText("Status: Active");
                    timeLabel.setText("Time: " + cyberManager.getComputerActiveTime(id));
                } else {
                    statusLabel.setText("Status: Inactive");
                }
            }
        }
    }
}