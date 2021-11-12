package core.simulation;

import core.models.Model;

public class PointSingulier extends Point{
    protected Model model;

    public PointSingulier(String name, Model model) {
        super(name, false);
        this.model = model;
    }

    public String getName() {
        return name;
    }

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
