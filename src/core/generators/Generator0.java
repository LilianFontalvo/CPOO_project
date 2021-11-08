package core.generators;

import core.models.Model;
import core.simulation.Point;

/**
 * Generator0 est une classe permettant de générer une liste contenant un unique
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
     * @return Une liste contenant un unique point d'intérêt
     */
    public Point[] getPoints() {
        Point[] points = { new Point(model.toString() + "_0", model) };
        return points;
    }
}
