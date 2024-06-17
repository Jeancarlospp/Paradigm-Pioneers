
package ec.edu.espe.EDICOMPUCMS.view;

import ec.edu.espe.EDICOMPUCMS.model.Login;
import static ec.edu.espe.EDICOMPUCMS.model.MainMenu.showMainMenu;
import ec.edu.espe.EDICOMPUCMS.model.Users;

public class EDICOMPUCyberManagementSystem {
    public static void main(String[ ] args){
        
        Users[] users ={
          new Users("Jeancarloooo","jean2005"),
          new Users("Andrea,","andrea2005")  
        };
        
        Login login = new Login(users);
                  if(login.showLoginMenu()){
                      showMainMenu();
                  } else{
                      System.out.println("exiting the system");
                  }     
    }  
}
