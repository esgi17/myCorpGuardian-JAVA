package pa.Models;

public class Event {

    /**
     * L'ID du schedule
     */
    private String _id;

    /**
     * Le jour schedule
     */
    private String _title;

    /**
     * L'ID du schedule
     */
    private String _date;

    /**
     * L'ID du groupe
     */
    private String _device_id;

    /**
     * L'ID de la device
     */
    private String _pass_id;

    /**
     * Constructeur de l'objet Schedule
     *
     * @param schedule
     *                  Tableau contenant les propriétés du planning
     */
    public void Schedule(String id, String title, String date, String pass_id, String device_id) {
        this._id = id;
        this._title = title;
        this._date = date;
        this._pass_id = pass_id;
        this._device_id = device_id;
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
    public String getTitle() {
        return _title;
    }
    /**
     * Setter : Modification de l'ID
     * @param title
     *                  L'ID du groupe
     */
    public void setTitle(String title) {
        this._title = title;
    }

    /**
     * Getter : Récupération de l'ID
     * @return L'ID du groupe
     */
    public String getDate() {
        return _date;
    }
    /**
     * Setter : Modification de l'ID
     * @param date
     *                  L'ID du groupe
     */
    public void setDate(String date) {
        this._date = date;
    }
    /**
     * Getter : Récupération de l'ID
     * @return L'ID du groupe
     */
    public String getPassId() {
        return _pass_id;
    }
    /**
     * Setter : Modification de l'ID
     * @param pass_id
     *                  L'ID du groupe
     */
    public void setPassId(String pass_id) {
        this._pass_id = pass_id;
    }

    /**
     * Getter : Récupération de l'ID
     * @return L'ID du groupe
     */
    public String getDeviceId() {
        return _device_id;
    }
    /**
     * Setter : Modification de l'ID
     * @param device_id
     *                  L'ID du groupe
     */
    public void setDeviceId(String device_id) {
        this._device_id = device_id;
    }

}
