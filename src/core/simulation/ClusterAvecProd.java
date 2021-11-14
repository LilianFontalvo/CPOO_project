/*
Classe définissant les clusters autonomes.
Ils ont un unique producteur
*/

package core.simulation;

import core.simulation.Chemin;

public class ClusterAvecProd extends Cluster {

    private Point producteur; // Attribut : le producteur du cluster

    /**
     * @return le surplus de puissance produite par le cluster.
     */
    public double SurplusPuiss() {
        return (PuissProdCluster - PuissConsoCluster);
    }
}
