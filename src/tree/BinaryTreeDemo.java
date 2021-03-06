package tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        //测试
        BinaryTree binaryTree = new BinaryTree();
        //创建节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode heroNode2 = new HeroNode(2, "吴用");
        HeroNode heroNode3 = new HeroNode(3, "林冲");
        HeroNode heroNode4 = new HeroNode(4, "卢俊义");
        //手动组建二叉树
        root.setLeft(heroNode2);
        root.setRight(heroNode3);
        heroNode3.setRight(heroNode4);
        //设置根节点
        binaryTree.setRoot(root);
//        System.out.println("前序遍历");
//        binaryTree.preOrder();
//        System.out.println("中序遍历");
//        binaryTree.infixOrder();
//        System.out.println("后序遍历");
//        binaryTree.postOrder();
//        int no = 1;
//        System.out.println("编号为" + no + "的英雄为：" + binaryTree.preOrderSearch(no));

        System.out.println("删除前，前序遍历");
        binaryTree.preOrder();
        binaryTree.delete(3);
        System.out.println("删除后，前序遍历");
        binaryTree.preOrder();
    }
}

//创建二叉树
class BinaryTree {
    //创建根节点
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
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
    public HeroNode preOrderSearch(int no) {
        //判断根节点是否为空
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            System.out.println("根节点为空！");
            return null;
        }
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        //判断根节点是否为空
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            System.out.println("根节点为空！");
            return null;
        }
    }

    //前序遍历查找
    public HeroNode postOrderSearch(int no) {
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
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
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

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
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
    public HeroNode preOrderSearch(int no) {
        //判断当前节点是否为要找的节点
        if (this.no == no) {
            return this;
        }
        //查找结果
        HeroNode result = null;
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
    public HeroNode infixOrderSearch(int no) {
        //查找结果
        HeroNode result = null;
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
    public HeroNode postOrderSearch(int no) {
        //查找结果
        HeroNode result = null;
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
