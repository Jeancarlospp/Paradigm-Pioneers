package ec.edu.espe.EDICOMPUCMS.view;

import ec.edu.espe.EDICOMPUCMS.controller.MainMenu;
import ec.edu.espe.EDICOMPUCMS.model.Users;

public class EDICOMPUCyberManagementSystem {
    public static void main(String[] args) {
        Users[] users = {
            new Users("Jeancarlo", "jean2005"),
            new Users("Andrea", "andrea2005"),
            new Users("Leidy", "leidy2005"),
            new Users("Kenned", "kenned2005")
        };

        Login login = new Login(users);
        if (login.showLoginMenu()) {
            MainMenu.showMainMenu();
        } else {
            System.out.println("Exiting the system.");
        }
    }    
}
