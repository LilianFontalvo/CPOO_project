package core.simulation;

import java.util.ArrayList;

/**
 * Point est une classe réprésentant un point d'intérêt dans la simulation.
 * Chaque point doit être différent mais peuvent partager un même modèle.
 * 
 * @author Lilian Fontalvo
 * @version 1.2
 */

public abstract class Point {
    /**
     * Nom du Point
     */
    protected String name;
    /**
     * Boolean qualifiant si le point est complexe ou non
     */
    protected boolean complexe;

    public Point(String name, boolean complexe) {
        this.name = name;
        this.complexe = complexe;
    }

    /**
     * Permet de recevoir la puissance consommée par le Point
     * 
     * @param min Entier représentant une minutes de la journée (entre 0 et 1439)
     * @param day Entier représentant un jour de l'année (entre 0 et 364)
     * @return La puissance consommée au jour day et à la minute min donnés
     */
    public abstract double getPowerConsMin(int min, int day);

    /**
     * Permet de recevoir la puissance produite par le Point
     * 
     * @param min Entier représentant une minutes de la journée (entre 0 et 1439)
     * @param day Entier représentant un jour de l'année (entre 0 et 364)
     * @return La puissance produite au jour day et à la minute min donnés
     */
    public abstract double getPowerProdMin(int min, int day);

    /**
     * Permet de recevoir l'énergie consommée par le Point en une journée
     * 
     * @param day Entier représentant un jour de l'année (entre 0 et 364)
     * @return L'énergie consommée au jour day
     */
    public abstract double getConsDay(int day);

      /**
     * Permet de recevoir l'énergie produite par le Point en une journée
     * 
     * @param day Entier représentant un jour de l'année (entre 0 et 364)
     * @return L'énergie produite au jour day
     */
    public abstract double getProdDay(int day);

    /**
     * 
     * @return Le nom du Point
     */
    public String getName() {
        return this.name;
    }

    /**
     * 
     * @return Le boolean qualifiant si le point est complexe ou non
     */
    public boolean isComplexe() {
        return this.complexe;
    }

    /**
     * @return Une ArrayList des points composants du point si le point et complexe,
     *         lui même sinon
     */
    // by Marta
    public ArrayList<PointSingulier> getAllPoints() {
        return getPointsBody(this);
    }

    private ArrayList<PointSingulier> getPointsBody(Point point) {
        ArrayList<PointSingulier> pointsToReturn = new ArrayList<>();
        if (point.isComplexe()) {
            for (Point p : ((PointComplexe) point).getPoints()) {
                pointsToReturn.addAll(this.getPointsBody(p));
            }
        } else {
            pointsToReturn.add((PointSingulier) point);
        }
        return pointsToReturn;
    }

}
