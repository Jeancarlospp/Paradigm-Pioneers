package ec.edu.espe.EDICOMPUCMS.controller;

import ec.edu.espe.EDICOMPUCMS.view.CustomerManagementPanel;
import ec.edu.espe.EDICOMPUCMS.view.LoginScreen;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainMenu extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JPanel sidePanel;
    private JLabel statusLabel;
    private JLabel clockLabel;

    public MainMenu() {
        setTitle("EDICOMPU Cyber Management System");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set up main layout
        setLayout(new BorderLayout());

        // Create and set up menu bar
        setJMenuBar(createMenuBar());

        // Create side panel
        sidePanel = createSidePanel();
        add(sidePanel, BorderLayout.WEST);

        // Create main content area
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(245, 245, 245);
                Color color2 = new Color(230, 230, 230);
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        add(mainPanel, BorderLayout.CENTER);

        // Add panels to main content area
        mainPanel.add(createHomePanel(), "Home");
        mainPanel.add(createCustomersPanel(), "Customers");
        mainPanel.add(createCyberManagementPanel(), "CyberManagement");
        mainPanel.add(createComputerRentalPanel(), "ComputerRental");
        mainPanel.add(createPaymentsPanel(), "Payments");

        // Create status bar
        JPanel statusBar = createStatusBar();
        add(statusBar, BorderLayout.SOUTH);

        // Show home panel by default
        cardLayout.show(mainPanel, "Home");
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(26, 35, 126));

        JMenu editMenu = new JMenu("Edit");
        editMenu.setForeground(Color.WHITE);
        addMenuItem(editMenu, "Preferences", e -> JOptionPane.showMessageDialog(this, "Edit preferences"));

        JMenu viewMenu = new JMenu("View");
        viewMenu.setForeground(Color.WHITE);
        addMenuItem(viewMenu, "Full Screen", e -> JOptionPane.showMessageDialog(this, "Toggle full screen"));

        JMenu helpMenu = new JMenu("Help");
        helpMenu.setForeground(Color.WHITE);
        addMenuItem(helpMenu, "About", e -> JOptionPane.showMessageDialog(this, "About EDICOMPU CMS"));

        menuBar.add(editMenu);
        menuBar.add(viewMenu);
        menuBar.add(helpMenu);

        return menuBar;
    }

    private JPanel createSidePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(new Color(26, 35, 126));

        String[] buttonLabels = {"Home", "Customers", "Cyber Management", "Computer Rental", "Payments"};
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setAlignmentX(Component.LEFT_ALIGNMENT);
            button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getMinimumSize().height));
            button.setBackground(new Color(63, 81, 181));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.addActionListener(e -> cardLayout.show(mainPanel, label.replace(" ", "")));
            panel.add(button);
            panel.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        JButton logoutButton = new JButton("Logout");
        logoutButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        logoutButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, logoutButton.getMinimumSize().height));
        logoutButton.setBackground(new Color(63, 81, 181));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFocusPainted(false);
        logoutButton.addActionListener(e -> logout());
        panel.add(Box.createVerticalGlue());
        panel.add(logoutButton);

        return panel;
    }

    private JPanel createStatusBar() {
        JPanel statusBar = new JPanel(new BorderLayout());
        statusBar.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        statusBar.setBackground(new Color(245, 245, 245));

        statusLabel = new JLabel("Ready");
        statusBar.add(statusLabel, BorderLayout.WEST);

        clockLabel = new JLabel();
        updateClock();
        Timer timer = new Timer(1000, e -> updateClock());
        timer.start();
        statusBar.add(clockLabel, BorderLayout.EAST);

        return statusBar;
    }

    private void updateClock() {
        LocalDateTime now = LocalDateTime.now();
        clockLabel.setText(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    private void addMenuItem(JMenu menu, String label, ActionListener listener) {
        JMenuItem menuItem = new JMenuItem(label);
        menuItem.setForeground(Color.BLACK);
        menuItem.addActionListener(listener);
        menu.add(menuItem);
    }

    // The following methods remain largely unchanged, just update their content as needed
    private JPanel createHomePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Welcome to EDICOMPU Cyber Management System", SwingConstants.CENTER), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createCustomersPanel() {
      return new CustomerManagementPanel();
    }

    private JPanel createCyberManagementPanel() {
        // Update this method to create a more detailed cyber management panel
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Cyber Management", SwingConstants.CENTER), BorderLayout.NORTH);
        // Add more components for cyber management
        return panel;
    }

    private JPanel createComputerRentalPanel() {
        // Update this method to create a more detailed computer rental panel
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Computer Rental", SwingConstants.CENTER), BorderLayout.NORTH);
        // Add more components for computer rental
        return panel;
    }

    private JPanel createPaymentsPanel() {
        // Update this method to create a more detailed payments panel
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Payments", SwingConstants.CENTER), BorderLayout.NORTH);
        // Add more components for payments management
        return panel;
    }

    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to logout?", "Logout Confirmation",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            new LoginScreen().setVisible(true);
            dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new MainMenu().setVisible(true);
        });
    }
}