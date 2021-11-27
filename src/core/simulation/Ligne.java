/*
Classe définissant une ligne liant deux cluster,
De l'énergie est perdue en fonction de la longueur de la ligne.
*/

package core.simulation;

public class Ligne {
    private String nom; // le nom de la ligne
    private Cluster depart; // le cluster de départ de la ligne
    private Cluster arrivee; // le cluster d'arrivée de la ligne
    private double coeff_pertes; // Coefficient des pertes énergétiques sur cette ligne

    /**
     * Définit une nouvelle ligne
     * 
     * @param nom          le nom de la ligne
     * @param depart       cluster de départ de la ligne
     * @param arrivee      cluster d'arrivée de la ligne
     * @param coeff_pertes Coefficient des pertes énergétiques sur cette ligne
     * 
     */
    public Ligne(String nom, Cluster depart, Cluster arrivee, double coeff_pertes) {
        this.nom = nom;
        this.depart = depart;
        this.arrivee = arrivee;
        this.coeff_pertes = coeff_pertes;
    }

    /**
     * Renvoie nom de la ligne
     **/

    public String getNom() {
        return nom;
    }

    /**
     * Renvoie cluster de départ de la ligne
     **/

    public Cluster getDepart() {
        return depart;
    }

    /**
     * Renvoie cluster d'arrivée de la ligne
     **/

    public Cluster getArrivee() {
        return arrivee;
    }

    /**
     * @return le Coefficient des pertes énergétiques sur cette ligne
     * 
     */
    public Double getCoeff_pertes() {
        return coeff_pertes;
    }

    /**
     * @return la longueur de la ligne
     */
    public Double longueurLigne() {
        double y1 = depart.getY();
        double y2 = arrivee.getY();
        double x1 = depart.getX();
        double x2 = arrivee.getX();
        return (Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2)));
    }

    /**
     * @return l'énergie perdue sur cette ligne'
     */
    public Double PertesLigne() {
        double longueur = longueurLigne();
        return (longueur * coeff_pertes);
    }
}
