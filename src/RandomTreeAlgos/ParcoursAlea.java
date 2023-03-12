package RandomTreeAlgos;

import Graph.Edge;
import Graph.Graph;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ParcoursAlea {
    private final Graph graph;
    private final Random random;

    public ParcoursAlea(Graph graph) {
        this.graph = graph;
        this.random = new Random();
    }

    public ArrayList<Edge> generateRandomTree() {
        int root = random.nextInt(graph.order);
        System.out.println("Racine : " + root);
        return generateRandomTree(root);
    }

    public ArrayList<Edge> generateRandomTree(int root) {
        ArrayList<Edge> tree = new ArrayList<>();
        boolean[] reached = new boolean[graph.order];
        List<Integer> toExplore = new ArrayList<>();
        toExplore.add(root);
        reached[root] = true;
        while (!toExplore.isEmpty()) {
            int current = toExplore.remove(random.nextInt(toExplore.size()));
            for (int neighbor : graph.getSuccessors(current)) {
                if (!reached[neighbor]) {
                    tree.add(new Edge(current, neighbor, 0));
                    reached[neighbor] = true;
                    toExplore.add(neighbor);
                }
            }
        }
        return tree;
    }

}
