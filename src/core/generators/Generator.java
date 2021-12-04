package core.generators;

import core.simulation.Cluster;

/**
 * Generator est une interface regroupant toute classe permettant de générer une liste de clusters.
 * 
 * @author Lilian Fontalvo
 * @version 1.0
 */
public interface Generator {
    /**
     * Facilite la création d'une liste de clusters pouvant ensuite servir à démarrer une simulation.
     * 
     * @see Simulation
     * 
     * @return Une liste de clusters
     */
    public Cluster[] getClusters();
}
