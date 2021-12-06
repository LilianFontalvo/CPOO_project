package core.weatherModels.mathematicalModels;

/**
 * RandomConstantFunction
 * 
 * Cette classe permet de déterminer une constante aléatoire compris dans un intervalle 
 * renseigné par l'utilisateur.
 * @author q.bertrand
 */

public class RandomConstantFunction {

    /**
     * Les bornes inférieure et supérieure.
     */
    private double lowerBoundInOrdinate ;
    private double upperBoundInOrdinate ;
    
    /**
     * 
     * @param lowerBoundInOrdinate // idem attribut
     * @param upperBoundInOrdinate // idem attribut
     */
    public RandomConstantFunction (double lowerBoundInOrdinate, double upperBoundInOrdinate) {
        this.lowerBoundInOrdinate = lowerBoundInOrdinate ;
        this.upperBoundInOrdinate = upperBoundInOrdinate ;
    } 

    /**
     * Méthode qui met exactement en place l'objectif fixé par la classe.
     * @param x Le paramètre en abscisse auquel sera associé cette constante
     * @return La constante aléatoire dans l'intervalle souhaité.
     */
    public double getConstante (int x) {
        double constante = lowerBoundInOrdinate + Math.random() * (upperBoundInOrdinate - lowerBoundInOrdinate);
        return (constante) ;
    }

    public double getLowerBoundInOrdinate() {
        return this.lowerBoundInOrdinate;
    }

    public double getUpperBoundInOrdinate() {
        return this.upperBoundInOrdinate;
    }


}
