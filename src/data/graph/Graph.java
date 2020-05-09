package data.graph;

import java.util.LinkedList;
import java.util.Queue;

// 无向图
public class Graph {
    private int v; // 顶点的个数
    private LinkedList<Integer> adj[]; // 邻接表
    private boolean found = false; // 深入优先搜索标志位

    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    // 无向图一条边存两次
    public void addEdge(int s, int t) {
        adj[s].add(t);
        adj[t].add(s);
    }

    /**
     * 广度优先搜索
     *
     * @param s 起始顶点
     * @param t 要查找的顶点
     */
    public void bfs(int s, int t) {
        if (s == t) return;
        // 记录已经被访问的顶点
        boolean[] visited = new boolean[v];
        visited[s] = true;
        // 用来存储已经被访问、但相连的顶点还没有被访问的顶点
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        // 用来记录搜索路径
        int[] prev = new int[v];
        for (int i = 0; i < prev.length; i++) {
            prev[i] = -1;
        }
        // 循环队列，直到队列元素个数为0
        while (queue.size() != 0) {
            // 出队一个元素，获取到该点的所在邻接表中的链表
            int w = queue.poll();
            LinkedList<Integer> list = adj[w];
            // 遍历链表查找需要的顶点
            for (Integer q : list) {
                // 判断当前顶点是否被访问过
                if (!visited[q]) {
                    prev[q] = w;
                    if (q == t) {
                        print(prev, s, t);
                        return;
                    }
                    visited[q] = true;
                    queue.add(q);
                }
            }
        }
    }

    /**
     * 深度优先搜索
     * @param s
     * @param t
     */
    public void dfs(int s, int t) {
        if (s == t) return;
        found = false;
        boolean[] visited = new boolean[v];
        visited[s] = true;
        int[] prev = new int[v];
        for (int i = 0; i < prev.length; i++) {
            prev[i] = -1;
        }
        recurDfs(s, t, visited, prev);
        print(prev, s, t);
    }

    private void recurDfs(int w, int t, boolean[] visited, int[] prev) {
        if (found == true) return;
        visited[w] = true;
        if (w == t) {
            found = true;
            return;
        }
        for (int i = 0; i < adj[w].size(); i++) {
            int q = adj[w].get(i);
            if (!visited[q]) {
                prev[q] = w;
                recurDfs(q, t, visited, prev);
            }
        }
    }

    private void print(int[] prev, int s, int t) { // 递归打印s->t的路径
        if (prev[t] != -1 && t != s) {
            print(prev, s, prev[t]);
        }
        System.out.print(t + " ");
    }

    public static void main(String[] args) {
        Graph graph = new Graph(8);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 7);
        graph.dfs(0, 7);
    }
}
