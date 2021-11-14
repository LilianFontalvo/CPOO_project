/*
Classe définissant les clusters uniquement consommateur.
Ils n'ont pas de producteurs
*/

package core.simulation;

import core.simulation.Chemin;

public class ClusterSansProd extends Cluster {

    private Chemin chemin_Approvisionnement; // Chemin d'approvisionnement du cluster

    /**
     * 
     * @return La puissance que consomme en plus le cluster, due aux pertes du
     *         chemin d'approvisionnement en énergie
     */
    public double PertesPuissCluster() {
        return chemin_Approvisionnement.pertesChemin;
    }
}
