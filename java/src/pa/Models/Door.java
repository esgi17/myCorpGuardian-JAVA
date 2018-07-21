package pa.Models;

/**
 * Classe Door representant une porte pouvant être ajoutée au systeme
 *
 * Une porte est définie par les caractéristiques suivantes :
 * <ul>
 *     <li>serialNumber [String] : Le numéro de série de l'appareil</li>
 *     <li>brand [String] : La marque de l'appareil </li>
 *     <li>schedule [Schedule] : Le planning de l'appareil </li>
 * </ul>
 *
 * @author Robin Tersou
 * @since 1.0
 */
public class Door extends SecuritySystem {

    /**
     * L'ID de la porte
     */
    private String _id;

    /**
     * L'ID de la device
     */
    private String _device_id;

    /**
     * Constructeur de l'objet Door
     *
     * @param id
     *                      id de la porte
     * @param device_id
     *                      id de la device
     */
    public void Door( String id,String device_id ) {
        this._id = id;
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
    public String getDeviceId() {
        return _device_id;
    }
    /**
     * Setter : Modification de l'ID
     * @param id
     *                  L'ID de la device
     */
    public void setDeviceId(String device_id) {
        this._device_id = device_id;
    }

}
