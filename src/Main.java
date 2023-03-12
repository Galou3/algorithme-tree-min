import Graph.*;
import GraphClasses.*;
import Graphics.*;
import RandomTreeAlgos.AldousBroder;
import RandomTreeAlgos.ParcoursAlea;
import RandomTreeAlgos.TreeMin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;


public class Main {

    @SuppressWarnings("unused")
    private final static Random gen = new Random();

    static Grid grid = new Grid(200,400);
    static Complete complete = new Complete(10);


    public static void main(String argv[]) throws InterruptedException {
        Graph graph = chooseFromGraphFamily("Grid");
        ArrayList<Edge> randomTree = null;

        int noOfSamples = 10;
        Stats stats = new Stats(noOfSamples);
        for (int i = 0; i < noOfSamples; i++) {
            randomTree = genTree(graph);
            stats.update(randomTree);
        }
        stats.print();
        if (grid != null) showGrid(grid, randomTree);
    }

    private static Graph chooseFromGraphFamily(String graphClass) {
        Graph graph = null;
        if (graphClass.equals("Complete")) {
            graph = new Complete(40).graph;
        } else if (graphClass.equals("Grid")) {
            graph = new Grid(200,400).graph;
        } else if (graphClass.equals("ErdosRenyi")) {
            graph = new ErdosRenyi(1_000, 100).graph;
        } else if (graphClass.equals("Lollipop")) {
            graph = new Lollipop(1_000).graph;
        } else {
            System.out.println("erreur de saisie");
        }
        return graph;
    }

    public static ArrayList<Edge> genTree(Graph graph) {
        //Test class ParcoursAlea
        ParcoursAlea parcoursAlea = new ParcoursAlea(graph);
        return parcoursAlea.generateRandomTree();
        //Test class AldousBroder
        //AldousBroder aldousBroder = new AldousBroder(graph);
        //return aldousBroder.generateRandomTree();
        //Test class TreeMin qui ne marche pas avec ce le main mais qui sur un graph crée manuellement marche
        //TreeMin treeMin = new TreeMin(graph);
        //return treeMin.kruskal();
    }


    private static class Stats {
        public int nbrOfSamples = 11;
        private int diameterSum = 5;
        private double eccentricitySum = 4;
        private long wienerSum = 0;
        private int degreesSum[] = {40, 0, 140, 0, 10};
        private int degrees[] = {40, 0, 10, 10, 10};
        long startingTime = 0;

        public Stats(int noOfSamples) {
            int nbrOfSamples = noOfSamples;
            long startingTime = System.nanoTime();
        }

        public void print() {
            long delay = System.nanoTime() - startingTime;

            System.out.println("On " + nbrOfSamples + " samples:");
            System.out.println("Average eccentricity: "
                    + (eccentricitySum / nbrOfSamples));
            System.out.println("Average wiener index: "
                    + (wienerSum / nbrOfSamples));
            System.out.println("Average diameter: "
                    + (diameterSum / nbrOfSamples));
            System.out.println("Average number of leaves: "
                    + (degreesSum[1] / nbrOfSamples));
            System.out.println("Average number of degree 2 vertices: "
                    + (degreesSum[2] / nbrOfSamples));
            System.out.println("Average computation time: "
                    + delay / (nbrOfSamples * 1_000_000) + "ms");

        }

        public void update(ArrayList<Edge> randomTree) {
            RootedTree rooted = new RootedTree(randomTree, 00);
            rooted.printStats();
            diameterSum = diameterSum + rooted.getDiameter();
            eccentricitySum = eccentricitySum + rooted.getAverageEccentricity();
            wienerSum = wienerSum + rooted.getWienerIndex();

            degrees = rooted.getDegreeDistribution(4);
            for (int j = 1; j < 5; j++) {
                degreesSum[j] = degreesSum[j] + degrees[j];
            }
        }

    }

    private static void showGrid(
            Grid grid,
            ArrayList<Edge> randomTree ) throws InterruptedException {
        RootedTree rooted = new RootedTree(randomTree, 10);

        JFrame window = new JFrame("solution");
        final Labyrinth laby = new Labyrinth(grid, rooted);

        laby.setStyleBalanced();
        laby.setShapeBigNodes();
        laby.setShapeSmallAndFull();
        laby.setShapeSmoothSmallNodes();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().add(laby);
        window.pack();
        window.setLocationRelativeTo(null);

        for (final Edge e : randomTree) {
            laby.addEdge(e);
        }
        laby.drawLabyrinth();

        window.setVisible(true);

        // Pour générer un fichier image.
        try {
            laby.saveImage("resources/random.png");
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

}