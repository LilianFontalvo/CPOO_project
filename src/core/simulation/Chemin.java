/* Classe pour définir les chemins, liste de lignes reliant les cluster entre eux.
*/

package core.simulation;

import java.util.ArrayList;

public class Chemin {
    private ArrayList<Ligne> Liste_de_lignes;

    /**
     * 
     * @return La liste des lignes qu'emprunte le chemin.
     */
    public ArrayList<Ligne> getListe_de_lignes() {
        return Liste_de_lignes;
    }

    /**
     * 
     * @return les pertes énergétiques dues au transfert d'énergie sur le chemin
     */
    public double pertesChemin() {
        double pertes = 0;
        for (Ligne ligne : Liste_de_lignes) {
            pertes = pertes + ligne.PertesLigne();
        }
        return pertes;
    }

}
