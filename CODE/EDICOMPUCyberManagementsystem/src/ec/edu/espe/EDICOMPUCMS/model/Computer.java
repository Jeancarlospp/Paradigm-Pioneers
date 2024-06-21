package ec.edu.espe.EDICOMPUCMS.model;

import java.time.Duration;
import java.time.Instant;

public class Computer {
    private int id;
    private boolean isActive;
    private Instant startTime;
    private Instant endTime;

    public Computer(int id) {
        this.id = id;
        this.isActive = false;
    }

    public int getId() {
        return id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void start() {
        this.isActive = true;
        this.startTime = Instant.now();
        this.endTime = null;
    }

    public void stop() {
        this.isActive = false;
        this.endTime = Instant.now();
    }

    public Duration getActiveDuration() {
        if (startTime == null) {
            return Duration.ZERO;
        }

        if (isActive) {
            return Duration.between(startTime, Instant.now());
        } else {
            return Duration.between(startTime, endTime);
        }
    }
<<<<<<< HEAD
public void setActive(boolean active) {
        this.isActive = active;
    }
}
=======
<<<<<<< HEAD
}
=======
    
}
>>>>>>> rama-Leidy
>>>>>>> 680da365c36fe09cfc504f88dfee0d333cc76c06
