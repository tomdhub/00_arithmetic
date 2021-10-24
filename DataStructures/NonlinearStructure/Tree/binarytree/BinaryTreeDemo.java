package NonlinearStructure.Tree.binarytree;


/**
 * @author tomable 二叉树
 * @create 2021-09-28-18:58
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        //创建一个二叉树
        BinaryTree binaryTree = new BinaryTree();

        //创建需要的节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        //手动创建该二叉树，后面学习递归的方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);

        //删除测试
        System.out.println("删除前，前序遍历");
        binaryTree.preOrder();  // 1,2,3,5,4

        binaryTree.delNode(3);
        System.out.println("删除后，前序遍历");
        binaryTree.preOrder();  // 1,2,3,,4

//        //遍历测试
//        System.out.println("前序遍历");
//        binaryTree.preOrder();  // 1,2,3,5,4
//        System.out.println("中序遍历");
//        binaryTree.infixOrder();// 2,1,5,3,4
//        System.out.println("后序遍历");
//        binaryTree.postOrder(); //2,5,4,3,1

//        //前序查找,前序遍历的次数:4
//        System.out.println("前序遍历方式~~~");
//        HeroNode resNode = binaryTree.preOrderSearch(5);
//        if (resNode != null) {
//            System.out.printf("找到了，信息为no=%d name=%s", resNode.getNo(), resNode.getName());
//        } else {
//            System.out.printf("没有找到no =%d的英雄", 5);
//        }

//        //中序查找，中序遍历3次
//        System.out.println("中序遍历方式~~~");
//        HeroNode resNode = binaryTree.infixOrderSearch(5);
//        if (resNode != null) {
//            System.out.printf("找到了，信息为no=%d name=%s", resNode.getNo(), resNode.getName());
//        } else {
//            System.out.printf("没有找到no =%d的英雄", 5);
//        }

//        //后序遍历查查找，次数2次
//        System.out.println("后序遍历方式~~~");
//        HeroNode resNode = binaryTree.postOrderSearch(5);
//        if (resNode != null) {
//            System.out.printf("找到了，信息为no=%d name=%s", resNode.getNo(), resNode.getName());
//        } else {
//            System.out.printf("没有找到no=%d 的英雄", 5);
//        }
    }
}

//创建HeroNode结点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;   //默认为空
    private HeroNode right;  //默认为空

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
        return "HerNode{" +
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
    public HeroNode preOrderSearch(int no) {
        if (this.no == no) {   //比较此节点是不是
            return this;
        }

        HeroNode resNode = null;

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
    public HeroNode infixOrderSearch(int no) {
        HeroNode resNode = null;

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
    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;

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
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
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

    //前序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //前序查找
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    //中序查找
    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    //后序查找
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }
}