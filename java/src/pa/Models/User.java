package pa.Models;

/**
 * <b>User est la classe représentant un utilisateur.</b>
 *
 *     Un User est un utilisateur des systèmes de sécurité mis en place (employé, visiteur, administrateur).
 *     Un User est caractérisé par les informations suivantes :
 *     <ul>
 *         <li> Un nom de famille </li>
 *         <li> Un prénom </li>
 *         <li> Une date de naissance </li>
 *         <li> L'id de son groupe </li>
 *     </ul>
 *
 * @author Robin Tersou
 * @version 1.0
 *
 */
public class User {
    /**
     * L'ID du "User"
     */
    private String _id;

    /**
     * Le prénom du "User"
     */
    private String _firstname;

    /**
     * Le nom du "User"
     */
    private String _lastname;

    /**
     * Le job du "User"
     */
    private String _job;

    /**
     * L'id du groupe attribué au "User"
     */
    private String _idGroup;


    /**
     * Constructeur de l'objet User
     *
     * @param firstname
     *                  Prénom du User
     * @param lastname
     *                  Nom de famille du User
     * @param idGroup
     *                  Id du groupe du User
     */
    public void User( String id, String firstname, String lastname, String job, String idGroup) {
        this._firstname = firstname;
        this._lastname = lastname;
        this._id = id;
        this._job = job;

        // Attribution du groupe par défaut si idGroup null
        if( idGroup == null ) {
            this._idGroup = "0";
        } else {
            this._idGroup = idGroup;
        }
    }

    /**
     * Getter : Récupération de l'ID
     * @return L'ID du User
     */
    public String getId() {
        return _id;
    }
    /**
     * Setter : Modification de l'ID
     * @param id
     *                  L'ID du User
     */
    public void setId(String id) {
        this._id = id;
    }


    /**
     * Getter : Récupération du job
     * @return Le job du User
     */
    public String getJob() {
        return _job;
    }
    /**
     * Setter : Modification du job
     * @param job
     *                  Le job du User
     */
    public void setJob(String job) {
        this._job = job;
    }


    /**
     * Getter : Récupération du prénom
     * @return Le prénom du User
     */
    public String getFirstname() {
        return _firstname;
    }

    /**
     * Setter : Modification du prénom
     *
     * @param firstname
     *                  Prénom du User
     */
    public void setFirstname(String firstname) {
        this._firstname = firstname;
    }

    /**
     * Getter : Récupération du nom de famille
     *
     * @return Le nom de famille du User
     */
    public String getLastname() {
        return _lastname;
    }

    /**
     * Setter : Modification du nom de famille
     * @param lastname
     *                  Le nom de de famille du User
     */
    public void setLastname(String lastname) {
        this._lastname = lastname;
    }

    /**
     * Getter : Récpération de l'id du groupe attribué
     * @return L'id du groupe attribué au User
     */
    public String getIdGroup() {
        return _idGroup;
    }

    /**
     * Setter : Attribution d'un groupe au User
     * @param idGroup
     *                  Id du groupe
     */
    public void setIdGroup(String idGroup) {
        this._idGroup = idGroup;
    }
}
