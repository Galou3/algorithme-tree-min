package RandomTreeAlgos;
import Graph.Edge;
import Graph.Graph;
import java.util.ArrayList;
import java.util.Random;

public class AldousBroder {

    private final Random random;
    private final Graph graph;

    public AldousBroder(Graph graph) {
        this.graph = graph;
        this.random = new Random();
    }

    public ArrayList<Edge> generateRandomTree() {
        int Actuel = random.nextInt(graph.order);
        boolean[] atteint = new boolean[graph.order];
        atteint[Actuel] = true;
        ArrayList<Edge> tree = new ArrayList<>();
        while (tree.size() < graph.order - 1) {
            int neighbor = graph.getRandomNeighbor(Actuel);
            if (!atteint[neighbor]) {
                tree.add(new Edge(Actuel, neighbor, 0));
                atteint[neighbor] = true;
            }
            Actuel = neighbor;
        }
        return tree;
    }
}