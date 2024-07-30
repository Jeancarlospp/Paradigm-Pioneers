package ec.edu.espe.EDICOMPUCMS.view;
import javax.swing.*;
import java.awt.*;

public class WelcomeScreen extends JFrame {
    public WelcomeScreen() {
        setTitle("Welcome");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("C:\\Users\\PACO\\OneDrive\\Universidad\\SEGUNDO SEMESTRE\\POO\\Paradigm-Pioneers\\CODE\\EDICOMPUCyberManagementSystem(Maven)\\Logotipo - EDICOMPU\\fondoA.jpg");
                Image img = icon.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Logo (unchanged)
        JLabel logoLabel = new JLabel();
        ImageIcon logoIcon = new ImageIcon("C:\\Users\\PACO\\OneDrive\\Universidad\\SEGUNDO SEMESTRE\\POO\\Paradigm-Pioneers\\CODE\\EDICOMPUCyberManagementSystem(Maven)\\Logotipo - EDICOMPU\\Logotipo - EDICOMPU.png");
        logoLabel.setIcon(logoIcon);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(logoLabel, gbc);

        // System Name
        JLabel systemName = new JLabel("EDICOMPU Cyber Management System");
        systemName.setFont(new Font("Georgia", Font.BOLD, 28));
        systemName.setForeground(new Color(173, 216, 230)); // Light blue color
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(systemName, gbc);

        // Group Name
        JLabel groupName = new JLabel("Developed by Paradigm Pioneers Squad");
        groupName.setFont(new Font("Calibri", Font.ITALIC, 18));
        groupName.setForeground(new Color(135, 206, 250)); // Sky blue color
        gbc.gridy = 2;
        panel.add(groupName, gbc);

        // Continue Button
        JButton continueButton = new JButton("Continue");
        continueButton.setFont(new Font("Arial", Font.BOLD, 16));
        continueButton.setForeground(new Color(25, 25, 112)); // Dark blue color
        continueButton.setBackground(new Color(173, 216, 230)); // Light blue background
        continueButton.setPreferredSize(new Dimension(150, 40));
        continueButton.setFocusPainted(false);
        continueButton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(25, 25, 112), 2),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)));
        continueButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        continueButton.addActionListener(e -> {
            new LoginScreen().setVisible(true);
            dispose();
        });
        continueButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                continueButton.setBackground(new Color(135, 206, 250)); // Lighter blue on hover
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                continueButton.setBackground(new Color(173, 216, 230)); // Back to original color
            }
        });
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        panel.add(continueButton, gbc);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WelcomeScreen().setVisible(true));
    }
}