package pa.Models;

/**
 * Classe Sensor representant un capteur pouvant être ajoutée au systeme
 *
 * Un capteur est défini par les caractéristiques suivantes :
 * <ul>
 *     <li>serialNumber [String] : Le numéro de série de l'appareil</li>
 *     <li>brand [String] : La marque de l'appareil </li>
 *     <li>schedule [Schedule] : Le planning de l'appareil </li>
 * </ul>
 *
 * @author Robin Tersou
 * @since 1.0
 */
public class Sensor extends SecuritySystem {

    /**
     * Constructeur de l'objet Sensor
     *
     * @param serialNumber
     *                      Numéro de série de l'appareil
     * @param brand
     *                      Marque de l'appareil
     * @param schedule
     *                      Planning attribué
     */
    public void Sensor( String serialNumber, String brand, Schedule schedule ) {
        this.serialNumber = serialNumber;
        this.brand = brand;
        this.schedule = schedule;
    }

}
