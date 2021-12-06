package core.weatherModels.mathematicalModels;

/**
 * AffineFunction
 * 
 * Cette classe permet de construire une fonction affine à partir de deux points
 * que l'utilisateur renseignera.
 * 
 * @author q.bertrand
 */

public class AffineFunction {
    
    /**
     * Les deux points appartenant à la fonction que l'on souhaite tracer et qui permettent de déterminer
     * l'équation complète de la droite.
     * En 1ère position la position selon les abscisses, en 2ème, la position selon les ordonnées.
     */
    private double[] point1 = new double[2];
    private double[] point2 = new double[2];

    /**
     * 
     * @param point1 // voir attribut
     * @param point2 // voir attribut
     */
    public AffineFunction (double[] point1, double[] point2) {
        this.point1 = point1 ;
        this.point2 = point2 ;
    }

    /**
     * Méthode permettant de calculer et retourner la pente de la fonction affine. 
     * @return la pente de la fonction affine 
     */
    public double getSlope () {
        double slope = (point2[1] - point1[1])/(point2[0] - point1[0]) ;
        return (slope) ;
    }

    /**
     * Méthode permettant de calculer et retourner l'ordonnée à l'origine de la fonction affine.
     * @return l'ordonnée à l'origine
     */
    public double GetYIntercepter () {
        double intercepter = point1[1] - getSlope() * point1[1] ;
        return (intercepter) ;
    }

    public double[] getPoint1() {
        return this.point1;
    }

    public double[] getPoint2() {
        return this.point2;
    }

}