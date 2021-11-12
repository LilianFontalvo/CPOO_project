package core.generators;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import core.models.*;
import core.simulation.*;

public class LoadingParam {

    static private String clustersFile = "src/parameters/clusters.csv";
    static private String linesFile = "src/parameters/lines.csv";
    static private String consumersFile = "src/parameters/consumers.csv";
    static private String producersFile = "src/parameters/producers.csv";

    static public Cluster[] read() throws IOException {

        FileReader in = new FileReader(clustersFile);
        BufferedReader binClusters = new BufferedReader(in);
        ArrayList<Cluster> clusters = new ArrayList<Cluster>();
        while (binClusters.ready()) {
            String line = binClusters.readLine();
            String[] tokens = line.split(";");
            
            // double name = Double.parseDouble(tokens[0].trim());
            // double age = Double.parseDouble(tokens[1].trim());
        }
        binClusters.close();
        return null;
    }
    
}
