/*
Classe définissant les clusters. 
Un cluster est localisé à un endroit d'abscisse x et d'ordonnee y.
Chaque cluster contient une liste de points, consommateurs ou récepteurs.
*/

package core.simulation;

public class Cluster extends PointComplexe {

    private double x; // Abscisse du cluster
    private double y; // Ordonnée du cluster

    /**
     * Construit un nouveau cluster
     * 
     * @param name   nom de la ville
     * @param points Points du cluster
     * @param x      Abscisse du cluster
     * @param y      Ordonnée du cluster
     * 
     */
    public Cluster(String name, Point[] points, double x, double y) {
        super(name, points);
        this.x = x;
        this.y = y;
    }

    /**
     * Construit un nouveau cluster
     * 
     * @param points Points du cluster
     * @param x      Abscisse du cluster
     * @param y      Ordonnée du cluster
     * 
     */
    public Cluster(Point[] points, double x, double y) {
        this("Cluster", points, x, y);
    }

    /**
     * Renvoie la liste des points du cluster
     */
    public Point[] getPoints() {
        return points;
    }

    /**
     * @return le nom du cluster
     */
    public String getName() {
        return name;
    }

    /**
     * @return l'abscisse du cluster
     */
    public double getX() {
        return x;
    }

    /**
     * @return l'ordonnée du cluster
     */
    public double getY() {
        return y;
    }

}
