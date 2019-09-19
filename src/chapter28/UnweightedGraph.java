package chapter28;

import java.util.List;

public class UnweightedGraph<V> extends AbstractGraph<V> {
    public UnweightedGraph() {

    }

    protected UnweightedGraph(V[] vertices, int[][] edges) {
        super(vertices, edges);
    }

    // construct a graph from vertices and edges stored in List
    protected UnweightedGraph(List<V> vertices, List<Edge> edges) {
        super(vertices, edges);
    }

    // construct a graph for integer vertices 0, 1, 2, 3, 4, 5, 6 and edges List
    protected UnweightedGraph(List<Edge> edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }

    // construct a graph from integer vertices 0, 1, 2 and edge arrays
    protected UnweightedGraph(int[][] edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }
}
