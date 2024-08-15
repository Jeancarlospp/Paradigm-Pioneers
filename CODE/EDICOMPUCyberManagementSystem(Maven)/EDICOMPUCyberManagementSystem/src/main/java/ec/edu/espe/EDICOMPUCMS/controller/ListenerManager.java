package ec.edu.espe.EDICOMPUCMS.controller;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrea Raura,Paradigm Pioneers Squad, DCCO-ESPE
 */
public class ListenerManager {
    private List<Runnable> historyUpdateListeners = new ArrayList<>();

    public void addHistoryUpdateListener(Runnable listener) {
        historyUpdateListeners.add(listener);
    }

    public void notifyHistoryUpdateListeners() {
        historyUpdateListeners.forEach(Runnable::run);
    }
    
}
