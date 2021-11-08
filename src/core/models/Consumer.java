package core.models;

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
