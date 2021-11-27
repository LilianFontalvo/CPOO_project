package core.generators;

import java.util.Random;
import core.models.*;

/**
 * RandomSmallTown est une sous-classe de Generator1 qui permet de créer automatiquement 
 * et aléatoirement une liste depoints représentant une petite ville.
 */



public class RandomSmallTown extends Generator1 {

    public RandomSmallTown() {
        super(null, null);
        Random rand = new Random();
        Model[] listM = { new RandHouse(), new Factory(), new SolarPlant(), new NuclearPlant() };
        listModel = listM;
        int nH = 1000 + rand.nextInt(9001); // pose un nombre aléatoire de points
        int nF = 1 + rand.nextInt(5);
        int nS = 5 + rand.nextInt(6);
        int nN = rand.nextInt(2);
        int[] listN = {nH, nF, nS, nN}; 
        listNb = listN;
    }

}
