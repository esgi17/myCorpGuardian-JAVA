package pa.Models;

public class State {

    /**
     * L'ID du schedule
     */
    private String _id;

    /**
     * Le jour schedule
     */
    private String _state;
    /**
     * Constructeur de l'objet Schedule
     *
     * @param schedule
     *                  Tableau contenant les propriétés du planning
     */
    public void Schedule(String id, String state) {
        this._id = id;
        this._state = state;
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
     * Getter : Récupération de l'ID
     * @return L'ID du groupe
     */
    public String getState() {
        return _state;
    }
    /**
     * Setter : Modification de l'ID
     * @param title
     *                  L'ID du groupe
     */
    public void setState(String state) {
        this._state = state;
    }

}
