package core.simulation;

import java.util.ArrayList;

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

public abstract class Point {

    protected String name;
    protected boolean complexe;

    public Point(String name, boolean complexe){
        this.name = name;
        this.complexe = complexe;
    }

    public abstract double getPowerConsMin(int min, int day);
    public abstract double getPowerProdMin(int min, int day);
    public abstract double getConsDay(int day);
    public abstract double getProdDay(int day);


    public String getName() {
        return this.name;
    }

    public boolean isComplexe() {
        return this.complexe;
    }

        
    public ArrayList <PointSingulier> getAllPoints(){
        return getPointsBody(this);
     }

    private ArrayList <PointSingulier> getPointsBody(Point point){
        ArrayList <PointSingulier> pointsToReturn = new ArrayList<>();
        if (point.isComplexe()){
            for (Point p : ((PointComplexe) point).getPoints()){
                pointsToReturn.addAll(this.getPointsBody(p));
            }
        } else {
            pointsToReturn.add((PointSingulier)point);
        }
        return pointsToReturn;
     }


}
