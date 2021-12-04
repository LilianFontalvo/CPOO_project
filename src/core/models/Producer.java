package core.models;

/**
 * Sous-classe de Model qui regroupe les producteurs, i.e. à consommation nulle
 * 
 * @author Lilian Fontalvo
 * @version 1.0
 */
public abstract class Producer extends Model{
    
    public Producer(){
        super();
    }

    public double getPowerConsMin(int min, int day){
        return 0;
    }

    public double getConsDay(int day){
        return 0;
    }
}
