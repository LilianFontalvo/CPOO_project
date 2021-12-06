package core.weatherModels;

import core.weatherModels.mathematicalModels.GaussianFunction;
import core.weatherModels.mathematicalModels.SinusFunction;

/**
 * CloudCover
 * 
 * Cette classe va permettre de définir un coefficient de couverture nuageuse
 * (entre 0 = pas de nuage et
 * 1 = nuages isolant totalement du rayonnement solaire).
 * 
 * @author q.bertrand
 */

public class CloudCover implements UtilConstantes {

    private int day;
    private int min;

    /**
     * Un good day = true est un jour où il n'y a pas de nuage. Un goodDay = false
     * est un jour où il y
     * a une proportion non nulle de nuage dans le ciel.
     */
    private boolean goodDay;

    /**
     * La probabilité qu'un jour soit good.
     */
    private double probabilityOfGoodDay;

    /**
     * Proportion moyenne de nuage dans le ciel pour un jour donné.
     * Si le jour est good, ce ratio est nul.
     */
    private double averageRateOfCloudCoverInTheDay;

    /**
     * Proportion de nuage dans le ciel pour une minute donnée d'un jour donné.
     * Si le jour est good, ce ratio est nul.
     */
    private double averageRateOfCloudCoverInTheMinute;

    /**
     * On veut représenter la courbe de probabilité de tomber sur une "belle
     * journée" (cad sans aucun nuage) par jour avec : a*sin(b(x-c))+d.
     * On estime qu'en son maximum la probabilité d'une journée sans nuage est
     * d'environ 0.9 (au mois de juin / juillet), alors qu'en son minimum, elle
     * n'est
     * plus que de 0.1 (en décembre / janvier).
     */
    final static double maxFunction = 0.9;
    final static double minFunction = 0.1;
    final static double period = NumberOfDaysInYear;
    final static double abscissePointInflection = period/4;

    /**
     * Pour une journée donnée, on veut calculer le ratio de nuages dans le ciel.
     * On va procéder de manière aléatoire selon une représentation gaussienne
     * centrée en 0,5 (cad au milieu
     * de l'intervalle possible [0,1] = [aucun nuage ; saturation en nuages]).
     * On a construit la gaussienne pour s'assurer que cette intervalle (implémenté
     * par limiteSupérieureVoulue)
     * soit respecté (voir classe GaussianFunction pour plus de détails).
     */
    final static double average = 0.5;
    final static double limiteSuperieureVoulue = 1;

    /**
     * Pour finir, à partir du ratio de nuage sur une journée, on veut calculer
     * l'évolution journalière en
     * fonction des minutes. Ainsi, on considèrera que ce ratio journalier pourra
     * varier aléatoirement dans
     * un intervalle de 30% pendant la journée.
     */
    final static double variationInTheDay = 0.30;

    /**
     * On construit ici nos deux représentations (sinus et gaussienne), comme
     * spécifié précédemment.
     * 
     * @param min
     * @param day
     */
    public CloudCover(int min, int day) {
        this.day = day;
        this.min = min;

        /**
         * Etape 1 : Quelle est la probabilité que ce jour soit un good day ?
         */
        SinusFunction sinus = new SinusFunction(minFunction, maxFunction, abscissePointInflection, period);
        probabilityOfGoodDay = sinus.Sinus(day);

        /**
         * Etape 2 : Quelle est la couverture nuageuse moyenne sur ce jour ? 
         */
        double i = Math.random(); // un double entre 0 et 1 qui nous permettra de faire une disjonction de cas
                                  // entre un goodday = true et un goodday = false
        if (i < probabilityOfGoodDay) {
            goodDay = true;
            averageRateOfCloudCoverInTheDay = 0; // le jour est un goodday, pas besoin d'aller plus loin dans le calcul du
                                          // ratio, qui, de fait, est égal à 0 ici.
        } else {
            goodDay = false;
            GaussianFunction gaussian = new GaussianFunction(average, limiteSuperieureVoulue);
            averageRateOfCloudCoverInTheDay = gaussian.getGaussian();
        }

        /**
         * Etape 3 : Quelle est la couverture nuageuse par minute sur ce jour ? 
         * Le nombre aléatoire sera entre 0,7 et 1,3, donc on sera comme convenu dans un intervalle de 30% 
         * autour de la moyenne journalière.
         */
        averageRateOfCloudCoverInTheMinute = ((1 - variationInTheDay) + Math.random() * 2 * variationInTheDay) * averageRateOfCloudCoverInTheDay ;
    }

    public int getDay() {
        return this.day;
    }

    public int getMin() {
        return this.min;
    }

    public boolean getGoodDay() {
        return this.goodDay;
    }

    public boolean isGoodDay() {
        return this.goodDay;
    }

    public double getProbabilityOfGoodDay() {
        return this.probabilityOfGoodDay;
    }

    public double getRateOfCloudCoverInTheDay() {
        return this.averageRateOfCloudCoverInTheDay;
    }

    public double getAverageRatioOfCloudCoverOfTheMinute() {
        return this.averageRateOfCloudCoverInTheMinute;
    }

}
