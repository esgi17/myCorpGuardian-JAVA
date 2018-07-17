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
    private int _idPass;

    /**
     * L'identifiant du User
     */
    private int _idUser;

    /**
     * Constructeur de l'objet Pass
     * @param idPass
     *              L'id du pass
     * @param idUser
     *              L'id du User affecté
     */
    public void Pass(int idPass, int idUser) {
        this._idPass = idPass;
        this._idUser = idUser;
    }

    /**
     * Getter : Récupération de l'id du pass
     * @return L'id du Pass
     */
    public int get_idPass() {
        return _idPass;
    }

    /**
     * Setter : Affectation de l'id du Pass
     * @param idPass L'id du Pass
     */
    public void set_idPass(int idPass) {
        this._idPass = _idPass;
    }

    /**
     * Getter : Récupération de l'id du User
     * @return L'id du User
     */
    public int get_idUser() {
        return _idUser;
    }

    /**
     * Setter : Affectation de l'id du User
     * @param idUser L'id du User
     */
    public void set_idUser(int idUser) {
        this._idUser = _idUser;
    }
}
