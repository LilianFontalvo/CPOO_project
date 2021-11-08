package core.simulation;

import javax.swing.JFrame;

import ptolemy.plot.Plot;

/**
 * Simulation est un classe permettant la simulation et l'affichage de la
 * consommation/production électrique d'un ensemble de points d'intérêt sur 1
 * jour ou sur 1 an.
 * 
 * @author Lilian Fontalvo
 * @version 1.0
 */

public class Simulation {

    /**
     * Liste de Points inclus dans la simulation
     * 
     * @see Point
     */
    private Cluster[] clusters;

    private int nbClusters;

    /**
     * Nombre de Point dans la liste de Points "points".
     */
    private int[] nbPoints;


    /**
     * Constructeur de simulation à partir d'une liste de points. Initialise
     * nbPoints à partir de la liste "points" fournie en paramètre.
     * 
     * @param points Liste de points d'intérêt à intégrer dans la simulation
     */
    public Simulation(Cluster[] clusters) {
        this.clusters = clusters;
        this.nbClusters = 0;
        for (Cluster cluster : clusters) {
            this.nbClusters++;
        }
        this.nbPoints = new int[nbClusters];
        int i = 0;
        for (Cluster cluster : clusters) {
            int j = 0;
            for (Point point : cluster.getPoints()) {
                point.getName();
                j++;
            }
            nbPoints[i] = j;
            i++;
        }
    }

    /**
     * Affiche une ligne du bilan au format CSV.
     * 
     * @param i         Entier représentant la minute ou le jour
     * @param powerCons La puissance moyenne consommée en W
     * @param powerProd La puissance moyenne produite en W
     * @param cumulCons L'énergie consommée cumulée en Wh
     * @param cumulProd L'énergie produite cumulée en Wh
     */
    private void printLine(int i, double powerCons, double powerProd, double cumulCons, double cumulProd) {
        System.out.println(i + ";" + powerCons + ";" + powerProd + ";" + cumulCons + ";" + cumulProd);
    }

    /**
     * Permet de recevoir la puissance électrique consommée d'un certain point
     * d'intérêt
     * 
     * @param k   Entier renvoyant au kième élément de la liste "points"
     * @param min Entier représentant une minutes de la journée (entre 0 et 1439)
     * @param day Entier représentant un jour de l'année (entre 0 et 364)
     * @return La puissance électrique consommée à la minute et au jour donnés de
     *         points[k]
     */
    private double getPowerConsMin(int c, int p, int min, int day) {
        return clusters[c].getPoints()[p].getModel().getPowerConsMin(min, day);
    }

    /**
     * Permet de recevoir la puissance électrique consommée d'un certain cluster
     * 
     * @param c   Entier renvoyant au kième élément de la liste "clusters"
     * @param min Entier représentant une minutes de la journée (entre 0 et 1439)
     * @param day Entier représentant un jour de l'année (entre 0 et 364)
     * @return La puissance électrique consommée à la minute et au jour donnés de
     *         clusters[c]
     */
    private double getPowerConsMin(int c, int min, int day) {
        double cons = 0;
        for (Point point : clusters[c].getPoints()) {
            cons += point.getModel().getPowerConsMin(min, day);
        }
        return cons;
    }

    /**
     * Permet de recevoir la puissance électrique produite d'un certain point
     * d'intérêt
     * 
     * @param k   Entier renvoyant au kième élément de la liste "points"
     * @param min Entier représentant une minutes de la journée (entre 0 et 1439)
     * @param day Entier représentant un jour de l'année (entre 0 et 364)
     * @return La puissance électrique produite à la minute et au jour donnés de
     *         points[k]
     */
    private double getPowerProdMin(int c, int p, int min, int day) {
        return clusters[c].getPoints()[p].getModel().getPowerProdMin(min, day);
    }

    /**
     * Permet de recevoir la puissance électrique produite d'un certain cluster
     * 
     * @param c   Entier renvoyant au kième élément de la liste "clusters"
     * @param min Entier représentant une minutes de la journée (entre 0 et 1439)
     * @param day Entier représentant un jour de l'année (entre 0 et 364)
     * @return La puissance électrique produite à la minute et au jour donnés de
     *         clusters[c]
     */
    private double getPowerProdMin(int c, int min, int day) {
        double prod = 0;
        for (Point point : clusters[c].getPoints()) {
            prod += point.getModel().getPowerProdMin(min, day);
        }
        return prod;
    }

    /**
     * Calcule l'enrgie totale consommée dans la journée
     * 
     * @param k   Entier renvoyant au kième élément de la liste "points"
     * @param day Entier représentant un jour de l'année (entre 0 et 364)
     * @return L'énergie cumulée consommée en Wh dansla journée k
     * 
     * @see getCumulMin
     */
    private double getConsDay(int c, int p, int day) {
        return clusters[c].getPoints()[p].getModel().getConsDay(day);
    }

