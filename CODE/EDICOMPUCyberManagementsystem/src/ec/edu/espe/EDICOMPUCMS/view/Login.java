package ec.edu.espe.EDICOMPUCMS.view;
/**
 *
 * @author Sjean
 */
import ec.edu.espe.EDICOMPUCMS.model.Users;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Login {
    
    private Users[ ] users;

    public Login(Users[] users) {
        this.users = users;
    }
    
    public boolean authenticate(String username, String password){
        for(Users user : users){
            if( user.getUsername().equals(username)&& user.getPassword().equals(password)){
                return true;
            }
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
        
        if (option ==JOptionPane.OK_OPTION){
            String username = usernameField.getText();
            char [ ] passwordArray = passwordField.getPassword();
            String password =  new String(passwordArray);
            
            if(authenticate(username, password)){
                JOptionPane.showMessageDialog(null, "login successful");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect username or password");
                return false;
            }
        } else{
            JOptionPane.showMessageDialog(null, "login cancelled");
            return false;
        }
    }
}
