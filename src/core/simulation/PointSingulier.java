package core.simulation;

import core.models.Model;

/**
 * Sous-classe de point réprésentant un point singulier dans la simulation.
 * Un point singulier représente une entité physique suivant un modèle.
 * 
 * @author Lilian Fontalvo
 * @version 1.0
 */
public class PointSingulier extends Point{
    protected Model model;

    public PointSingulier(String name, Model model) {
        super(name, false);
        this.model = model;
    }

    /**
     * 
     * @return Le nom du Point
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @return Le modèle suivi par le Point
     */
    public Model getModel() {
        return model;
    }

    public  double getPowerConsMin(int min, int day){
        return model.getPowerConsMin(min, day);
    }

    public  double getPowerProdMin(int min, int day){
        return model.getPowerProdMin(min, day);
    }

    public  double getConsDay(int day){
        return model.getConsDay(day);
    }

    public  double getProdDay(int day){
        return model.getProdDay(day);
    }

}
