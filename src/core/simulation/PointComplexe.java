package core.simulation;

public class PointComplexe extends Point{
    protected Point[] points;

    public PointComplexe(String name, Point[] points) {
        super(name,true);
        this.points = points;
    }

    public String getName() {
        return name;
    }

    /**
     * Renvoie le modèle que suit le point d'intérêt
     * 
     * @return Le modèle du Point
     */
    public Point[] getPoints() {
        return points;
    }

    public double getPowerConsMin(int p, int min, int day) {
        return points[p].getPowerConsMin(min, day);
    }
    
    public double getPowerProdMin(int p, int min, int day) {
        return points[p].getPowerProdMin(min, day);
    }

    public double getConsDay(int p, int day) {
        return points[p].getConsDay(day);
    }

    public double getProdDay(int p, int day) {
        return points[p].getProdDay(day);
    }

    public double getPowerConsMin(int min, int day) {
        double cons = 0;
        for (Point point : points) {
            cons += point.getPowerConsMin(min, day);
        }
        return cons;
    }
    
    public double getPowerProdMin(int min, int day) {
        double prod = 0;
        for (Point point : points) {
            prod += point.getPowerProdMin(min, day);
        }
        return prod;
    }

    public double getConsDay(int day) {
        double cons = 0;
        for (Point point : points) {
            cons += point.getConsDay(day);
        }
        return cons;
    }

    public double getProdDay(int day) {
        double prod = 0;
        for (Point point : points) {
            prod += point.getProdDay(day);
        }
        return prod;
    }
}
