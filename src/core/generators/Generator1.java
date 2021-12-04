package core.generators;

import java.util.ArrayList;

import core.models.Model;
import core.simulation.Cluster;
import core.simulation.*;

/**
 * Generator1 est une classe permettant de générer un cluster contenant des
 * points d'intérêt
 * en fonction d'une liste de modèle à suivre et de leur répartition. Genrator1
 * implémente l'interface Generator.
 * 
 * @author Lilian Fontalvo
 * @version 1.0
 * 
 * @see Generator
 */
public class Generator1 implements Generator {
    /**
     * Liste de modèle à utiliser
     */
    protected Model[] listModel;
    /**
     * Liste d'entier représentant le nombre de points suivant le modèle
     * correspondant dans "listModel"
     */
    protected int[] listNb;

    public Generator1(Model[] listModel, int[] listNb) {
        this.listModel = listModel;
        this.listNb = listNb;
    }

    /**
     * Calcule le nombre total de points du cluster
     * 
     * @return le nombre de points du cluster
     */
    public int getNb() {
        int N = 0;
        for (int nb : listNb) {
            N += nb;
        }
        return N;
    }

    /**
     * Constuit un cluster contenant une liste de points en fonction des répartition
     * de listModel et
     * listNb
     * 
     * @return Un cluster contenant une liste de points d'intérêt
     */
    public Cluster[] getClusters() {
        Point[] points = new Point[getNb()];
        Model model;
        int i = 0;
        int k = 0;
        for (int nb : listNb) {
            for (int j = 0; j < nb; j++) {
                model = listModel[k];
                points[i] = new PointSingulier(model.toString() + "_" + j, model);
                i++;
            }
            k++;
        }
        ArrayList<Ligne> lignes = new ArrayList<Ligne>();
        Cluster[] cluster = { new ClusterSansProd("null", points, 0, 0, new Chemin(lignes)) };
        return cluster;
    }
}
