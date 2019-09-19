package chapter28;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGraph<V> implements Graph<V> {

    protected List<V> vertices = new ArrayList<>(); // store(存储) vertex
    protected List<List<Edge>> neighbors = new ArrayList<>(); // adjacency lists 邻接线性表

    protected AbstractGraph() {

    }

    // construct a graph from vertices and edges stored in arrays
    protected AbstractGraph(V[] vertices, int[][] edges) {
        for (int i = 0; i < vertices.length; i++)
            addVertex(vertices[i]);
        createAdjacencyLists(edges, vertices.length);
    }

    // construct a graph from vertices and edges stored in List
    protected AbstractGraph(List<V> vertices, List<Edge> edges) {
        for (int i = 0; i < vertices.size(); i++) {
            addVertex(vertices.get(i));
        }

        createAdjacencyLists(edges, vertices.size());
    }

    // construct a graph for integer vertices 0, 1, 2, 3, 4, 5, 6 and edges List
    protected AbstractGraph(List<Edge> edges, int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++) {
            addVertex((V) (new Integer(i))); // vertex is {0, 1, 2, 3, 4}
        }
        createAdjacencyLists(edges, numberOfVertices);
    }

    // construct a graph from integer vertices 0, 1, 2 and edge arrays
    protected AbstractGraph(int[][] edges, int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++) {
            addVertex((V) (new Integer(i)));
        }

        createAdjacencyLists(edges, numberOfVertices);
    }

    private void createAdjacencyLists(int[][] edges, int numberOfVertices) {
        for (int i = 0; i < edges.length; i++) {
            addEdge(edges[i][0], edges[i][1]);
        }
    }

    private void createAdjacencyLists(List<Edge> edges, int numberOfVertices) {
        for (Edge edge : edges) {
            addEdge(edge.getU(), edge.getV());
        }
    }

    @Override
    public int getSize() {
        return vertices.size();
    }

    @Override
    public List<V> getVertices() {
        return vertices;
    }

    @Override
    public V getVertex(int index) {
        return vertices.get(index);
    }

    @Override
    public int getIndex(V v) {
        return vertices.indexOf(v);
    }

    @Override
    public List<Integer> getNeighbors(int index) {
        ArrayList<Integer> result = new ArrayList<>();
        for (Edge edge : neighbors.get(index))
            result.add(edge.getV());
        return result;
    }

    // 返回顶点的 度
    @Override
    public int getDegree(int index) {

        return neighbors.get(index).size();
    }

    @Override
    public void printEdges() {
        for (int u = 0; u < neighbors.size(); u++) {
            System.out.print(getVertex(u) + " (" + u + " ): ");
            for (Edge edge : neighbors.get(u)) {
                System.out.print("(" + getVertex(edge.getU()) + ", " + getVertex(edge.v) + ")");
                System.out.print("(" + edge.getU() + ", " + edge.getV() + ")");
            }
            System.out.println();
        }
    }

    @Override
    public void clear() {
        vertices.clear();
        neighbors.clear();
    }

    @Override
    public boolean addVertex(V v) {
        if (!vertices.contains(v)) {
            vertices.add(v);
            neighbors.add(new ArrayList<Edge>());
            return true;
        }
        return false;
    }

    @Override
    public boolean addEdge(int u, int v) {

        return addEdge(new Edge(u, v));
    }

    protected boolean addEdge(Edge edge) {
        if (edge.getU() < 0 || edge.getU() > getSize() - 1)
            throw new IllegalArgumentException("No such index: " + edge.getU());
        if (edge.getV() < 0 || edge.getV() > getSize() - 1)
            throw new IllegalArgumentException("No such index: " + edge.getV());
        if (!neighbors.get(edge.getU()).contains(edge)) {
            neighbors.get(edge.getU()).add(edge);
            return true;
        }
        return false;

    }

    public static class Edge {
        private int u; // starting vertex of the edge
        private int v; // ending vertex of the edge

        public Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }

        public int getU() {
            return u;
        }

        public int getV() {
            return v;
        }

        @Override
        public boolean equals(Object obj) {
            return this.u == ((Edge) obj).getU() && this.v == ((Edge) obj).getV();
        }
    }

    @Override
    public Tree dfs(int v) {
        ArrayList<Integer> searchOrder = new ArrayList<>();
        int[] parent = new int[vertices.size()];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = -1;
        }
        boolean[] isVisited = new boolean[vertices.size()];

        dfs(v, parent, searchOrder, isVisited);
        return new Tree(v, parent, searchOrder);
    }

    private void dfs(int u, int[] parent, List<Integer> searchOrder, boolean[] isVisited) {
        searchOrder.add(u);
        isVisited[u] = true;
        for (Edge edge : neighbors.get(u)) {
            if (!isVisited[edge.getV()]) {
                parent[edge.getV()] = u;
                dfs(edge.getV(), parent, searchOrder, isVisited);
            }
        }
    }

    @Override
    public Tree bfs(int v) {
        return null;
    }

    public class Tree {
        private int root;
        private int[] parent; // 当前的数组的索引位代表 顶点数组的下标，存储的值为父顶点
        private List<Integer> searchOrder;// 从某一个顶点遍历完之后生成一棵树，searchOrder 存储了遍历的步骤，从那些顶点开始遍历

        public Tree(int root, int[] parent, List<Integer> searchOrder) {
            this.root = root;
            this.parent = parent;
            this.searchOrder = searchOrder;
        }

        // 从某一个结点开始遍历，这个定点就是根顶点
        public int getRoot() {
            return root;
        }

        // 从某一个顶点遍历完之后生成一棵树，searchOrder 存储了遍历的步骤，从那些顶点开始遍历
        // 返回某一个点的父顶点
        public int getParent(int v) {
            return parent[v];
        }

        // 返回此次遍历 遍历到了多少顶点
        public int getNumberOfVerticesFound() {
            return searchOrder.size();
        }

        public List<V> getPath(int currentIndex) {
            ArrayList<V> path = new ArrayList<>();
            do {
                path.add(vertices.get(currentIndex));
                currentIndex = parent[currentIndex]; // 变成了当前索引的父索引
            } while (currentIndex != -1); // 当一个顶点没有父顶点的时候，其 parent[i] = -1;
            return path;
        }

        public void printPath(int currentIndex) {
            List<V> path = getPath(currentIndex);
            System.out.print("A path from " + vertices.get(root)
                    + " to " + vertices.get(currentIndex) + " : ");
            for (int i = path.size() - 1; i >= 0; i++) {
                System.out.print(path.get(i) + " ");
            }
        }

        public void printTree() {
            System.out.println("Root is " + vertices.get(root));
            System.out.print("Edges : ");
            for (int i = 0; i < parent.length; i++) {
                if (-1 != parent[i]) {
                    System.out.print("(" + vertices.get(parent[i]) + ", " + vertices.get(i) + ") ");
                }
                System.out.println();
            }
        }
    }
}
