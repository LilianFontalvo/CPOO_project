/* Classe pour définir les chemins, liste de lignes reliant les cluster entre eux.
*/

package core.simulation;

import java.util.ArrayList;

public class Chemin {
    private String nom;
    private Cluster départ;
    private Cluster arrivée;
    private ArrayList<Ligne> Liste_de_lignes;


    public Chemin(String nom, Cluster départ, Cluster arrivée, ArrayList<Ligne> Liste_de_lignes) {
        this.nom = nom;
        this.départ = départ;
        this.arrivée = arrivée;
        this.Liste_de_lignes = Liste_de_lignes;
    }
    
    
    public Chemin(ArrayList<Ligne> Liste_de_lignes) {
        this("LigneStandard",null,null, Liste_de_lignes);
    }

    /**
     * 
     * @return Le nom du chemin.
     */
    public String getNom() {
        return nom;
    }

    /**
     * 
     * @return Le départ du chemin.
     */
    public Cluster getDépart() {
        return départ;
    }/**
     * 
     * @return L'arrivée chemin.
     */
    public Cluster getArrivée() {
        return arrivée;
    }

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
