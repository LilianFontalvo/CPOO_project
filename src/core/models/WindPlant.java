package core.models;

import core.weatherModels.Sunlight;

/**
 * WindPlant est une classe modélisant la consommation/production électrique
 * d'une centrale éolienne. 
 * 
 * @author q.bertrand
 * @version 1.0
 */
public class WindPlant extends Producer {

    /**
     * Ratio d'énergie produite par éolienne sur énergie du vent soufflé.
     */
    private final static double rendement = 0.3 ; 

    public WindPlant() {
    }

    public double getPowerConsMin(int min, int day) {
        return 0;
    }

    /**
     * @param min/day les paramètres logiques de jour et de minute
     * @return La production électrique avec 10.000 éoliennes sur la minute choisie
     */
    public double getPowerProdMin(int min, int day) {
        Sunlight windPlant = new Sunlight (min, day, 100); // On choisit 10.000 éoliennes.
        return (rendement * windPlant.getAveragePowerProductionOfTheMinute()) ;
    }

    /**
     * Renvoie le nom du modèle permettant ainsi de pouvoir l'identifier à
     * posteriori (si unicité)
     * 
     * @returns Le nom du modèle ("windPlant")
     */
    public String toString() {
        return "windPlant";
    }

    public double getRendement () {
        return rendement ;
    }

}