package tree;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        //测试中序线索二叉树
        heroNode root = new heroNode(1, "tom");
        heroNode heroNode2 = new heroNode(3, "ace");
        heroNode heroNode3 = new heroNode(6, "smith");
        heroNode heroNode4 = new heroNode(8, "mary");
        heroNode heroNode5 = new heroNode(10, "king");
        heroNode heroNode6 = new heroNode(14, "dim");


        //二叉树手动创建
        root.setLeft(heroNode2);
        root.setRight(heroNode3);
        heroNode2.setLeft(heroNode4);
        heroNode2.setRight(heroNode5);
        heroNode3.setLeft(heroNode6);

        //测试线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
//        threadedBinaryTree.minthreadedNodes();
        threadedBinaryTree.latethreadedNodes();

        //遍历线索化二叉树
        threadedBinaryTree.threadedList();

        //测试
//        heroNode leftNode = heroNode5.getLeft();
//        heroNode rightNode = heroNode5.getRight();
//        System.out.println(leftNode);
//        System.out.println(rightNode);

    }
}

//创建线索化二叉树
class ThreadedBinaryTree {
    //创建根节点
    private heroNode root;
    //创建变量用于指向前驱节点
    private heroNode pre = null;

    public void setRoot(heroNode root) {
        this.root = root;
    }

    //重载线索化方法
    public void minthreadedNodes() {
        this.minthreadedNodes(root);
    }

    //重载线索化方法
    public void latethreadedNodes() {
        this.latethreadedNodes(root);
    }

    //遍历线索化二叉树
    public void threadedList() {
        //定义一个变量，用于储存当前节点,从root开始
        heroNode node = root;
        while (node != null) {
            //先一直遍历到最左边的子节点,即左指针的类型为1
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            //输出当前节点
            System.out.println(node);
            //然后一直沿着节点的后继指针找下去(即右指针类型为1)，找的过程不断输出后继节点
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            //替换这个遍历的节点
            node = node.getRight();
        }
    }


    //对二叉树进行中序线索化
    public void minthreadedNodes(heroNode node) {
        //node == null则不能线索化
        if (node == null) {
            return;
        }
        //向左递归线索化左子树
        minthreadedNodes(node.getLeft());
        //线索化当前节点
        //前驱节点
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        //后继节点
        if (pre != null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        //将pre指向当前节点
        pre = node;
        //向右递归线索化右子树
        minthreadedNodes(node.getRight());
    }

    //对二叉树进行后序线索化
    public void latethreadedNodes(heroNode node) {
        //node == null则不能线索化
        if (node == null) {
            return;
        }
        //向左递归线索化左子树
        latethreadedNodes(node.getLeft());
        //向右递归线索化右子树
        minthreadedNodes(node.getRight());
        //线索化当前节点
        //前驱节点
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        //后继节点
        if (pre != null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        //将pre指向当前节点
        pre = node;
    }

    //前序遍历
    public void preOrder() {
        //判断根节点是否为空
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("根节点为空！");
        }
    }

    //中序遍历
    public void infixOrder() {
        //判断根节点是否为空
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("根节点为空！");
        }
    }

    //后序遍历
    public void postOrder() {
        //判断根节点是否为空
        if (root != null) {
            root.postOrder();
        } else {
            System.out.println("根节点为空！");
        }
    }

    //前序遍历查找
    public heroNode preOrderSearch(int no) {
        //判断根节点是否为空
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            System.out.println("根节点为空！");
            return null;
        }
    }

    //中序遍历查找
    public heroNode infixOrderSearch(int no) {
        //判断根节点是否为空
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            System.out.println("根节点为空！");
            return null;
        }
    }

    //前序遍历查找
    public heroNode postOrderSearch(int no) {
        //判断根节点是否为空
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            System.out.println("根节点为空！");
            return null;
        }
    }

    //删除节点
    public void delete(int no) {
        //判断根节点是否为待删除节点
        if (root != null) {
            if (root.getNo() == no) {
                root = null;
            } else {
                //若不是，则递归删除
                root.delete(no);
            }
        } else {
            System.out.println("空树，不能删除！");
        }
    }

}

//先创建Hero节点
class heroNode {
    private int no;
    private String name;
    private heroNode left;
    private heroNode right;
    //左指针和右指针类型，0为指向子节点，1为指向前后驱节点
    private int leftType = 0;
    private int rightType = 0;

    public heroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public heroNode getLeft() {
        return left;
    }

    public void setLeft(heroNode left) {
        this.left = left;
    }

    public heroNode getRight() {
        return right;
    }

    public void setRight(heroNode right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "heroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //编写前序遍历
    public void preOrder() {
        System.out.println(this);//先输出父节点
        //递归向左子树遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        //递归向右子树遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //编写中序遍历
    public void infixOrder() {
        //先递归向左子树遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        System.out.println(this);//输出父节点
        //递归向右子树遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //编写后序遍历
    public void postOrder() {
        //先递归向左子树遍历
        if (this.left != null) {
            this.left.postOrder();
        }
        //递归向右子树遍历
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);//输出父节点
    }

    //前序遍历查找
    public heroNode preOrderSearch(int no) {
        //判断当前节点是否为要找的节点
        if (this.no == no) {
            return this;
        }
        //查找结果
        heroNode result = null;
        //向左递归遍历
        if (this.left != null) {
            result = this.left.preOrderSearch(no);
        }
        //判断左递归是否找到
        if (result != null) {
            return result;
        }
        //向右递归遍历
        if (this.right != null) {
            result = this.right.preOrderSearch(no);
        }
        return result;
    }

    //中序遍历查找
    public heroNode infixOrderSearch(int no) {
        //查找结果
        heroNode result = null;
        //向左递归遍历
        if (this.left != null) {
            result = this.left.infixOrderSearch(no);
        }
        //判断左递归是否找到
        if (result != null) {
            return result;
        }
        //判断当前节点是否为要找的节点
        if (this.no == no) {
            return this;
        }
        //向右递归遍历
        if (this.right != null) {
            result = this.right.infixOrderSearch(no);
        }
        return result;
    }

    //后序遍历查找
    public heroNode postOrderSearch(int no) {
        //查找结果
        heroNode result = null;
        //向左递归遍历
        if (this.left != null) {
            result = this.left.postOrderSearch(no);
        }
        //判断左递归是否找到
        if (result != null) {
            return result;
        }
        //向右递归遍历
        if (this.right != null) {
            result = this.right.postOrderSearch(no);
        }
        //判断右递归是否找到
        if (result != null) {
            return result;
        }
        //判断当前节点是否为要找的节点
        if (this.no == no) {
            return this;
        }
        return result;
    }

    //删除节点
    //因为二叉树是单向的，所以我们不能判断当前的节点是不是待删除的节点，只能判断该节点的子节点是不是待删除的节点
    public void delete(int no) {
        //判断左子节点是不是待删除节点
        if (this.left != null && this.left.no == no) {
            this.left = null;
        }
        //判断右子节点是不是待删除节点
        if (this.right != null && this.right.no == no) {
            this.right = null;
        }
        //向左递归
        if (this.left != null) {
            this.left.delete(no);
        }
        //向右递归
        if (this.right != null) {
            this.right.delete(no);
        }
    }
}
