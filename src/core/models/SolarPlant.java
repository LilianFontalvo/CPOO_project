package core.models;

/**
 * SolarPlant est une classe modélisant la consommation/production électrique
 * d'une centrale solaire. Le modèle produit 70 000 kW de 7h à 17h et rien en
 * dehors de cette plage horaire. Le modèle ne consomme rien.
 * 
 * @author Lilian Fontalvo
 * @version 1.0
 */
public class SolarPlant extends Producer {

    public SolarPlant() {
    }

    public double getPowerConsMin(int min, int day) {
        return 0;
    }

    public double getPowerProdMin(int min, int day) {
        if (min <= 60 * 7)
            return 0;
        if ((60 * 7 < min) && (min <= 60 * 17))
            return 70000;
        else
            return 0;
    }

    /**
     * Renvoie le nom du modèle permettant ainsi de pouvoir l'identifier à
     * posteriori (si unicité)
     * 
     * @returns Le nom du modèle ("solarPlant")
     */
    public String toString() {
        return "solarPlant";
    }
}
