package pa.Models;

/**
 * Classe Pass représentant un badge associé à un utilisateur
 *
 * Un Pass a les caractéristiques suivantes :
 * <ul>
 *     <li> idPass : Identifiant du pass </li>
 *     <li> idUser : Identifiant du User </li>
 * </ul>
 *
 * @author Robin Tersou
 * @version 1.0
 */
public class Pass {
    /**
     * L'identifiant du Pass
     */
    private String _id;

    /**
     * L'identifiant du User
     */
    private String _idUser;

    /**
     * L'id de la device
     */
    private String _idDevice;

    /**
     * Constructeur de l'objet Pass
     * @param idPass
     *              L'id du pass
     * @param idUser
     *              L'id du User affecté
     */
    public void Pass(String id, String idUser, String idDevice) {
        this._id = id;
        this._idUser = idUser;
        this._idDevice = idDevice;
    }

    /**
     * Getter : Récupération de l'id du pass
     * @return L'id du Pass
     */
    public String getId() {
        return _id;
    }

    /**
     * Setter : Affectation de l'id du Pass
     * @param idPass L'id du Pass
     */
    public void setId(String id) {
        this._id = id;
    }

    /**
     * Getter : Récupération de l'id du User
     * @return L'id du User
     */
    public String getIdUser() {
        return _idUser;
    }

    /**
     * Setter : Affectation de l'id du User
     * @param idUser L'id du User
     */
    public void setIdUser(String idUser) {
        this._idUser = idUser;
    }

    /**
     * Getter : Récupération de l'id du User
     * @return L'id du User
     */
    public String getIdDevice() {
        return _idDevice;
    }

    /**
     * Setter : Affectation de l'id du User
     * @param idDevice L'id du User
     */
    public void setIdDevice(String idDevice) {
        this._idDevice = idDevice;
    }
}
