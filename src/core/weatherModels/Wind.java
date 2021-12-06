package core.weatherModels;

import core.weatherModels.mathematicalModels.RandomConstantFunction;
import core.weatherModels.mathematicalModels.SinusFunction;

/**
 * Wind 
 * 
 * Cette classe va permettre de déterminer l'énergie du vent à une minute donnée, sur un jour donné... 
 * exploitable à 100% par une éolienne.
 */

public class Wind implements UtilConstantes {
    
    private int day;
    private int min;

    private double averagePowerProductionOfTheDay ; 
    private double averagePowerProductionOfTheMinute ;

    /**
     * On veut décrire la variation de la vitesse moyenne du vent selon les jours de l'année :
     * et on choisit d'utiliser une représentation sinus. On observera deux périodes sur l'année, avec 
     * des pics de vitesse moyenne en avril et octobre
     */
    final static double maxFunction = 1000 ;
    final static double minFunction = 100;
    final static double period = NumberOfDaysInYear/2;
    final static double abscissePointInflection = 50 ;

    public Wind (int min, int day, int numberOfWindTurbines) {
        this.min = min ;
        this.day = day ;
        SinusFunction function1 = new SinusFunction(minFunction, maxFunction, abscissePointInflection, period);
        averagePowerProductionOfTheDay = numberOfWindTurbines * function1.Sinus(day);
        
        RandomConstantFunction function2 = new RandomConstantFunction(averagePowerProductionOfTheDay*0.7, averagePowerProductionOfTheDay*1.3);
        averagePowerProductionOfTheMinute = function2.getConstante(min);
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
