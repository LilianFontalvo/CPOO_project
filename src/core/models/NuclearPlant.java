package core.models;

/**
 * NuclearPlant est une classe modélisant la consommation/production électrique
 * d'une centrale nucléaire. Le modèle produit 1 000 MW tout au long de la
 * journée. Le modèle ne consomme rien.
 * 
 * @author Lilian Fontalvo
 * @version 1.1
 */
public class NuclearPlant extends Producer {

    public NuclearPlant() {
    }

    public double getPowerConsMin(int min, int day) {
        return 0;
    }

    public double getPowerProdMin(int min, int day) {
        return 1000e6;
    }
     
    public double getConsDay(int day) {
        return 0;
    }

    public double getProdDay(int day) {
        return 24000e6;
    }

    /**
     * Renvoie le nom du modèle permettant ainsi de pouvoir l'identifier à
     * posteriori (si unicité)
     * 
     * @returns Le nom du modèle ("nuclearPlant")
     */
    public String toString() {
        return "nuclearPlant";
    }
}
