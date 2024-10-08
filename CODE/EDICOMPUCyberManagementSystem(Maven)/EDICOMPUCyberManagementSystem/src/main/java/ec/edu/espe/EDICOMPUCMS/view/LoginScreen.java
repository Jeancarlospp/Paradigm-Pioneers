package ec.edu.espe.EDICOMPUCMS.view;

import ec.edu.espe.EDICOMPUCMS.controller.CustomerManager;
import ec.edu.espe.EDICOMPUCMS.controller.MainMenu;
import ec.edu.espe.EDICOMPUCMS.model.Users;
import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JFrame {
    private CustomerManager customerManager;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginScreen() {
        this.customerManager = new CustomerManager();

        // Configurar el JFrame
        setTitle("Login");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear panel con fondo personalizado
        JPanel panel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("C:\\Users\\KENNED\\Desktop\\PPS TP\\Paradigm-Pioneers\\CODE\\EDICOMPUCyberManagementSystem(Maven)\\Logotipo - EDICOMPU\\fondoA.jpg");
                Image img = icon.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Agregar icono de usuario
        JLabel userIcon = new JLabel(new ImageIcon("C:\\Users\\KENNED\\Desktop\\PPS TP\\Paradigm-Pioneers\\CODE\\EDICOMPUCyberManagementSystem(Maven)\\Logotipo - EDICOMPU\\Logotipo - EDICOMPU.png"));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(userIcon, gbc);

        // Campo de texto para el username
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setForeground(new Color(173, 216, 230)); // Azul claro
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(usernameLabel, gbc);

        usernameField = new JTextField(20);
        usernameField.setBackground(new Color(240, 248, 255)); // Azul muy claro
        usernameField.setBorder(BorderFactory.createLineBorder(new Color(0, 149, 255)));
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(usernameField, gbc);

        // Campo de texto para la contraseña
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(new Color(173, 216, 230)); // Azul claro
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        panel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        passwordField.setBackground(new Color(240, 248, 255)); // Azul muy claro
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(0, 149, 255)));
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(passwordField, gbc);

        // Panel de opciones (Remember me y Forgot Password)
        JPanel optionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        optionsPanel.setOpaque(false);
        JCheckBox rememberMe = new JCheckBox("Remember me");
        rememberMe.setForeground(new Color(173, 216, 230)); // Azul claro
        rememberMe.setOpaque(false);
        JLabel forgotPassword = new JLabel("Forgot Password?");
        forgotPassword.setForeground(new Color(135, 206, 250)); // Azul cielo
        forgotPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
        optionsPanel.add(rememberMe);
        optionsPanel.add(forgotPassword);
        gbc.gridy = 5;
        panel.add(optionsPanel, gbc);

        // Botón de inicio de sesión
        JButton loginButton = new JButton("LOGIN");
        loginButton.setBackground(new Color(24, 2, 64)); // Azul brillante
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        panel.add(loginButton, gbc);

        // Efecto hover para el botón de login
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(30, 144, 255)); // Azul más oscuro al pasar el mouse
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(0, 149, 255)); // Volver al color original
            }
        });

        // Acción del botón de login
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (authenticate(username, password)) {
                JOptionPane.showMessageDialog(null, "Login successful");
                new MainMenu().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect username or password", "Login Error", JOptionPane.ERROR_MESSAGE);
                passwordField.setText("");
            }
        });

        // Botón de registro
        JButton registerButton = new JButton("Register");
        registerButton.setBackground(new Color(24, 2, 64)); // Azul brillante
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        panel.add(registerButton, gbc);

        // Acción del botón de registro
        registerButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Username and password cannot be empty", "Registration Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Users newUser = new Users(username, password);
                customerManager.addUser(newUser);
                JOptionPane.showMessageDialog(null, "User registered successfully");
            }
        });

        // Agregar el panel al frame
        add(panel);
    }

    private boolean authenticate(String username, String password) {
        Users user = customerManager.getUser(username);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginScreen().setVisible(true);
        });
    }
}
