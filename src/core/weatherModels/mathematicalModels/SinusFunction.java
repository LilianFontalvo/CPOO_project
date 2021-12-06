package core.weatherModels.mathematicalModels;

/**
 * Sinus Function 
 * 
 * Cette classe a vocation à déterminer l'expression analytique d'une fonction sinus dont on 
 * ne connait que la représentation graphique.
 * 
 * @author q.bertrand
 */

public class SinusFunction {

    /**
     * La fonction sinus de cette manière : f(x) = a * sin(b * (x-c)) + d      avec :
     */
    private double a; // mesure de l'amplitude
    private double b; // mesure de la période
    private double c; // mesure du déphasage 
    private double d; // mesure de l'axe d'oscillation

    /** 
     * Les données qui seront demandées à l'utilisateur pour déterminer l'expression du sinus possédant ces 
     * telles caractéristiques. 
     */
    private double maxFunction ; // la valeur du maximum de la fonction
    private double minFunction ; // la valeur du minimum de la fonction 
    private double abscissePointInflection ; // l'abscisse du point d'inflexion
    private double period ; // la période 

    /**
     * On va construire la fonction sinus (et donc fixer les valeurs de a, b, c, d), en fonction 
     * des données du max, du min et de la période de la fonction. 
     * Les calculs sont issus des techniques de résolution usuels; 
     * @param minFunction // idem attribut
     * @param maxFunction // idem attribut 
     * @param period // idem attribut
     */
    public SinusFunction (double minFunction, double maxFunction, double abscissePointInflection, double period) {

        this.maxFunction = maxFunction;
        this.minFunction = minFunction;
        this.abscissePointInflection = abscissePointInflection;
        this.period = period;

        a = (maxFunction-minFunction)/2 ;    
        d = a + minFunction ; 
        b = 2*Math.PI/period ;
        c = abscissePointInflection ; 
    }

    /**
     * Méthode d'exécution de la fonction sinus créée. 
     * @param day Rentrer le jour que l'on souhaite évaluer au travers de cette fonction sinus.
     * @return Retourne la valeur en ce jour de la fonction sinus. 
     * Si sinus est une fonction telle que f(x) = y, alors day correspon au x et return renvoie le y.
     */
    public double Sinus (int x) {
        return (a * Math.sin(b*(x-c))+ d);
    }

    public double getA() {
        return this.a;
    }

    public double getB() {
        return this.b;
    }

    public double getC() {
        return this.c;
    }

    public double getD() {
        return this.d;
    }

    public double getMaxFunction() {
        return this.maxFunction;
    }

    public double getMinFunction() {
        return this.minFunction;
    }

    public double getAbscissePointInflection() {
        return this.abscissePointInflection;
    }

    public double getPeriod() {
        return this.period;
    }

}
