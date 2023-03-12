package RandomTreeAlgos;
import Graph.*;

import java.util.*;

public class TreeMin{
    private final Graph graph;
    private final Random random;

    public TreeMin(Graph graph) {
        this.graph = graph;
        this.random = new Random();
    }

    public ArrayList<Edge> kruskal() {
        int n = graph.order;
        ArrayList<Edge> edges = new ArrayList<>(graph.getEdgeCardinality());
        for (int i = 0; i < n; i++) {
            edges.addAll(graph.getAdjacency().get(i));
        }
        Collections.sort(edges);
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        ArrayList<Edge> tree = new ArrayList<>();
        for (Edge e : edges) {
            int u = e.getSource();
            int v = e.getDest();
            int rootU = find(parent, u);
            int rootV = find(parent, v);
            if (rootU != rootV) {
                tree.add(e);
                parent[rootU] = rootV;
            }
        }
        return tree;
    }

    private static int find(int[] parent, int u) {
        if (parent[u] == u) {
            return u;
        }
        return parent[u] = find(parent, parent[u]);
    }

}