
package ec.edu.espe.EDICOMPUCMS.controller;

import ec.edu.espe.EDICOMPUCMS.model.Tariff;

/**
 *
 * @author Leidy Saraguro,Paradigm Pioneers Squad, DCCO-ESPE
 */
public class TariffManager {
    private Tariff tariff;

    public TariffManager(double hourlyRate, double minimumCharge) {
        this.tariff = new Tariff(hourlyRate, minimumCharge);
    }

    public Tariff getTariff() {
        return tariff;
    }
}