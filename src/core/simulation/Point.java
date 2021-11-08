package core.simulation;

import core.models.Model;

/**
 * Point est une classe réprésentant un point d'intérêt dans la simulation.
 * Chaque point doit être différent mais peuvent partager un même modèle. Un
 * ensemble de points (Point[]) peut être simulé par Simulation.
 * 
 * @see Simulation
 * 
 * @author Lilian Fontalvo
 * @version 1.0
 */

public class Point {

    /**
     * Le nom du point d'intérêt
     */
    private String name;
    /**
     * Le modèle de consommation du point d'intérêt
     * 
     * @see Model
     */
    private Model model;

    public Point(String name, Model model) {
        this.name = name;
        this.model = model;
    }

    /**
     * Renvoie le nom du point d'intérêt
     * 
     * @return Le nom du Point (String)
     */
    public String getName() {
        return name;
    }

    /**
     * Renvoie le modèle que suit le point d'intérêt
     * 
     * @return Le modèle du Point
     */
    public Model getModel() {
        return model;
    }

}
