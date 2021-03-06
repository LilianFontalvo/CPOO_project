package core.mains;

import core.generators.LoadingParam;
import core.simulation.Cluster;
import core.simulation.Simulation;

import java.io.IOException;

public class ParamatersMain {
    public static void main(String[] args) throws IOException {
        Cluster[] listClusters = LoadingParam.readClusters();
        Simulation sim = new Simulation(listClusters);
        sim.simOneDay(241, true);
    }
    
}
