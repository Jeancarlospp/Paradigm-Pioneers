
package ec.edu.espe.EDICOMPUCMS.model;

public class Computer {
    private int id;
    private String status;

    public Computer(int id) {
        this.id = id;
        this.status = "Inactive";
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Computer{" + "id=" + id + ", status='" + status + '\'' + '}';
    }
}