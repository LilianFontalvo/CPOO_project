package core.models;

import java.util.Random;

public class RandHouse extends Consumer{

    Random rand;

    public RandHouse(){
        super();
        rand = new Random();
    }

    public double getPowerConsMin(int min, int day) {
        double reveil = 6+2*rand.nextDouble();
        double departMatin =  7.5+rand.nextDouble();
        double retourMidi = 11.5+rand.nextDouble();
        double departMidi = 13.5+rand.nextDouble();
        double retourSoir = 16+3*rand.nextDouble();
        double dodo = 21+3*rand.nextDouble();
        if (min <= 60 * reveil)
            return 350 + rand.nextInt(100);
        if ((60 * reveil < min) && (min <= 60 * departMatin))
            return 350 + rand.nextInt(4000);
        if ((60 * departMatin < min) && (min <= 60 * retourMidi))
            return 350 + rand.nextInt(150);
        if ((60 * retourMidi < min) && (min <= 60 * departMidi))
            return 350 + rand.nextInt(8000);
        if ((60 * departMidi < min) && (min <= 60 * retourSoir))
        return 350 + rand.nextInt(150);
        if ((60 * retourSoir < min) && (min <= 60 * dodo))
        return 350 + rand.nextInt(8000);
        else
            return 350 + rand.nextInt(100);
    }
    
}
