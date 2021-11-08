package core.models;

/**
 * House est une classe modélisant la consommation/production électrique d'une
 * maison. Le modèle consomme 2 400 W de 7h à 8H, 4 000 W de 12h à 13h30 et de
 * 18h à 23h. En dehors de ces plages horaires, la consommation est de 400 W. Le
 * modèle ne produit rien.
 * 
 * @author Lilian Fontalvo
 * @version 1.0
 */
public class House extends Consumer {

    public House() {
    }

    public double getPowerConsMin(int min, int day) {
        if (min <= 60 * 7)
            return 400;
        if ((60 * 7 < min) && (min <= 60 * 8))
            return 2400;
        if ((60 * 8 < min) && (min <= 60 * 12))
            return 400;
        if ((60 * 12 < min) && (min <= 60 * 13.5))
            return 4000;
        if ((60 * 13.5 < min) && (min <= 60 * 18))
            return 400;
        if ((60 * 18 < min) && (min <= 60 * 23))
            return 4000;
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
     * @returns Le nom du modèle ("house")
     */
    public String toString() {
        return "house";
    }
}
