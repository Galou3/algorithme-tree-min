package RandomTreeAlgos;

import Graph.Arc;
import Graph.Graph;

import java.util.*;


public class BreadthFirstSearch {

	Graph graph; //un graphe
	Queue<Arc> frontier; //file d'attente qui contient les sommets
	ArrayList<Arc> tree; //liste qui contient les arcs du graphe
	BitSet reached; //pour chaque sommet s'il a été atteint ou non
	
	private void push(int vertex) {

		for (Arc arc : graph.outNeighbours(vertex)) frontier.offer(arc);
	}
	
	private void explore(Arc nextArc) {
		if (reached.get(nextArc.getDest())) return;
		reached.set(nextArc.getDest());
		tree.add(nextArc);
		push(nextArc.getDest());
	}

	private void bfs(int startingVertex) {
		reached.set(startingVertex);
		push(startingVertex);
		while (!frontier.isEmpty()) {
			explore(frontier.poll());
		}
	}
	
	private BreadthFirstSearch (Graph graph) {
		this.graph = graph;
		this.frontier = new LinkedList<>();
		this.tree = new ArrayList<>();
		this.reached = new BitSet(graph.order);
	}
	
	public static ArrayList<Arc> generateTree(Graph graph, int root) {
		BreadthFirstSearch algo = new BreadthFirstSearch(graph);
		algo.bfs(root);
		return algo.tree;
	}

}
