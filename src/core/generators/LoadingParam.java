package core.generators;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import core.models.*;
import core.simulation.*;

public class LoadingParam {

    static private String clustersFile = "src/parameters/clusters.csv";
    static private String consumersFile = "src/parameters/consumers.csv";
    static private String producersFile = "src/parameters/producers.csv";
    static private String linesFile = "src/parameters/lines.csv";
    static private String pathsFile = "src/parameters/paths.csv";

    static private Model getModelFromString(String[] tokens) {
        String model = tokens[4].trim();
        switch (model) {
        case "House":
            return new House();
        case "RandHouse":
            int i = 0;
            String token = "";
            for (String string : tokens) {
                token = string;
                i++;
            }
            if (i == 6)
                return new RandHouse(Long.parseUnsignedLong(token.trim()));
            else
                return new RandHouse();
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
            tokens = line.split(";");
            String nameP = tokens[0].trim();
            int nb = Integer.parseInt(tokens[1].trim());
            int nbIP = Integer.parseInt(tokens[3].trim());
            String clusterName = tokens[2].trim();
            if (!clusterName.equals(nameCluster))
                throw new IllegalArgumentException(
                        "Integrated point of complex point '" + name + "' is in the wrong cluster.");
            if (nbIP == 0) {
                for (int i = 0; i < nb; i++) {
                    Model model = getModelFromString(tokens);
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
                        "Wrong number of integrated points. Expected " + nbTot + ", given " + count + ".");
        }
        points = pointsAL.toArray(points);
        return new PointComplexe(name, points);
    }

    static private void readPoint(String filename, int nbCluster, ArrayList<String> namesClusters,
            ArrayList<ArrayList<Point>> listsPoints, boolean isProducer) throws NumberFormatException, IOException {
        FileReader in = new FileReader(filename);
        BufferedReader bin = new BufferedReader(in);
        bin.readLine();
        while (bin.ready()) {
            String line = bin.readLine();
            String[] tokens = line.split(";");
            String name = tokens[0].trim();
            int nb = Integer.parseInt(tokens[1].trim());
            int nbIP = Integer.parseInt(tokens[3].trim());
            String clusterName = tokens[2].trim();
            if ((isProducer) & (nb != 1))
                throw new IllegalArgumentException(
                        "Wrong number of Producers in the cluster. Expected 1, given " + nb + ".");
            if (nbIP == 0) {
                for (int i = 0; i < nbCluster; i++) {
                    String nameCluster = namesClusters.get(i);
                    if (clusterName.equals(nameCluster)) {
                        for (int j = 0; j < nb; j++) {
                            ArrayList<Point> pointsAL = listsPoints.get(i);
                            Model model = getModelFromString(tokens);
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

    static private void readLines(String filename, ArrayList<String> namesClusters, ArrayList<Ligne> listLines,
            ArrayList<String> names, ArrayList<Cluster> clusters) throws IOException {
        FileReader in = new FileReader(filename);
        BufferedReader binLines = new BufferedReader(in);
        binLines.readLine();
        while (binLines.ready()) {
            String line = binLines.readLine();
            String[] tokens = line.split(";");
            String name = tokens[0].trim();
            String startName = tokens[1].trim();
            String endName = tokens[2].trim();
            double coef = Double.parseDouble(tokens[3].trim());
            Cluster start = null;
            Cluster end = null;
            for (Cluster cluster : clusters) {
                if (startName.equals(cluster.getName())) {
                    start = cluster;
                    break;
                }
            }
            for (Cluster cluster : clusters) {
                if (endName.equals(cluster.getName())) {
                    end = cluster;
                    break;
                }
            }
            listLines.add(new Ligne(name, start, end, coef));
        }
        binLines.close();
    }

    static void readPath(String filename, ArrayList<Chemin> listPaths, ArrayList<Ligne> listLines,
            ArrayList<Cluster> clusters) throws IOException {
        FileReader in = new FileReader(filename);
        BufferedReader binPaths = new BufferedReader(in);
        binPaths.readLine();
        while (binPaths.ready()) {
            ArrayList<Ligne> lines = new ArrayList<Ligne>();
            String line = binPaths.readLine();
            String[] tokens = line.split(";");
            String name = tokens[0].trim();
            String startName = tokens[1].trim();
            String endName = tokens[2].trim();
            int i = 0;
            for (String nameLine : tokens) {
                if (i >= 3){
                    Ligne ligne1 = null;
                    for (Ligne ligne2 : listLines) {
                        if (nameLine.equals(ligne2.getNom())) {
                            ligne1 = ligne2;
                            lines.add(ligne1);
                        }
                    }
                }
                i++;
            }
            Cluster start = null;
            Cluster end = null;
            for (Cluster cluster : clusters) {
                if (startName.equals(cluster.getName())) {
                    start = cluster;
                    break;
                }
            }
            for (Cluster cluster : clusters) {
                if (endName.equals(cluster.getName())) {
                    end = cluster;
                    break;
                }
            }
            listPaths.add(new Chemin(name, start, end, lines));
        }
        binPaths.close();
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

        ArrayList<ArrayList<Point>> listsProd = new ArrayList<ArrayList<Point>>();
        for (int i = 0; i < nbCluster; i++) {
            listsProd.add(new ArrayList<Point>());
        }
        readPoint(producersFile, nbCluster, namesClusters, listsProd, true);

        ArrayList<ArrayList<Point>> listsConso = new ArrayList<ArrayList<Point>>();
        for (int i = 0; i < nbCluster; i++) {
            listsConso.add(new ArrayList<Point>());
        }
        readPoint(consumersFile, nbCluster, namesClusters, listsConso, false);

        for (int i = 0; i < nbCluster; i++) {
            ArrayList<Point> pointsAL = listsConso.get(i);
            Point[] points = new Point[pointsAL.size()];
            points = pointsAL.toArray(points);
            double[] pos = positionsClusters.get(i);
            ArrayList<Point> prodsAL = listsProd.get(i);
            if (prodsAL.isEmpty()){
                clusters.add(new ClusterSansProd(namesClusters.get(i), points, pos[0], pos[1], null));
            } else {
                int nb = prodsAL.size();
                if (nb != 1) throw new IllegalArgumentException(
                    "Wrong number of Producers in the cluster. Expected 1, given " + nb + ".");
                    clusters.add(new ClusterAvecProd(namesClusters.get(i), points, pos[0], pos[1], prodsAL.get(0)));
            }
        }
        
        ArrayList<Ligne> listLines = new ArrayList<Ligne>();
        readLines(linesFile, namesClusters, listLines, namesClusters, clusters);

        ArrayList<Chemin> listPaths = new ArrayList<Chemin>();
        readPath(pathsFile, listPaths, listLines, clusters);


    Cluster[] clustersA = new Cluster[nbCluster];
        for (int i = 0; i < nbCluster; i++) {
            ArrayList<Point> prodsAL = listsProd.get(i);
            Cluster cluster = clusters.get(i);
            if (prodsAL.isEmpty()){
                ClusterSansProd clustersp = (ClusterSansProd) cluster;
                String name = cluster.getName();
                for (Chemin path : listPaths) {
                    String startName = path.getDépart().getName();
                    String endName = path.getArrivée().getName();
                    if ((name.equals(startName))
                            || (name.equals(endName))) {
                        clustersp.setChemin(path);
                    }
                }
                clustersA[i] = clustersp;
            } else {
                clustersA[i] = cluster;
            }
        }

        return clustersA;
    }

    public static void main(String[] args) throws IOException {
        Cluster[] listClusters = LoadingParam.readClusters();
        Simulation sim2 = new Simulation(listClusters);
        sim2.simOneDay(241, true);
    }
}
