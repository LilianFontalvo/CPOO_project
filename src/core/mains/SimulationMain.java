package core.mains;

import core.generators.*;
import core.models.*;
import core.simulation.*;

public class SimulationMain {
    public static void main(String[] args) {

        // For only one element
        Generator scenario0 = new Generator0(new NuclearPlant());
        Simulation sim0 = new Simulation(scenario0.getPoints());
        sim0.simOneDay(241, false); // jour 241, le 28 août, un jour comme un autre (jour 0 = 1er janvier)
        sim0.simOneYear(false);

        // For a list of points and their occurences
        Model[] listModel = { new House(), new Factory(), new SolarPlant(), new NuclearPlant() };
        int[] listNb = { 100, 1, 4, 1 };
        Generator scenario1 = new Generator1(listModel, listNb);
        Simulation sim1 = new Simulation(scenario1.getPoints());
        sim1.simOneDay(241, false);
        sim1.simOneYear(false);

        // For a small randomly generated town
        Generator scenario2 = new RandomSmallTown();
        Point[] listPoint = scenario2.getPoints();
        Simulation sim2 = new Simulation(listPoint);
        sim2.simOneDay(241, true);
        sim2.simOneYear(true);
    }
}





