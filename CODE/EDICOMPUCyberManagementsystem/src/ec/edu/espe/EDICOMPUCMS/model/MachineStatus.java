
package ec.edu.espe.EDICOMPUCMS.model;

public class MachineStatus {
    private Computer computer;
    private String status;
    private String message;

    public MachineStatus(Computer computer, String status, String message) {
        this.computer = computer;
        this.status = status;
        this.message = message;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MachineStatus{" + "computer=" + computer + ", status=" + status + ", message=" + message + '}';
    }
    
}
