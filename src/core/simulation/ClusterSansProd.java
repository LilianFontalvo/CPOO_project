/*
Classe définissant les clusters uniquement consommateur.
Ils n'ont pas de producteurs
*/

package core.simulation;

public class ClusterSansProd extends Cluster {

    /**
     * Construit un nouveau cluster avec production
     * 
     * @param name          nom de la ville
     * @param consommateurs Points du cluster consommateurs
     * @param x             Abscisse du cluster
     * @param y             Ordonnée du cluster
     * 
     */
    public ClusterSansProd(String name, Point[] consommateurs, double x, double y) {
        super(name, consommateurs, x, y);
    }

    private Chemin chemin_Approvisionnement; // Chemin d'approvisionnement du cluster

    /**
     * 
     * @return La puissance que consomme en plus le cluster, due aux pertes du
     *         chemin d'approvisionnement en énergie
     */
    public double getPowerPertesMin() {
        return chemin_Approvisionnement.pertesChemin();
    }

}
