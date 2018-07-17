package pa.Models;

/**
 * Classe Alarm representant une alarme pouvant être ajoutée au systeme
 *
 * Une alarme est définie par les caractéristiques suivantes :
 * <ul>
 *     <li>serialNumber [String] : Le numéro de série de l'appareil</li>
 *     <li>brand [String] : La marque de l'appareil </li>
 *     <li>schedule [Schedule] : Le planning de l'appareil </li>
 * </ul>
 *
 * @author Robin Tersou
 * @since 1.0
 */
public class Alarm extends SecuritySystem {

    /**
     * Constructeur de l'objet Alarm
     *
     * @param serialNumber
     *                      Numéro de série de l'appareil
     * @param brand
     *                      Marque de l'appareil
     * @param schedule
     *                      Planning attribué
     */
    public void Alarm( String serialNumber, String brand, Schedule schedule ) {
        this.serialNumber = serialNumber;
        this.brand = brand;
        this.schedule = schedule;
    }

}
