package core.weatherModels;

import core.weatherModels.mathematicalModels.AffineFunction;
import core.weatherModels.mathematicalModels.SinusFunction;

/**
 * Sunlight
 * 
 * Classe dont l'objectif est de déterminer l'énergie (en Wh) émise par le
 * soleil par minute, en tout jour de l'année.
 * 
 * @author q.bertrand
 */

public class Sunlight implements UtilConstantes {

    private int day;
    private int min;

    /**
     * C'est la production d'énergie moyenne du rayonnement solaire au cours de la
     * journée sur un m² (en Wh).
     */
    private double averagePowerProductionOfTheDay;

    /**
     * C'est la production d'énergie moyenne du rayonnement solaire au cours de la
     * minute (en Wh).
     */
    private double averagePowerProductionOfTheMinute;

    /**
     * On veut décrire la variation de la production dûe au rayonnement solaire
     * selon les jours de l'année :
     * on choisit d'utiliser une représentation sinus.
     * A l'aide de données graphiques sur Internet
     * (https://energieplus-lesite.be/theories/climat8/ensoleillement-d8/),
     * on détermine les 3 constantes suivantes qui nous permettront d'utiliser la
     * classe SinusFunction.
     */
    private final static double maxFunction = 5000; // en Wh
    private final static double minFunction = 500; // en Wh
    private final static double period = NumberOfDaysInYear; // en jours
    private final static double abscissePointInflection = period/4 ; // en jours 
    
    /**
     * 
     * @param min                 // la minute choisie
     * @param day                 // le jour choisi
     * @param numberOfSquareMeter // le nombre de metrès carrés de surface
     *                            considérée
     */
    public Sunlight(int min, int day, int numberOfSquareMeter) {
        this.day = day;
        this.min = min;
        SinusFunction function = new SinusFunction(minFunction, maxFunction, abscissePointInflection, period);

        averagePowerProductionOfTheDay = numberOfSquareMeter * function.Sinus(day);

        /**
         * Pour l'énergie d'ensoleillement par mètre carré en fonction des minutes, on
         * prend une fonction triangle
         * qui part de 0 à 0h, qui atteint son maximum à 12h et qui revient de façon
         * symétrique en 0 à 24h.
         * Cette fonction doit respecter la moyenne journalière définie juste au-dessus
         * :
         * Ainsi, le maximum à 12h sera égale à 2 fois la moyenne.
         * Pour les points, en 1ère position les abscisses (temps), et en seconde les
         * ordonnées (énergie).
         */
        double[] point1 = { 0, 0 };
        double[] point2 = { 12 * NumberOfMinutesInHours, 2 * averagePowerProductionOfTheDay };
        double[] point3 = { 24 * NumberOfMinutesInHours, 0 };
        if (min <= NumberOfMinutesInHours * 12) {
            AffineFunction courbe1 = new AffineFunction(point1, point2);
            averagePowerProductionOfTheMinute = numberOfSquareMeter
                    * (courbe1.getSlope() * min + courbe1.GetYIntercepter());
        } else {
            AffineFunction courbe2 = new AffineFunction(point2, point3);
            averagePowerProductionOfTheMinute = numberOfSquareMeter
                    * (courbe2.getSlope() * min + courbe2.GetYIntercepter());
        }
    }

    public int getDay() {
        return this.day;
    }

    public int getMin() {
        return this.min;
    }

    public double getAveragePowerProductionOfTheDay() {
        return this.averagePowerProductionOfTheDay;
    }

    public double getAveragePowerProductionOfTheMinute() {
        return this.averagePowerProductionOfTheMinute;
    }
}
