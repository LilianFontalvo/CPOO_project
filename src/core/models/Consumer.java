package core.models;
/**
 * Sous-classe de Model regroupant les consommateurs, i.e. à production nulle
 * 
 * @author Lilian Fontalvo
 * @version 1.0
 */
public abstract class Consumer extends Model{
    
    public Consumer(){
        super();
    }

    public double getPowerProdMin(int min, int day){
        return 0;
    }

    public double getProdDay(int day){
        return 0;
    }
}