    /**
     * Permet de recevoir la puissance électrique consommée d'un certain point
     * d'intérêt
     * 
     * @param c   Entier renvoyant au kième élément de la liste "points"
     * @param min Entier représentant une minutes de la journée (entre 0 et 1439)
     * @param day Entier représentant un jour de l'année (entre 0 et 364)
     * @return La puissance électrique consommée à la minute et au jour donnés de
     *         points[k]
     */
    private double getConsDay(int c, int day) {
        double cons = 0;
        for (Point point : clusters[c].getPoints()) {
            cons += point.getModel().getConsDay(day);
        }
        return cons;
    }

    /**
     * Calcule l'enrgie totale produite dans la journée
     * 
     * @param k   Entier renvoyant au kième élément de la liste "points"
     * @param day Entier représentant un jour de l'année (entre 0 et 364)
     * @return L'énergie cumulée produite en Wh dansla journée k
     * 
     * @see getCumulMin
     */
    private double getProdDay(int c, int p, int day) {
        return clusters[c].getPoints()[p].getModel().getProdDay(day);
    }

    /**
     * Permet de recevoir la puissance électrique produite d'un certain cluster
     * 
     * @param c   Entier renvoyant au kième élément de la liste "clusters"
     * @param min Entier représentant une minutes de la journée (entre 0 et 1439)
     * @param day Entier représentant un jour de l'année (entre 0 et 364)
     * @return La puissance électrique produite à la minute et au jour donnés de
     *         clusters[k]
     */
    private double getProdDay(int c, int day) {
        double prod = 0;
        for (Point point : clusters[c].getPoints()) {
            prod += point.getModel().getProdDay(day);
        }
        return prod;
    }

    /**
     * Simule et affiche dans la console la production/consommation de tous les
     * points d'intérêt de la liste "points" à chaque minute selon le format:
     * "minute; puissance instantannée consommée; puissance instantannée produite;
     * énergie cumulée consommée; énergie cumulée produite", respectivement en W et
     * en Wh
     *  
     * @param day Entier représentant un jour de l'année (entre 0 et 364)
     * @param graph Booléen pour afficher ou non un graphique des données
     */
    public void simOneDay(int day, boolean graph) {
        double powerCons;
        double powerProd;
        double cumulCons = 0;
        double cumulProd = 0;
        Plot plot = new Plot();
        for (int i = 0; i < 1440; i++) {
            powerCons = 0;
            powerProd = 0;
            for (int k = 0; k < nbClusters; k++) { 
                powerCons += getPowerConsMin(k, i, day);
                powerProd += getPowerProdMin(k, i, day);
            }
            if (graph){
                plot.addPoint(0, i, powerCons, true);
                plot.addPoint(1, i, powerProd, true);
            }
            cumulCons += powerCons; // en Wmin, or on veut en Wh, d'où le /60
            cumulProd += powerProd; // en Wmin, or on veut en Wh, d'où le /60
            printLine(i, powerCons, powerProd, cumulCons/60, cumulProd/60);
        }
        if (graph){
            JFrame frame = new JFrame("Consumption and production over day "+day);
            frame.add(plot);
            frame.pack();
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }

    /**
     * Simule et affiche dans la console la production/consommation de tous les
     * points d'intérêt de la liste "points" pour chaque jour selon le format:
     * "jour; puissance moyenne consommée; puissance moyenne produite; énergie
     * cumulée consommée; énergie cumulée produite", respectivement en W et en Wh
     * 
     * @param graph Booléen pour afficher ou non un graphique des données
     */
    public void simOneYear(boolean graph) {
        double dailyCons;
        double dailyProd;
        double cumulCons = 0;
        double cumulProd = 0;
        Plot plot = new Plot();
        for (int i = 0; i < 365; i++) {
            dailyCons = 0;
            dailyProd = 0;
            for (int k = 0; k < nbClusters; k++) {
                dailyCons += getConsDay(k, i); // en Wh, or on veut des W, d'où le /24
                dailyProd += getProdDay(k, i); // en Wh, or on veut des W, d'où le /24
            }
            cumulCons += dailyCons;
            cumulProd += dailyProd;
            if (graph){
                plot.addPoint(0, i, dailyCons, true);
                plot.addPoint(1, i, dailyProd, true);
            }
            printLine(i, dailyCons / 24, dailyProd / 24, cumulCons, cumulProd);
        }
        if (graph){
            JFrame frame = new JFrame("Consumption and production over a year");
            frame.add(plot);
            frame.pack();
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }

}
