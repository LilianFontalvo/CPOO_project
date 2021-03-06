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
    public ClusterSansProd(String name, Point[] consommateurs, double x, double y, Chemin chemin) {
        super(name, consommateurs, x, y);
        cheminApprovisionnement = chemin;
    }

    private Chemin cheminApprovisionnement; // Chemin d'approvisionnement du cluster

    /**
     * 
     * @return La puissance que consomme en plus le cluster, due aux pertes du
     *         chemin d'approvisionnement en énergie
     */
    public double getPowerPertesMin() {
        return cheminApprovisionnement.pertesChemin();
    }

    public Chemin getChemin() {
        return this.cheminApprovisionnement;
    }

    public void setChemin(Chemin cheminApprovisionnement) {
        this.cheminApprovisionnement = cheminApprovisionnement;
    }


}
