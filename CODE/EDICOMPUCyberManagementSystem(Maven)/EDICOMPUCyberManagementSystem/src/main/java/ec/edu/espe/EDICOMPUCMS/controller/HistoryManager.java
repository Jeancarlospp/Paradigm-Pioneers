
package ec.edu.espe.EDICOMPUCMS.controller;

import ec.edu.espe.EDICOMPUCMS.model.Computer;
import ec.edu.espe.EDICOMPUCMS.model.History;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leidy Saraguro,Paradigm Pioneers Squad, DCCO-ESPE
 */
public class HistoryManager {
    private List<History> history = new ArrayList<>();

    public void addHistoryEntry(Computer computer, double cost) {
        History entry = new History(computer.getId(), computer.getStartTime(), computer.getEndTime(), cost);
        history.add(entry);
    }

    public List<History> getHistory() {
        return new ArrayList<>(history);
    }
}
