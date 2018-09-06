package pa.Models;

/**
 * Classe Camera representant une camera pouvant être ajoutée au systeme
 *
 * Une caméra est définie par les caractéristiques suivantes :
 * <ul>
 *     <li>serialNumber [String] : Le numéro de série de l'appareil</li>
 *     <li>brand [String] : La marque de l'appareil </li>
 *     <li>schedule [Schedule] : Le planning de l'appareil </li>
 * </ul>
 *
 * @author Robin Tersou
 * @since 1.0
 */
public class Camera  {
    /**
     * L'identifiant du Pass
     */
    private String _id;

    /**
     * L'identifiant du User
     */
    private String _url;

    /**
     * L'id de la device
     */
    private String _idDevice;

    /**
     * Constructeur de l'objet Pass
     * @param idPass
     *              L'id du pass
     * @param url
     *              L'id du User affecté
     */
    public void Pass(String id, String url, String idDevice) {
        this._id = id;
        this._url = url;
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
    public String getUrl() {
        return _url;
    }

    /**
     * Setter : Affectation de l'id du User
     * @param url L'id du User
     */
    public void setUrl(String url) {
        this._url = url;
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

