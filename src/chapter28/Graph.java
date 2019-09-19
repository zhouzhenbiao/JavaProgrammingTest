package chapter28;

public interface Graph<V> {
    // 返回图中的顶点数
    int getSize();

    // 返回途中的顶点
    java.util.List<V> getVertices();

    // 返回指定顶点下标的顶点对象
    V getVertex(int index);

    // 返回指定顶点的下标
    int getIndex(V v);

    // 返回指定下标的顶点的邻居
    java.util.List<Integer> getNeighbors(int index);

    // 返回指定顶点的 度
    int getDegree(int index);

    // 打印所有的边
    void printEdges();

    //清楚图
    void clear();

    boolean addVertex(V v);

    // 添加从 u 到 v 的边到图中，如果 u 或者 v 无效，则抛出异常 IllegalArgumentException，如果 （u， v）在图中返回 false
    boolean addEdge(int u, int v);

    // 得到从 v 开始的 deep 深度优先遍历搜索树
    AbstractGraph<V>.Tree dfs(int v);

    // 得到从 v 开始的 广度优先遍历搜索树
    AbstractGraph<V>.Tree bfs(int v);
}
