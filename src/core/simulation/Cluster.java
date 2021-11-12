package core.simulation;

public class Cluster extends PointComplexe {
    
    private double x;
    private double y;

    public Cluster(String name, Point[] points, double x, double y){
        super(name, points);
        this.x = x;
        this.y = y;
    }

    public Cluster(Point[] points, double x, double y){
        this("Cluster", points, x, y);
    }

    public Point[] getPoints(){
        return points;
    }

    public String getName() {
        return name;
    }

    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
}
