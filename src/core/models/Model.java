package core.models;

/**
 * Model est une interface permettant de regrouper toutes les classes qui
 * peuvent modéliser la consommation/production d'un point d'intérêt.
 * 
 * @author Lilian Fontalvo
 * @version 1.2
 */

public abstract class Model {

    /**
     * Permet de recevoir la puissance électrique consommée
     * 
     * @param min Entier représentant les minutes d'une journée (entre 0 et 1439)
     * @param day Entier représentant les jours d'une année (entre 0 et 364)
     * @return La puissance électrique consommée à la minute et au jour donnés.
     */
    public abstract double getPowerConsMin(int min, int day);

    /**
     * Permet de recevoir la puissance électrique produite
     * 
     * @param min Entier représentant les minutes d'une journée (entre 0 et 1439)
     * @param day Entier représentant les jours d'une année (entre 0 et 364)
     * @return La puissance électrique produite à la minute et au jour donnés.
     */
    public abstract double getPowerProdMin(int min, int day);

    /**
     * Calcule l'énergie cumulée à partir de la puissance instantanée.
     * 
     * @param min  Entier représentant une minutes de la journée (entre 0 et 1439)
     * @param day  Entier représentant un jour de l'année (entre 0 et 364)
     * @param cons Booléen permettant de différencier le cas de consommation et
     *             celui de production
     * @return L'énergie cumulée en Wh au jour day jusqu'à la minute min.
     */
    private double getCumulMin(int min, int day, boolean cons) {
        if (min == -1)
            return 0;
        else {
            double powerMin;
            if (cons)
                powerMin = getPowerConsMin(min, day);
            else
                powerMin = getPowerProdMin(min, day);
            return getCumulMin(min - 1, day, cons) + powerMin / 60;
        }
    }
    
    /** 
     * Calcule l'enrgie totale consommée dans la journée
     * 
     * @param day Entier représentant un jour de l'année (entre 0 et 364)
     * @return L'énergie cumulée consommée en Wh dansla journée k
     * 
     * @see getCumulMin
     */
    public double getConsDay(int day) {
        return getCumulMin(1439, day, true); // 1439 correspond à la dernière minute de la journée
    }

    /**
     * Calcule l'enrgie totale produite dans la journée
     * 
     * @param day Entier représentant un jour de l'année (entre 0 et 364)
     * @return L'énergie cumulée produite en Wh dansla journée k
     * 
     * @see getCumulMin
     */
    public double getProdDay(int day) {
        return getCumulMin(1439, day, false); // 1439 correspond à la dernière minute de la journée
    }
}