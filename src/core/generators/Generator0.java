package core.generators;

import java.util.ArrayList;

import core.models.Model;
import core.simulation.*;

/**
 * Generator0 est une classe permettant de générer une liste d'un unique cluster
 * contenant un unique
 * point d'intérêt. Genrator0 implémente l'interface Generator.
 * 
 * @author Lilian Fontalvo
 * @version 1.0
 * 
 * @see Generator
 */
public class Generator0 implements Generator {
    /**
     * Le modèle que devra suivre le point
     */
    private Model model;

    public Generator0(Model model) {
        this.model = model;
    }

    /**
     * Crée une liste d'un seul élément en fonction du modèle fourni
     * 
     * @return Une liste d'un seul cluster contenant un unique point d'intérêt
     *         suivant le modèle fourni
     */
    public Cluster[] getClusters() {
        Point[] points = { new PointSingulier(model.toString() + "_0", model) };
        ArrayList<Ligne> lignes = new ArrayList<Ligne>();
        Cluster[] cluster = { new ClusterSansProd("null", points, 0, 0, new Chemin(lignes)) };
        return cluster;
    }
}
