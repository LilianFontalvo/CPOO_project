package core.mains;
import core.generators.Generator;
import core.generators.RandomSmallTown;
import core.simulation.Cluster;
import core.simulation.Simulation;
import javax.swing.SwingUtilities;

import GUI.Gui;

public class GUIMain {
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Generator scenario2 = new RandomSmallTown();
                Cluster[] listClusters = scenario2.getClusters();
                Simulation sim2 = new Simulation(listClusters);
                new Gui(sim2);
            }
        });
    }       
}
    


    

