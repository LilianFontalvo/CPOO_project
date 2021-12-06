package core.models;

import core.weatherModels.CloudCover;
import core.weatherModels.Sunlight;

/**
 * SolarPlant est une classe modélisant la consommation/production électrique
 * d'une centrale solaire. Le modèle considère une centrale de 28.000 mètres 
 * carrés.
 * @author Lilian Fontalvo & q.bertrand
 * @version 1.0
 */
public class SolarPlant extends Producer {

    /**
     * Ratio d'énergie produite sur énergie émise par le Soleil
     */
    private final static double rendement = 0.2 ; 

    public SolarPlant() {
    }

    public double getPowerConsMin(int min, int day) {
        return 0;
    }

    /**
     * @param min/day les paramètres logiques de jour et de minute
     * @return La production électrique avec 28.000 m² de panneaux solaires sur la minute choisie
     */
    public double getPowerProdMin(int min, int day) {
        Sunlight solarPlant = new Sunlight (min, day, 28000); // On choisit une centrale de 28.000 m².
        CloudCover cloud = new CloudCover(min, day);
        return (rendement * solarPlant.getAveragePowerProductionOfTheMinute()*(1-cloud.getAverageRatioOfCloudCoverOfTheMinute())) ;
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

    public double getRendement () {
        return rendement ;
    }
}
