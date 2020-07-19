package avl;

public class AVLTreeDemo {
    public static void main(String[] args) {
        //int[] arr = {4, 3, 6, 5, 7, 8};
//        int[] arr = {10, 12, 8, 9, 7, 6};
        int[] arr = {10, 11, 7, 6, 8, 9};
        //创建一个avl树
        AVLTree avlTree = new AVLTree();
        //添加节点
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }

        System.out.println("树的高度=" + avlTree.getRoot().height());
        System.out.println("树的左子树高度=" + avlTree.getRoot().leftHeight());
        System.out.println("树的右子树高度=" + avlTree.getRoot().rightHeight());
        System.out.println("当前的根节点：" + avlTree.getRoot());
    }

}

class AVLTree {
    //根节点
    private Node root;

    public Node getRoot() {
        return root;
    }

    //删除节点
    public void delNode(int value) {
        if (root == null) {//判断根节点是否为空
            return;
        }
        //要删除的节点
        Node targetNode = search(value);
        if (targetNode == null) {//未找到待删除节点
            return;
        }
        if (root.left == null && root.right == null) {//若树只有根节点，则直接删除
            root = null;
        }
        //要删除节点的父节点
        Node targetParent = searchParent(value);
        if (targetNode != null) {
            if (targetNode.left == null && targetNode.right == null) {//待删除节点为叶子节点
                if (leftOrRight(targetParent, targetNode) == 0) {//父节点的左子节点为对应删除节点
                    targetParent.left = null;
                } else if (leftOrRight(targetParent, targetNode) == 1) {//父节点的右子节点为对应删除节点
                    targetParent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {//待删除节点既有左子树，又有右子树
                int minVal = delLeftest(targetNode.right);//获取待删除节点右子树的最小值
                targetNode.value = minVal;//并替换掉待删除节点
            } else {
                if (targetParent != null) {
                    if (targetNode.left != null) {//待删除节点只有左子树
                        if (leftOrRight(targetParent, targetNode) == 0) {//父节点的左子节点为对应删除节点
                            targetParent.left = targetNode.left;
                        } else if (leftOrRight(targetParent, targetNode) == 1) {//父节点的右子节点为对应删除节点
                            targetParent.right = targetNode.left;
                        }
                    } else if (targetNode.right != null) {//待删除节点只有右子树
                        if (leftOrRight(targetParent, targetNode) == 0) {//父节点的左子节点为对应删除节点
                            targetParent.left = targetNode.right;
                        } else if (leftOrRight(targetParent, targetNode) == 1) {//父节点的右子节点为对应删除节点
                            targetParent.right = targetNode.right;
                        }
                    }
                } else {//待删除节点的父节点为空，代表待删除节点为根节点，且其只有一个子树，则让该子树成为根节点即可
                    if (root.left != null) {
                        root = root.left;
                    } else if (root.right != null) {
                        root = root.right;
                    }
                }
            }
        } else {
            System.out.println("未查找到待删除节点！");
        }

    }

    //用于判断节点是父节点的左子节点还是右子节点
    public int leftOrRight(Node parent, Node son) {
        if (parent.left != null && parent.left.value == son.value) {//子节点为父节点的左子节点
            return 0;
        } else if (parent.right != null && parent.right.value == son.value) {//子节点为父节点的右子节点
            return 1;
        } else {
            return -1;
        }
    }

    //找到最左的节点并删除它，返回删除节点的值
    public int delLeftest(Node node) {
        if (node.left != null) {
            return delLeftest(node.left);
        } else {
            int value = node.value;
            delNode(node.value);
            return value;
        }
    }

    //根据给的数查找对应节点
    public Node search(int value) {
        if (root == null) {//如果根节点为空
            return null;
        }
        return root.search(value);
    }

    //根据给的数查找对应节点的父节点
    public Node searchParent(int value) {
        if (root == null) {//如果根节点为空
            return null;
        }
        return root.searchParent(value);
    }

    //添加
    public void add(Node node) {
        if (root == null) {//根节点为空
            root = node;
        } else {
            root.add(node);
        }
    }

    //遍历(中序)
    public void list() {
        if (root == null) {
            System.out.println("该树为空！");
        } else {
            root.infixOrder();
        }
    }
}

//创建一个节点类
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //获取左子树的高度
    public int leftHeight() {
        if (this.left == null) {
            return 0;
        }
        return this.left.height();
    }

    //获取右子树的高度
    public int rightHeight() {
        if (this.right == null) {
            return 0;
        }
        return this.right.height();
    }

    //获取树的高度
    public int height() {
        return Math.max(this.left == null ? 0 : this.left.height(), this.right == null ? 0 : this.right.height()) + 1;
    }

    //左旋转方法
    public void leftRotate() {
        //创建一个新的左子节点,新左子节点的值为当前根节点的值，因为根节点的地址是不变的，所以不能试图跟换根节点
        Node newLeftNode = new Node(this.value);
        //新左子节点的右指针指向当前根节点的右子树的左子树
        newLeftNode.right = this.right.left;
        //新根节点的左指针指向当前节点的左子树
        newLeftNode.left = this.left;
        //将当前节点的值换成当前节点的右子节点的值
        this.value = this.right.value;
        //当前节点的左指针指向新左子节点
        this.left = newLeftNode;
        //当前的根节点的右指针指向当前根节点的右子树的右子树
        this.right = this.right.right;
    }

    //右旋转方法
    public void rightRotate() {
        //创建一个新的右子节点，值为当前根节点的值
        Node newRightNode = new Node(this.value);
        //新右子节点的左子树为当前节点的左子树的右子树
        newRightNode.left = this.left.right;
        //新右子节点的右子树为当前节点的右子树
        newRightNode.right = this.right;
        //当前节点的值为当前节点的左节点的值
        this.value = this.left.value;
        //当前节点的左子树为当前节点的左子树的左子树
        this.left = this.left.left;
        //当前节点的右子节点为新右子节点
        this.right = newRightNode;

    }

    //根据value查找对应节点
    public Node search(int value) {
        //如果value与当前节点的值相等，则表明当前节点为要找的节点
        if (value == this.value) {
            return this;
        } else {
            if (value < this.value && this.left != null) {//如果value小于当前节点的值,且当前节点左子节点不为空，则向左查询
                return this.left.search(value);
            } else if (value >= this.value && this.right != null) {//若大于等于，向右查询
                return this.right.search(value);
            } else {
                return null;
            }
        }
    }

    //根据value查找对应节点的父节点
    public Node searchParent(int value) {
        //如果value与当前节点的左子节点或右子节点的值相等，且但前节点的左或右子节点不为空，则表明当前节点为要找节点的父节点
        if (this.left != null && value == this.left.value || this.right != null && value == this.right.value) {
            return this;
        }
        if (value < this.value && this.left != null) {
            //向左递归
            return this.left.searchParent(value);
        } else if (value >= this.value && this.right != null) {
            //向右递归
            return this.right.searchParent(value);
        } else {
            return null;
        }
    }

    //添加节点(满足二叉排序树的要求)
    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (node.value < this.value) {//若待添加数据小于当前数据，则加入当前数据的左子节点
            if (this.left == null) {
                this.left = node;
            } else {//若左子节点不为空，则遍历左子树
                this.left.add(node);
            }
        } else {//反之，则加入右子节点
            if (this.right == null) {
                this.right = node;
            } else {//若右子节点不为空，则遍历右子树
                this.right.add(node);
            }
        }
        //判断是否需要左旋转
        if (this.rightHeight() - this.leftHeight() > 1) {
            if (this.right != null && this.right.leftHeight() > this.right.rightHeight()) {
                //若当前节点的右子节点的左子树的高度大于其右子树的高度，则要对当前节点的右子节点先进行右旋转
                this.right.rightRotate();
            }
            this.leftRotate();

        } else if (this.leftHeight() - this.rightHeight() > 1) {//判断是否需要右旋转
            if (this.left != null && this.left.rightHeight() > this.left.leftHeight()) {
                //若当前节点的左子节点的右子树的高度大于其左子树的高度，则要对当前节点的左子节点先进行左旋转
                this.left.leftRotate();
            }
            this.rightRotate();
        }
    }

    //中序遍历
    public void infixOrder() {
        //先遍历左子树
        if (this.left != null) {
            this.left.infixOrder();
        }
        //输出当前节点
        System.out.println(this);
        //遍历右子树
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
}
