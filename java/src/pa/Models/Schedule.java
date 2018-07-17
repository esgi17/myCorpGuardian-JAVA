package pa.Models;

/**
 * <b> Classe Schedule contenant la configuration du planning </b>
 *
 * Cette classe construit un planning par défaut si le format du tableau donné n'est pas bon
 *
 * @author Robin Tersou
 * @version 1.0
 */
public class Schedule {

    /**
     * L'ID du schedule
     */
    private String _id;

    /**
     * L'ID du schedule
     */
    private String _h_start;

    /**
     * L'ID du schedule
     */
    private String _h_stop;

    /**
     * L'ID du groupe
     */
    private String _group_id;

    /**
     * L'ID de la device
     */
    private String _door_id;

    /**
     * Constructeur de l'objet Schedule
     *
     * @param schedule
     *                  Tableau contenant les propriétés du planning
     */
    public void Schedule(String id, String h_start, String h_stop, String group_id, String door_id) {
        this._id = id;
        this._door_id = door_id;
        this._group_id = group_id;
        this._h_start = h_start;
        this._h_stop = h_stop;
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
    public String getHStart() {
        return _h_start;
    }
    /**
     * Setter : Modification de l'ID
     * @param h_start
     *                  L'ID du groupe
     */
    public void setHStart(String h_start) {
        this._h_start = h_start;
    }

    /**
     * Getter : Récupération de l'ID
     * @return L'ID du groupe
     */
    public String getHStop() {
        return _h_stop;
    }
    /**
     * Setter : Modification de l'ID
     * @param h_stop
     *                  L'ID du groupe
     */
    public void setHStop(String h_stop) {
        this._h_stop = h_stop;
    }
    /**
     * Getter : Récupération de l'ID
     * @return L'ID du groupe
     */
    public String getGroupId() {
        return _group_id;
    }
    /**
     * Setter : Modification de l'ID
     * @param group_id
     *                  L'ID du groupe
     */
    public void setGroupId(String group_id) {
        this._group_id = group_id;
    }

    /**
     * Getter : Récupération de l'ID
     * @return L'ID du groupe
     */
    public String getDoorId() {
        return _door_id;
    }
    /**
     * Setter : Modification de l'ID
     * @param door_id
     *                  L'ID du groupe
     */
    public void setDoorId(String door_id) {
        this._door_id = door_id;
    }
}
