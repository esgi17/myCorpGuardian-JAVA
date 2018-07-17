package pa.Models;

/**
 * SystemType est une énumération.
 *
 * Il existe différents types d'equipements de sécurité dans l'application :
 * <ul>
 *     <li> Alarme </li>
 *     <li> Camera </li>
 *     <li> Capteur </li>
 *     <li> Porte </li>
 * </ul>
 *
 * @author Robin Tersou
 * @version 1.0
 */
public enum SystemType {
    /**
     * L'énumération des niveaux
     *
     * @since 1.0
     */
    ALARM("Alarme", 0),
    CAMERA("Camera", 1),
    SENSOR("Capteur", 2),
    DOOR("Porte", 3);

    /**
     * Le nom du type
     */
    private String _typeName;

    /**
     * L'id du type
     */
    private Integer _id;

    /**
     * Constructeur SystemType
     *
     * @param type
     *              Le nom du type
     * @param i
     *              L'id du type
     */
    SystemType(String type, int i) {
        this._typeName = type;
        this._id = i;
    }

    /**
     * Retourne le nom du type
     *
     * @return le nom du type sous forme de chaine de caractères
     */
    public String get_typeName() {
        return _typeName;
    }

    /**
     * Retourne l'id du type
     *
     * @return l'id du type sous la forme d'un entier
     */
    public Integer get_id() {
        return _id;
    }
}
