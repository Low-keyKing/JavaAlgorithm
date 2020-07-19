package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//图
public class Graph {
    private ArrayList<String> vertexList;//用于存放图的结点
    private int[][] edges;//存放图对应的路径
    private int numOfEdges = 0;//表示路径(边)的数目
    private boolean[] isVisited;//用于判断结点是否已经遍历

    public static void main(String[] args) {
        //测试
        int n = 5;
        String[] vertices = {"A", "B", "C", "D", "E"};
        //创建图
        Graph graph = new Graph(n);
        //添加结点
        for (String vertex : vertices) {
            graph.insertVertex(vertex);
        }
        //添加路径
        //A-B A-C B-C B-D B-E
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 4, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        //显示图对应的矩阵
        graph.showEdges();
        //测试深度优先方法
        //graph.dfs();
        //测试广度优先方法
        graph.bfs();
    }

    public Graph(int n) {
        vertexList = new ArrayList<>(n);
        edges = new int[n][n];
        isVisited = new boolean[n];
    }

    //重载DFS方法
    public void dfs() {
        dfs(isVisited, 0);
    }

    //深度优先遍历
    public void dfs(boolean[] isVisited, int start) {
        //输出当前结点
        System.out.print("-->" + vertexList.get(start));
        //将当前结点设置为已访问
        isVisited[start] = true;
        //遍历当前结点后的所有结点，并从中找到当前结点的邻接结点，并对邻接结点进行处理
        for (int i = start + 1; i < vertexList.size(); i++) {
            //判断是否为当前结点的邻接结点，并且尚未访问
            if (edges[start][i] >= 1 && isVisited[i] == false) {
                dfs(isVisited, i);
            }
        }
    }

    //重载广度优先遍历
    public void bfs() {
        bfs(isVisited, 0);
    }

    //广度优先遍历
    public void bfs(boolean[] isVisited, int start) {
        //输出当前结点的值
        System.out.print("-->" + vertexList.get(start));
        //将当前结点设为已访问
        isVisited[start] = true;
        //遍历所有结点
        for (int i = start; i < vertexList.size(); i++) {
            //遍历但前节点后的所有节点，找到邻接结点
            for (int j = i + 1; j < vertexList.size(); j++) {
                //判断是否为当前结点的邻接结点，并且尚未访问
                if (edges[i][j] >= 1 && isVisited[j] == false) {
                    //输出该邻接结点
                    System.out.print("-->" + vertexList.get(j));
                    //并将其置为已访问
                    isVisited[j] = true;
                }
            }
        }
    }

    //图中常见的方法
    //获得结点的数目
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //获得边的数目
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //显示图对应的矩阵
    public void showEdges() {
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

    //返回下标对应的结点数值
    public String getValueByIndex(int index) {
        return vertexList.get(index);
    }

    //通过两个结点的下标，返回他们的权值
    public int getWeightByIndex(int v1, int v2) {
        return edges[v1][v2];
    }

    //插入结点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    //添加边
    //v1 v2代表要插入路径的两个结点，weight代表权值
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }


}
