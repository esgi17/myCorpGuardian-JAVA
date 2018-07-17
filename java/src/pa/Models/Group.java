package pa.Models;

public class Group {
    /**
     * L'ID du groupe
     */
    private String _id;

    /**
     * Le nom du groupe
     */
    private String _name;

    /**
     * Les users du groupe
     */
    private User[] _users;

    /**
     * Constructeur de l'objet groupe
     *
     * @param name
     *                  Nom du groupe
     */
    public void Group( String id, String name, User[] users) {
        this._name = name;
        this._id = id;
        this._users = users;
    }

    /**
     * Getter : Récupération de l'ID
     * @return L'ID du groupe
     */
    public String getId() {
        return _id;
    }
    /**
     * Setter : Modification de l'ID
     * @param id
     *                  L'ID du groupe
     */
    public void setId(String id) {
        this._id = id;
    }

    /**
     * Getter : Récupération des users
     * @return Le nom des users du groupe
     */
    public User[] getUsers() {
        return _users;
    }

    /**
     * Setter : Modification des users
     *
     * @param name
     *                  Nom des users du groupe
     */
    public void setUsers(User[] users) {
        this._users = users;
    }

    /**
     * Getter : Récupération du nom
     * @return Le nom du groupe
     */
    public String getName() {
        return _name;
    }

    /**
     * Setter : Modification du nom
     *
     * @param name
     *                  Nom du groupe
     */
    public void setName(String name) {
        this._name = name;
    }

}
