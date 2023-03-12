package Graph;

import java.util.*;


public class Graph {
    // classe de graphe non orientés permettant de manipuler
    // en même temps des arcs (orientés)
    // pour pouvoir stocker un arbre couvrant, en plus du graphe

    public int order; // nombre de sommets
    int edgeCardinality; //nombre d'arete

    ArrayList<LinkedList<Edge>> adjacency; //est une liste qui contient, pour chaque sommet du graphe, une liste des arêtes qui le relient à ses voisinsin.
    ArrayList<LinkedList<Arc>> inAdjacency; //est une liste qui contient, pour chaque sommet du graphe, une liste des arcs qui arrivent à ce sommet.
    ArrayList<LinkedList<Arc>> outAdjacency; //est une liste qui contient, pour chaque sommet du graphe, une liste des arcs qui partent de ce sommet.

    public Graph(int upperBound) {
        this.order = upperBound;
        this.edgeCardinality = 0;
        this.adjacency = new ArrayList<>(upperBound);
        this.inAdjacency = new ArrayList<>(upperBound);
        this.outAdjacency = new ArrayList<>(upperBound);
        for (int i = 0; i < upperBound; i++) {
            adjacency.add(new LinkedList<>());
            inAdjacency.add(new LinkedList<>());
            outAdjacency.add(new LinkedList<>());
        }
    }

    public boolean isVertex(int index) { //verifie si appartient au graphe
        return index >= 0 && index < order;

    }
    public void addVertex(int indexVertex) { //ajoute un sommet au grpahe
        if (isVertex(indexVertex)) {
            adjacency.add(indexVertex, new LinkedList<>());
            inAdjacency.add(indexVertex, new LinkedList<>());
            outAdjacency.add(indexVertex, new LinkedList<>());
            order++;
        }
    }

    public void ensureVertex(int indexVertex) { //assure que le graphe possède au moins "indexvertex+1" sommets
        if (!isVertex(indexVertex)) {
            for (int i = order; i <= indexVertex; i++) {
                adjacency.add(new LinkedList<>());
                inAdjacency.add(new LinkedList<>());
                outAdjacency.add(new LinkedList<>());
            }
            order = indexVertex + 1;
        }
    }

    public void addArc(Arc arc) {
        if (isVertex(arc.getSource()) && isVertex(arc.getDest())) {
            outAdjacency.get(arc.getSource()).add(arc);
            inAdjacency.get(arc.getDest()).add(arc);
            edgeCardinality++;
        }
    }

    public void addEdge(Edge e) {
        if (isVertex(e.getSource()) && isVertex(e.getDest())) {
            adjacency.get(e.getSource()).add(e);
            adjacency.get(e.getDest()).add(e);
            // todo remplir aussi outaadjency et inadjency 4 instructions
            addArc(new Arc(e,false));
            addArc(new Arc(e,true));
            edgeCardinality++;
        }
    }

    public Arc[] outNeighbours(int sommet) { //renvoie la liste des arcs sortants d'un sommet donné.
        if (isVertex(sommet)) {
            return outAdjacency.get(sommet).toArray(new Arc[0]);
        }
            return null;
   }

    public int[] getSuccessors(int vertex) {
        if (isVertex(vertex)) {
            List<Integer> successors = new ArrayList<>();
            for (Edge edge : adjacency.get(vertex)) {
                int neighbor = edge.oppositeExtremity(vertex);
                successors.add(neighbor);
            }
            return successors.stream().mapToInt(i -> i).toArray();
        }
        return new int[0];
    }
    public int getEdgeCardinality() {
        return edgeCardinality;
    }

    public ArrayList<LinkedList<Edge>> getAdjacency() {
        return adjacency;
    }

    public List<Edge> getEdges() {
        Set<Edge> edges = new HashSet<>();
        for (int i = 0; i < order; i++) {
            edges.addAll(adjacency.get(i));
        }
        return new ArrayList<>(edges);
    }
    public int getOrder() {
        return order;
    }

    public int getRandomNeighbor(int current) {
        int[] successors = getSuccessors(current);
        if (successors.length == 0) {
            return -1;
        }
        return successors[(int) (Math.random() * successors.length)];

    }
}
