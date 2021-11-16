package core.models;

import java.util.Random;

public class RandHouse extends Consumer{

    Random rand;
    private double reveil;
    private double departMatin;
    private double retourMidi;
    private double departMidi;
    private double retourSoir;
    private double dodo;
    private double[] doubles;

    public RandHouse(long seed){
        super();
        rand = new Random(seed);
        reveil = 6+2*rand.nextDouble();
        departMatin =  7.5+rand.nextDouble();
        retourMidi = 11.5+rand.nextDouble();
        departMidi = 13.5+rand.nextDouble();
        retourSoir = 16+3*rand.nextDouble();
        dodo = 21+3*rand.nextDouble();
        doubles = rand.doubles(1440).toArray();
    }

    public RandHouse(){
        this(new Random().nextLong());
    }

    public double getPowerConsMin(int min, int day) {
        if (min <= 60 * reveil)
            return 350 + 100*doubles[min];
        if ((60 * reveil < min) && (min <= 60 * departMatin))
            return 350 + 5000*doubles[min];
        if ((60 * departMatin < min) && (min <= 60 * retourMidi))
            return 350 + 150*doubles[min];
        if ((60 * retourMidi < min) && (min <= 60 * departMidi))
            return 350 + 8000*doubles[min];
        if ((60 * departMidi < min) && (min <= 60 * retourSoir))
        return 350 + 150*doubles[min];
        if ((60 * retourSoir < min) && (min <= 60 * dodo))
        return 350 + 8000*doubles[min];
        else
            return 350 + 100*doubles[min];
    }

}
