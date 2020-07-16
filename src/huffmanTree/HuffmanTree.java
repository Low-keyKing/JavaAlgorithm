package huffmanTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    public static void main(String[] args) {
        int arr[] = {13, 7, 8, 3, 29, 6, 1};
        //测试
        System.out.println("哈夫曼树为：");
        Node root = createHuffmanTree(arr);
        root.preOrder();
    }

    public static Node createHuffmanTree(int[] arr) {
        //为了操作方便，将数组中的数都放入ArrayList中
        List<Node> nodes = new ArrayList<>();
        for (int i : arr) {
            nodes.add(new Node(i));
        }
        while (nodes.size() > 1) {//若为1，则代表哈夫曼树已经创建成功
            //将数据进行排序
            Collections.sort(nodes);
            //树中权值最小的节点
            Node leftNode = nodes.get(0);
            //树中权值第二小的节点
            Node rightNode = nodes.get(1);
            //创建一颗新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //删除原先的两个节点
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将新的二叉树放入ArrayList中
            nodes.add(parent);
        }
        //返回最后的根节点
        return nodes.get(0);
    }
}

class Node implements Comparable<Node> {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        //向左递归
        if (this.left != null) {
            this.left.preOrder();
        }
        //向右递归
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}