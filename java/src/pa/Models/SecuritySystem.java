package pa.Models;

/**
 * SecuritySystem est une classe abstraite qui sera mère des modeles de tous les equipements de sécurité
 *
 * @author Robin Tersou
 * @version 1.0
 */
public abstract class SecuritySystem {

    /**
     * La tranche horaire associée à l'equipement
     */
    protected Schedule schedule;

    /**
     * La marque de l'appareil
     */
    protected String brand;

    /**
     * Le numéro de série de l'appareil
     */
    protected String serialNumber;

    /**
     * Retourne la tranche horaire associée à l'équipement
     * @return L'objet Schedule associé à l'equipement
     */
    public Schedule getSchedule() {
        return this.schedule;
    };

    /**
     * Affecte une tranche horaire à un equipement.
     * @param schedule
     *                  L'objet Schedule à affecter
     */
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    //public abstract int getSystemType();

}
