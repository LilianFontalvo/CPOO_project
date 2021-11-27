/*
Classe définissant les clusters autonomes.
Ils ont un unique producteur
*/

package core.simulation;

public class ClusterAvecProd extends Cluster {

    private Point[] consommateurs;  // Attribut : le producteur du cluster
    private Point producteur;       // Attribut : le producteur du cluster

    /**
     * Construit un nouveau cluster avec production
     * 
     * @param name          nom de la ville
     * @param points        Points du cluster consommateurs
     * @param producteur    Points du cluster producteur
     * @param x             Abscisse du cluster
     * @param y             Ordonnée du cluster
     * 
     */
    public ClusterAvecProd(String name, Point[] consommateurs, double x, double y, Point producteur) {
        super(name, null, x, y);
        this.consommateurs = consommateurs;
        this.producteur = producteur;
        int nb = 0;
        for (Point conso : consommateurs) {
            nb++;
            conso.getName();
        }
        Point[] points = new Point[nb+1];
        for (int i = 0; i<nb; i++){
            points[i] = consommateurs[i];
        }
        points[nb] = producteur;
        super.setPoints(points);
    }

    /**
     * @return le surplus de puissance produite par le cluster. (en W)
     */
    public double SurplusPuissMin(int min, int day) {
        double consommation = 0;
        for (Point point : points) {
            consommation += point.getPowerConsMin(min, day);
        }
        return (producteur.getPowerProdMin(min, day) - consommation);
    }

      /**
     * 
     * @return La puissance que consomme en plus le cluster, due aux pertes du
     *         chemin d'approvisionnement en énergie
     */
    public double getPowerPertesMin() {
        return 0;
    }

}
