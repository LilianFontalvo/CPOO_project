package core.models;

/**
 * Factory est une classe modélisant la consommation/production électrique d'une
 * usine. Le modèle consomme 10 000 W entre 8h et 18H, et 400 W en dehors de
 * cette plage horaire. Le modèle ne produit rien.
 * 
 * @author Lilian Fontalvo
 * @version 1.0
 */
public class Factory extends Consumer {

    public Factory() {
    }

    public double getPowerConsMin(int min, int day) {
        if (min <= 60 * 8)
            return 400;
        if ((60 * 8 < min) && (min <= 60 * 18))
            return 10000;
        else
            return 400;
    }

    public double getPowerProdMin(int min, int day) {
        return 0;
    }

    /**
     * Renvoie le nom du modèle permettant ainsi de pouvoir l'identifier à
     * posteriori (si unicité)
     * 
     * @returns Le nom du modèle ("factory")
     */
    public String toString() {
        return "factory";
    }
}
