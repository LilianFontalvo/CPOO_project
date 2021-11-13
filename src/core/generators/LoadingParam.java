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

    static private Model getModelFromString(String[] tokens) {
        String model = tokens[4].trim();
        switch (model) {
        case "House":
            return new House();
        case "Factory":
            return new Factory();
        case "SolarPlant":
            return new SolarPlant();
        case "NuclearPlant":
            return new NuclearPlant();
        default:
            throw new IllegalArgumentException("The requested model '" + model + "' does not exist.");
        }
    }

    static private PointComplexe ComplexPointGenerator(BufferedReader bin, String[] tokens, String name)
            throws IOException {
        ArrayList<Point> pointsAL = new ArrayList<Point>();
        Point[] points = new Point[pointsAL.size()];
        int nbTot = Integer.parseInt(tokens[3].trim());
        int count = 0;
        String nameCluster = tokens[2];
        while ((count != nbTot) && (bin.ready())) {
            String line = bin.readLine();
            System.out.println("Complex Point: " + line);
            tokens = line.split(";");
            String nameP = tokens[0].trim();
            int nb = Integer.parseInt(tokens[1].trim());
            int nbIP = Integer.parseInt(tokens[3].trim());
            String clusterName = tokens[2].trim();
            if (!clusterName.equals(nameCluster))
                throw new IllegalArgumentException(
                        "Integrated point of complex point '" + name + "' is in the wrong cluster.");
            if (nbIP == 0) {
                Model model = getModelFromString(tokens);
                for (int i = 0; i < nb; i++) {
                    pointsAL.add(new PointSingulier(nameP, model));
                    count++;
                }
            } else {
                PointComplexe point = ComplexPointGenerator(bin, tokens, name);
                for (int i = 0; i < nb; i++) {
                    pointsAL.add(point);
                    count++;
                }
            }
            if (count != nbTot)
                throw new IllegalArgumentException(
                        "Wrong number of integrated points. Expected " + nbTot + ", given " + count +".");
        }
        points = pointsAL.toArray(points);
        return new PointComplexe(name, points);
    }

    static private void readPoint(String filename, int nbCluster, ArrayList<String> namesClusters,
            ArrayList<ArrayList<Point>> listsPoints) throws NumberFormatException, IOException {
        FileReader in = new FileReader(filename);
        BufferedReader bin = new BufferedReader(in);
        bin.readLine();
        while (bin.ready()) {
            String line = bin.readLine();
            System.out.println("readPoint: " + line);
            String[] tokens = line.split(";");
            String name = tokens[0].trim();
            int nb = Integer.parseInt(tokens[1].trim());
            int nbIP = Integer.parseInt(tokens[3].trim());
            String clusterName = tokens[2].trim();
            if (nbIP == 0) {
                Model model = getModelFromString(tokens);
                for (int i = 0; i < nbCluster; i++) {
                    String nameCluster = namesClusters.get(i);
                    if (clusterName.equals(nameCluster)) {
                        for (int j = 0; j < nb; j++) {
                            ArrayList<Point> pointsAL = listsPoints.get(i);
                            pointsAL.add(new PointSingulier(name, model));
                            listsPoints.set(i, pointsAL);
                        }
                    }
                }
            } else {
                for (int i = 0; i < nbCluster; i++) {
                    String nameCluster = namesClusters.get(i);
                    if (clusterName.equals(nameCluster)) {
                        PointComplexe point = ComplexPointGenerator(bin, tokens, name);
                        for (int j = 0; j < nb; j++) {
                            ArrayList<Point> pointsAL = listsPoints.get(i);
                            pointsAL.add(point);
                            listsPoints.set(i, pointsAL);
                        }
                    }
                }
            }
        }
        bin.close();
    }

    static public Cluster[] readClusters() throws IOException {
        ArrayList<Cluster> clusters = new ArrayList<Cluster>();

        ArrayList<String> namesClusters = new ArrayList<String>();
        ArrayList<double[]> positionsClusters = new ArrayList<double[]>();

        FileReader in = new FileReader(clustersFile);
        BufferedReader binClusters = new BufferedReader(in);
        binClusters.readLine();
        while (binClusters.ready()) {
            String line = binClusters.readLine();
            String[] tokens = line.split(";");
            namesClusters.add(tokens[0].trim());
            double[] pos = new double[2];
            pos[0] = Double.parseDouble(tokens[1].trim());
            pos[1] = Double.parseDouble(tokens[2].trim());
            positionsClusters.add(pos);
        }
        binClusters.close();

        int nbCluster = namesClusters.size();

        ArrayList<ArrayList<Point>> listsPoints = new ArrayList<ArrayList<Point>>();
        for (int i = 0; i < nbCluster; i++) {
            listsPoints.add(new ArrayList<Point>());
        }

        readPoint(consumersFile, nbCluster, namesClusters, listsPoints);
        readPoint(producersFile, nbCluster, namesClusters, listsPoints);

        for (int i = 0; i < nbCluster; i++) {
            ArrayList<Point> pointsAL = listsPoints.get(i);
            Point[] points = new Point[pointsAL.size()];
            points = pointsAL.toArray(points);
            double[] pos = positionsClusters.get(i);
            clusters.add(new Cluster(namesClusters.get(i), points, pos[0], pos[1]));
        }
        Cluster[] clustersA = new Cluster[nbCluster];
        clustersA = clusters.toArray(clustersA);
        return clustersA;
    }

    public static void main(String[] args) throws IOException {
        Cluster[] listClusters = LoadingParam.readClusters();
        Simulation sim2 = new Simulation(listClusters);
        sim2.simOneDay(241, true);
    }

}
