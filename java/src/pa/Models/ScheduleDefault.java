package pa.Models;

/**
 * Enumeration des valeurs par défaut des plannings
 *
 * <ul>
 *     <li> ON : 1 (Etat de marche)</li>
 *     <li> OFF : 0 (Etat d'arret)</li>
 *     <li> H_START : 6 (Heure de début (format 24h) )</li>
 *     <li> H_END : 22 (Heure de fin (format 24h) )</li>
 * </ul>
 */
public enum ScheduleDefault {
    /**
     * Enumérations
     */
    ON(1, "On"),
    OFF(0, "Off"),
    H_START(6, "Hour_start"),
    H_END(22, "Hour_end"),
    MON(0, "Monday"),
    TUE(1, "Tuesday"),
    WED(2, "Wednesday"),
    THU(3, "Thursday"),
    FRI(4, "Friday"),
    SAT(5, "Saturday"),
    SUN(6, "Sunday");

    /**
     * Valeur
     */
    private int _value;

    /**
     * Libelle
     */
    private String _libelle;

    /**
     * Constructeur
     * @param i
     *          Valeur à attribuer
     */
    ScheduleDefault(int i, String libelle) {
        this._value = i;
        this._libelle = libelle;
    }

    /**
     * Récupération de la valeur
     * @return La valeur
     */
    public int get_value() {
        return this._value;
    }

    /**
     * Récupération du libelle
     * @return Le libelle
     */
    public String get_libelle() {
        return _libelle;
    }
}
