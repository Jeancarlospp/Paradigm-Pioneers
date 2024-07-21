package ec.edu.espe.EDICOMPUCMS.view;


public class EDICOMPUCyberManagementSystem {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WelcomeScreen().setVisible(true);
            }
        });
    }
}
