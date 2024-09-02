package ec.edu.espe.EDICOMPUCMS.view;

import ec.edu.espe.EDICOMPUCMS.controller.CyberManager;
import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.text.DecimalFormat;

public class CyberManagementPanel extends JPanel {
    
    private CyberManager cyberManager;
    private JPanel computerGrid;
    private JTextArea activityLog;
    private Timer timer;

    private static final Color BACKGROUND_COLOR = new Color(240, 240, 240);
    private static final Color ACCENT_COLOR = new Color(70, 130, 180);
    private static final Color BUTTON_START_COLOR = new Color(100, 180, 100);
    private static final Color BUTTON_STOP_COLOR = new Color(180, 100, 100);
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 24);
    private static final Font REGULAR_FONT = new Font("Arial", Font.PLAIN, 12);

    public CyberManagementPanel(CyberManager cyberManager) {
        this.cyberManager = cyberManager;
        cyberManager = new CyberManager();
        setLayout(new BorderLayout());
        setBackground(BACKGROUND_COLOR);
        
        initComponents();
        startTimer();
    }

    private void initComponents() {
        // Title
        JLabel titleLabel = createStyledLabel("Cyber Management", TITLE_FONT, ACCENT_COLOR);
        add(titleLabel, BorderLayout.NORTH);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(BACKGROUND_COLOR);
        add(mainPanel, BorderLayout.CENTER);

        // Computer grid
        computerGrid = new JPanel(new GridLayout(2, 5, 10, 10));
        computerGrid.setBackground(BACKGROUND_COLOR);
        mainPanel.add(computerGrid, BorderLayout.CENTER);

        // Activity log
        activityLog = new JTextArea(5, 30);
        activityLog.setEditable(false);
        activityLog.setFont(REGULAR_FONT);
        activityLog.setForeground(ACCENT_COLOR);
        JScrollPane scrollPane = new JScrollPane(activityLog);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        initializeComputerGrid();
    }

    private JLabel createStyledLabel(String text, Font font, Color color) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(font);
        label.setForeground(color);
        return label;
    }

    private void initializeComputerGrid() {
        for (int i = 1; i <= 10; i++) {
            JPanel computerPanel = createComputerPanel(i);
            computerGrid.add(computerPanel);
        }
    }

    private JPanel createComputerPanel(int id) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(ACCENT_COLOR, 1));
        panel.putClientProperty("computerId", id);

        JLabel iconLabel = new JLabel(new ImageIcon("C:\\Users\\PACO\\OneDrive\\Universidad\\SEGUNDO SEMESTRE\\POO\\Paradigm-Pioneers\\CODE\\EDICOMPUCyberManagementSystem(Maven)\\Logotipo - EDICOMPU\\Compu.png"));
        panel.add(iconLabel, BorderLayout.CENTER);

        JLabel statusLabel = createStyledLabel("Inactive", REGULAR_FONT, Color.RED);
        panel.add(statusLabel, BorderLayout.NORTH);

        JLabel timeLabel = createStyledLabel("00:00:00", REGULAR_FONT, Color.BLACK);
        panel.add(timeLabel, BorderLayout.WEST);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        buttonPanel.setBackground(Color.WHITE);
        JButton startButton = createStyledButton("Start", BUTTON_START_COLOR);
        JButton stopButton = createStyledButton("Stop", BUTTON_STOP_COLOR);

        startButton.addActionListener(e -> startComputer(id, panel, statusLabel, timeLabel, startButton, stopButton));
        stopButton.addActionListener(e -> stopComputer(id, panel, statusLabel, timeLabel, startButton, stopButton));

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(REGULAR_FONT);
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        return button;
    }

   private void startComputer(int id, JPanel computerPanel, JLabel statusLabel, JLabel timeLabel, JButton startButton, JButton stopButton) {
        cyberManager.startComputer(id);
        statusLabel.setText("Active");
        statusLabel.setForeground(BUTTON_START_COLOR);
        startButton.setEnabled(false);
        stopButton.setEnabled(true);
        updateLog("Computer " + id + " started.");
    }


    private void stopComputer(int id, JPanel computerPanel, JLabel statusLabel, JLabel timeLabel, JButton startButton, JButton stopButton) {
        String activeTime = cyberManager.getComputerActiveTime(id);
        double cost = cyberManager.stopComputer(id);
        statusLabel.setText("Inactive");
        statusLabel.setForeground(Color.RED);
        timeLabel.setText("00:00:00");
        startButton.setEnabled(true);
        stopButton.setEnabled(false);
        
        DecimalFormat df = new DecimalFormat("#.##");
        updateLog("Computer " + id + " stopped. Active time: " + activeTime + ", Cost: $" + df.format(cost));
    }


    private void updateLog(String message) {
        activityLog.append(message + "\n");
        activityLog.setCaretPosition(activityLog.getDocument().getLength());
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
                    statusLabel.setText("Active");
                    statusLabel.setForeground(BUTTON_START_COLOR);
                    timeLabel.setText(cyberManager.getComputerActiveTime(id));
                } else {
                    statusLabel.setText("Inactive");
                    statusLabel.setForeground(Color.RED);
                }
            }
        }
    }
}