package ec.edu.espe.EDICOMPUCMS.model;

public class ComputerManager {
    private Computer[] computers;

    public ComputerManager(int numComputers) {
        computers = new Computer[numComputers];
        for (int i = 0; i < numComputers; i++) {
            computers[i] = new Computer(i + 1);
        }
    }

    public Computer[] getComputers() {
        return computers;
    }

    public Computer getComputer(int id) {
        if (id > 0 && id <= computers.length) {
            return computers[id - 1];
        } else {
            return null;
        }
    }

    public void activateComputer(int id) {
        Computer computer = getComputer(id);
        if (computer != null && computer.getStatus().equals("Inactive")) {
            computer.setStatus("Active");
            System.out.println("Computer " + id + " is now Active.");
        } else {
            System.out.println("Invalid operation. Computer is already Active or does not exist.");
        }
    }

    public void deactivateComputer(int id) {
        Computer computer = getComputer(id);
        if (computer != null && computer.getStatus().equals("Active")) {
            computer.setStatus("Inactive");
            System.out.println("Computer " + id + " is now Inactive.");
        } else {
            System.out.println("Invalid operation. Computer is already Inactive or does not exist.");
        }
    }

    public void showComputerStatus() {
        for (Computer computer : computers) {
            System.out.println(computer);
        }
    }
}