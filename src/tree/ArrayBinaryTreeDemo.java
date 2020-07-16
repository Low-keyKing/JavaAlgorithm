package tree;

public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        //创建一个数列
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        //创建一个二叉树
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        System.out.println("前序");
        arrayBinaryTree.preOrder();
        System.out.println();
        System.out.println("中序");
        arrayBinaryTree.infixOrder();
        System.out.println();
        System.out.println("后序");
        arrayBinaryTree.postOrder();
    }
}

class ArrayBinaryTree {
    private int[] arr;//有存储数据节点的数组

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //重载preOrder方法
    public void preOrder() {
        this.preOrder(0);
    }

    //重载infixOrder方法
    public void infixOrder() {
        this.infixOrder(0);
    }

    //重载postOrder方法
    public void postOrder() {
        this.postOrder(0);
    }

    //顺序存储二叉树的前序遍历
    public void preOrder(int index) {
        //父节点的左子节点下标为2*n + 1
        //右子节点为2*n + 2
        //节点的父节点为 (n-1)/2
        if (arr == null || arr.length == 0) {//数组为空
            System.out.println("数组为空，无法进行遍历！");
        }
        //输出当前这个元素
        System.out.print(arr[index] + " ");
        //向左递归遍历
        if (2 * index + 1 < arr.length) {
            preOrder(2 * index + 1);
        }
        //向右递归遍历
        if (2 * index + 2 < arr.length) {
            preOrder(2 * index + 2);
        }
    }

    //顺序存储二叉树的中序遍历
    public void infixOrder(int index) {
        //父节点的左子节点下标为2*n + 1
        //右子节点为2*n + 2
        //节点的父节点为 (n-1)/2
        if (arr == null || arr.length == 0) {//数组为空
            System.out.println("数组为空，无法进行遍历！");
        }
        //向左递归遍历
        if (2 * index + 1 < arr.length) {
            infixOrder(2 * index + 1);
        }
        //输出当前这个元素
        System.out.print(arr[index] + " ");
        //向右递归遍历
        if (2 * index + 2 < arr.length) {
            infixOrder(2 * index + 2);
        }
    }

    //顺序存储二叉树的后序遍历
    public void postOrder(int index) {
        //父节点的左子节点下标为2*n + 1
        //右子节点为2*n + 2
        //节点的父节点为 (n-1)/2
        if (arr == null || arr.length == 0) {//数组为空
            System.out.println("数组为空，无法进行遍历！");
        }
        //向左递归遍历
        if (2 * index + 1 < arr.length) {
            postOrder(2 * index + 1);
        }
        //向右递归遍历
        if (2 * index + 2 < arr.length) {
            postOrder(2 * index + 2);
        }
        //输出当前这个元素
        System.out.print(arr[index] + " ");
    }
}
