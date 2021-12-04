package core.mains;

import core.generators.LoadingParam;
import core.simulation.Cluster;
import core.simulation.Simulation;
import GUI.Gui;

import java.io.IOException;

public class ParamGuiMain {
    public static void main(String[] args) throws IOException {
        Cluster[] listClusters = LoadingParam.readClusters();
        Simulation sim = new Simulation(listClusters);
        new Gui(sim);
    }
    
}
