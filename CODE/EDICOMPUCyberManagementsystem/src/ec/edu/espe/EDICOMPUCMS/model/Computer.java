
package ec.edu.espe.EDICOMPUCMS.model;

public class Computer {
    int id;
    String status;
    int timeUsage;

    public Computer(int id, String status, int timeUsage) {
        this.id = id;
        this.status = status;
        this.timeUsage = timeUsage;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTimeUsage() {
        return timeUsage;
    }

    public void setTimeUsage(int timeUsage) {
        this.timeUsage = timeUsage;
    }

    @Override
    public String toString() {
        return "Computer{id=" + id + ", status='" + status + "', timeUsage=" + timeUsage + '}';
    }
}

