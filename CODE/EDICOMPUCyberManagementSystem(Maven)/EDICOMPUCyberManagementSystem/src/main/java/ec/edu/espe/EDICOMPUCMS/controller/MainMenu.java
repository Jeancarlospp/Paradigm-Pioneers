package ec.edu.espe.EDICOMPUCMS.controller;

import ec.edu.espe.EDICOMPUCMS.view.CustomerManagementPanel;
import ec.edu.espe.EDICOMPUCMS.view.CyberManagementPanel;
import ec.edu.espe.EDICOMPUCMS.view.LoginScreen;
import ec.edu.espe.EDICOMPUCMS.view.PaymentsPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Leidy Saraguro,Paradigm Pioneers Squad, DCCO-ESPE
 */

public class MainMenu extends JFrame {
    private CyberManager cyberManager;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JPanel sidePanel;
    private JLabel statusLabel;
    private JLabel clockLabel;

    public MainMenu() {
        cyberManager = new CyberManager();
        
        initializeFrame();
        setJMenuBar(createMenuBar());
        add(createSidePanel(), BorderLayout.WEST);
        add(createMainPanel(), BorderLayout.CENTER);
        add(createStatusBar(), BorderLayout.SOUTH);

        cardLayout.show(mainPanel, "Home");
    }

    private void initializeFrame() {
        setTitle("EDICOMPU Cyber Management System");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(26, 35, 126));

        JMenu editMenu = createMenu("Edit", Color.WHITE, "Preferences", e -> showDialog("Edit preferences"));
        JMenu viewMenu = createMenu("View", Color.WHITE, "Full Screen", e -> showDialog("Toggle full screen"));
        JMenu helpMenu = createMenu("Help", Color.WHITE, "About", e -> showDialog("About EDICOMPU CMS"));

        menuBar.add(editMenu);
        menuBar.add(viewMenu);
        menuBar.add(helpMenu);

        return menuBar;
    }

    private JMenu createMenu(String title, Color color, String itemLabel, ActionListener listener) {
        JMenu menu = new JMenu(title);
        menu.setForeground(color);
        addMenuItem(menu, itemLabel, listener);
        return menu;
    }

    private void showDialog(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    private JPanel createSidePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(new Color(26, 35, 126));

        String[] buttonLabels = {"Home", "Customers", "Cyber Management", "Payments"};
        for (String label : buttonLabels) {
            JButton button = createSideButton(label);
            button.addActionListener(e -> cardLayout.show(mainPanel, label.replace(" ", "")));
            panel.add(button);
            panel.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        JButton logoutButton = createSideButton("Logout");
        logoutButton.addActionListener(e -> logout());
        panel.add(Box.createVerticalGlue());
        panel.add(logoutButton);

        return panel;
    }

    private JButton createSideButton(String label) {
        JButton button = new JButton(label);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getMinimumSize().height));
        button.setBackground(new Color(63, 81, 181));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }

    private JPanel createMainPanel() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                paintBackgroundGradient(g);
            }
        };

        mainPanel.add(createHomePanel(), "Home");
        mainPanel.add(createCustomersPanel(), "Customers");
        mainPanel.add(createCyberManagementPanel(), "CyberManagement");
        mainPanel.add(createPaymentsPanel(), "Payments");

        return mainPanel;
    }

    private void paintBackgroundGradient(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Color color1 = new Color(245, 245, 245);
        Color color2 = new Color(230, 230, 230);
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    private JPanel createStatusBar() {
        JPanel statusBar = new JPanel(new BorderLayout());
        statusBar.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        statusBar.setBackground(new Color(245, 245, 245));

        statusLabel = new JLabel("Ready");
        statusBar.add(statusLabel, BorderLayout.WEST);

        clockLabel = new JLabel();
        startClock();
        statusBar.add(clockLabel, BorderLayout.EAST);

        return statusBar;
    }

    private void startClock() {
        updateClock();
        Timer timer = new Timer(1000, e -> updateClock());
        timer.start();
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

    private JPanel createHomePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Welcome to EDICOMPU Cyber Management System", SwingConstants.CENTER), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createCustomersPanel() {
        return new CustomerManagementPanel();
    }

    private JPanel createCyberManagementPanel() {
        return new CyberManagementPanel(cyberManager);
    }

    private JPanel createPaymentsPanel() {
        return new PaymentsPanel(cyberManager);
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
