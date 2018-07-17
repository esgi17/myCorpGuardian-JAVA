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
     * Le nom de la porte
     */
    private String _name;

    /**
     * La ref de la porte
     */
    private String _ref;

    /**
     * L'ID de la device
     */
    private String _device_id;

    /**
     * Constructeur de l'objet Door
     *
     * @param id
     *                      id de la porte
     * @param name
     *                      Nom de la porte
     * @param ref
     *                      Reference de la porte
     * @param device_id
     *                      id de la device
     */
    public void Door( String id, String ref, String name, String device_id ) {
        this._id = id;
        this._ref = ref;
        this._name = name;
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

    /**
     * Getter : Récupération du nom
     * @return Le nom du groupe
     */
    public String getRef() {
        return _ref;
    }

    /**
     * Setter : Modification du nom
     *
     * @param ref
     *                  Nom du groupe
     */
    public void setRef(String ref) {
        this._ref = ref;
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
