package NonlinearStructure.Tree.binarytree.threadedbinarytree;


/**
 * @author tomable  线索化二叉树
 * @create 2021-09-29-16:49
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNodes root = new HeroNodes(1, "AA");
        HeroNodes node2 = new HeroNodes(3, "BB");
        HeroNodes node3 = new HeroNodes(6, "CC");
        HeroNodes node4 = new HeroNodes(8, "DD");
        HeroNodes node5 = new HeroNodes(10, "EE");
        HeroNodes node6 = new HeroNodes(14, "FF");

        //手动处理
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();
        HeroNodes leftNode = node5.getLeft();
        System.out.println("前" + leftNode);   //3
        HeroNodes rightNode = node5.getRight();
        System.out.println("后" + rightNode);  //1

        //遍历线索化二叉树
        System.out.println("线索化遍历线索化二叉树");
        threadedBinaryTree.threadedList();  //8,3,10,1,14,6
    }
}

//创建节点
class HeroNodes {
    private int no;
    private String name;
    private HeroNodes left;   //默认为空
    private HeroNodes right;  //默认为空

    //说明：
    //1. leftType  为0指向左子树，为1指向前驱
    //2. rightType 为0指向右子树，为1指向后继
    private int leftType;
    private int rightType;

    public HeroNodes(int no, String name) {
        this.no = no;
        this.name = name;
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

    public HeroNodes getLeft() {
        return left;
    }

    public void setLeft(HeroNodes left) {
        this.left = left;
    }

    public HeroNodes getRight() {
        return right;
    }

    public void setRight(HeroNodes right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HerNodes{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //递归删除节点
    public void delNode(int no) {
        if (this.left != null && this.left.no == no) {    //左子树置空
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {  //右子树置空
            this.right = null;
            return;
        }
        if (this.left != null) {   //左子树递归
            this.left.delNode(no);
        }
        if (this.right != null) {  //右子树递归
            this.right.delNode(no);
        }
    }

    //前序遍历的方法
    public void preOrder() {
        System.out.println(this);   //输出父节点

        if (this.left != null) {   //向左递归
            this.left.preOrder();
        }

        if (this.right != null) {  //向右子树遍历
            this.right.preOrder();
        }
    }

    //中序遍历的方法
    public void infixOrder() {
        if (this.left != null) {   //向左递归
            this.left.infixOrder();
        }

        System.out.println(this);   //输出父节点

        if (this.right != null) {  //向右子树遍历
            this.right.infixOrder();
        }
    }

    //后序遍历的方法
    public void postOrder() {
        if (this.left != null) {   //向左递归
            this.left.postOrder();
        }

        if (this.right != null) {  //向右子树遍历
            this.right.postOrder();
        }

        System.out.println(this);   //输出父节点
    }

    /**
     * @param no 查找的编号
     * @return 找到返回Node ，否则返回null
     */
    //前序遍历查找
    public HeroNodes preOrderSearch(int no) {
        if (this.no == no) {   //比较此节点是不是
            return this;
        }

        HeroNodes resNode = null;

        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {  //左子树找到
            return resNode;
        }

        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    //中序遍历查找
    public HeroNodes infixOrderSearch(int no) {
        HeroNodes resNode = null;

        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {  //左子树找到
            return resNode;
        }

        if (this.no == no) {   //比较此节点是不是
            return this;
        }

        if (this.right != null) {   //右查找
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    //后序遍历查找
    public HeroNodes postOrderSearch(int no) {
        HeroNodes resNode = null;

        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {  //左子树找到
            return resNode;
        }

        if (this.right != null) {   //右查找
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {  //右子树找到
            return resNode;
        }

        if (this.no == no) {   //比较此节点是不是
            return this;
        }

        return resNode;
    }
}

//定义BinaryTree 二叉树
class ThreadedBinaryTree {
    private HeroNodes root;
    //实现线索化，需要创建指向当前结点的前驱节点的指针
    private HeroNodes pre = null;    //pre总是保留前一个节点

    public void setRoot(HeroNodes root) {
        this.root = root;
    }

    public void threadedNodes() {
        this.threadedNodes(root);
    }

    //遍历线索化二叉树方法
    public void threadedList() {
        //定义变量，存储当前遍历的节点，从root开始
        HeroNodes node = root;
        while (node != null) {
            //找leftTy为1的节点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            System.out.println(node);   //打印当前节点
            while (node.getLeftType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }

    /**
     * 对二叉树进行中序线索化的方法
     *
     * @param node 当前需要线索化的节点
     */
    public void threadedNodes(HeroNodes node) {
        if (node == null) {
            return;
        }
        //线索化左子树
        threadedNodes(node.getLeft());
        //线索化当前节点
        if (node.getLeft() == null) {
            //处理当前节点的前驱节点
            node.setLeft(pre);   //让当前节点的左指针指向前驱节点
            node.setLeftType(1); //表示指向前驱节点
        }
        //处理当前节点的后继节点
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
        //线索化右子树
        threadedNodes(node.getRight());
    }

    //删除结点
    public void delNode(int no) {
        if (root != null) {
            //判读root是否为删除的点
            if (root.getNo() == no) {
                root = null;
            } else { //递归删除
                root.delNode(no);
            }
        } else {
            System.out.println("空树，不能删除");
        }
    }

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //前序查找
    public HeroNodes preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    //中序查找
    public HeroNodes infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    //后序查找
    public HeroNodes postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }
}