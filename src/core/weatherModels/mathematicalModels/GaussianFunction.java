package core.weatherModels.mathematicalModels;
import java.util.Random;

/**
 * GaussianFunction
 * 
 * Cette classe a vocation à bâtir une gaussienne centrée en une valeur choisie par l'utilisateur, et 
 * dont l'écart-type découle des conditions d'intervalle fixées par l'utilisateur. 
 * 
 * @author q.bertrand
 */

public class GaussianFunction {

    private double average; // moyenne de la gaussienne
    private double standardDeviation; // écart-type de la gaussienne

    /**
     * Ce sont les limites dans lesquelles on voudra que notre nombre aléatoire soit tiré. Pour qu'un tel 
     * évènement ait une probabilité suffisamment acceptable, on fixera l'écart-type à partir de cet intervalle.
     * Ainsi, puisque dans une gaussienne, 99% des valeurs sont tirées entre -3sigma et sigma, on fixera 3 sigma 
     * égal à la taille de l'intervalle [average ; limiteSupérieureVoulue].
     */
    private double limiteInferieureVoulue;
    private double limiteSuperieureVoulue ;

    /**
     * Seulement deux valeurs choisies par l'utilisateur permettront de bâtir la gaussienne.
     * @param average // voir attribut homonyme
     * @param limiteSuperieureVoulue // voir attribut homonyme 
     * Pour la fixation de l'écart-type, voir explication attributs. 
     */
    public GaussianFunction (double average, double limiteSuperieureVoulue) {
        this.average = average ;
        this.limiteSuperieureVoulue = limiteSuperieureVoulue ;
        this.limiteInferieureVoulue = average - (limiteSuperieureVoulue - average) ;
        this.standardDeviation = (limiteSuperieureVoulue-average)/3 ;
    }

    /**
     * On va tirer un 'double' aléatoire avec la gaussienne ainsi déterminée. 
     * La boucle while permettra de recommencer le tirage si la valeur tirée n'est pas dans l'intervalle. 
     * Avec 99% de chance de réussite à chaque tirage, on sortira rapidement de la boucle. 
     * @return Un 'double' aléatoire selon la gaussienne, compris entre [limiteInférieureVoule ; limiteSupérieureVoulue]
     */
    public double getGaussian () {
        Random random = new Random() ;
        double tirage = random.nextGaussian() * standardDeviation + average ;
        while (tirage>limiteSuperieureVoulue || tirage<limiteInferieureVoulue) { // dans 1% des cas puisque 99% des valeurs tombent dans 3 sigma = limite supérieure
            tirage = random.nextGaussian() * standardDeviation + average ;
        }
        return tirage ;
    }

    public double getAverage() {
        return this.average;
    }

    public double getStandardDeviation() {
        return this.standardDeviation;
    }

    public double getLimiteInferieureVoulue() {
        return this.limiteInferieureVoulue;
    }

    public double getLimiteSuperieureVoulue() {
        return this.limiteSuperieureVoulue;
    }

}
