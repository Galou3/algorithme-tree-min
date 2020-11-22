package Graph;

import java.util.ArrayList;
import java.util.LinkedList;


public class Graph {
    // classe de graphe non orientés permettant de manipuler
    // en même temps des arcs (orientés)
    // pour pouvoir stocker un arbre couvrant, en plus du graphe

    public int order;
    int edgeCardinality;

    ArrayList<LinkedList<Edge>> adjacency;
    ArrayList<LinkedList<Arc>> inAdjacency;
    ArrayList<LinkedList<Arc>> outAdjacency;

    public Graph(int upperBound) {
        // à remplir
    }

    public boolean isVertex(int index) {
        // à remplir
        return true;
    }

    public void addVertex(int indexVertex) {
        // à remplir
    }

    public void ensureVertex(int indexVertex) {
        // à remplir
    }

    public void addArc(Arc arc) {
        // à remplir
    }

    public void addEdge(Edge e) {
        // à remplir
    }

    public Arc[] outNeighbours(int sommet) {
        // à remplir
        return outAdjacency.get(sommet).toArray(new Arc[0]);
   }

}
