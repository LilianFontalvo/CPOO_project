package core.models;

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
