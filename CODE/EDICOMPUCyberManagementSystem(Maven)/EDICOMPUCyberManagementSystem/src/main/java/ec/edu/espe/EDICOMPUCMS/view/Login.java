package ec.edu.espe.EDICOMPUCMS.view;

import ec.edu.espe.EDICOMPUCMS.controller.CustomerManager;
import ec.edu.espe.EDICOMPUCMS.model.Users;
import ec.edu.espe.EDICOMPUCMS.utils.PasswordManager;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login {

    private CustomerManager customerManager;

    public Login() {
        this.customerManager = new CustomerManager();
    }

    public boolean authenticate(String username, String password) {
        Users user = customerManager.getUser(username);
        if (user != null && PasswordManager.decrypt(user.getPassword()).equals(password)) {
            return true;
        }
        return false;
    }

    public boolean showLoginMenu() {
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Username"));
        panel.add(usernameField);
        panel.add(new JLabel("Password: "));
        panel.add(passwordField);

        int option = JOptionPane.showConfirmDialog(null, panel, "Login",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            char[] passwordArray = passwordField.getPassword();
            String password = new String(passwordArray);

            if (authenticate(username, password)) {
                JOptionPane.showMessageDialog(null, "login successful");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect username or password");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "login cancelled");
            return false;
        }
    }
}
