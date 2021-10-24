package NonlinearStructure.Tree.binarytree.binarysorttree;


/**
 * @author tomable
 * @create 2021-10-22-12:48
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();

        //循环添加结点
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }

        //中序遍历
        System.out.println("中序遍历：");
        System.out.println("中序遍历2：");
        System.out.println("中序遍历3：");
        binarySortTree.infixOrder();

        binarySortTree.delNode(7);
        System.out.println("删除结点后：");
        binarySortTree.infixOrder();
    }
}

//创建二叉排序树
class BinarySortTree {
    private Node root;

    //查找要删除的结点
    public Node search(int value){
        if (root==null){
            return null;
        }else {
            return root.search(value);
        }
    }

    //查找父节点
    public Node searchParent(int value){
        if (root==null){
            return null;
        }else {
            return root.searchParent(value);
        }
    }

    //删除结点
    public void delNode(int value){
        if (root==null){
            return;
        }else {
            //1. 先找到要删除的结点 targetNode
            Node targetNode = search(value);
            if (targetNode ==null){     //没有找到要删除的结点
                return;
            }
            if (root.left==null&&root.right==null){ //当前节点为父节点，直接删除
                root=null;
                return;
            }

            //2、找要删除结点的父节点
            Node parent=searchParent(value);

            //3、分3种不同的情况删除
            //3.1、删除的结点是叶子结点
            if (targetNode.left==null&&targetNode.right==null){
                //判断 targetNode 是父节点的左子节点还是右子结点
                if (parent.left!=null&&parent.left.value==value){   //左子结点
                    parent.left=null;
                }else if (parent.right!=null&&parent.right.value==value){   //右子结点
                    parent.right=null;
                }
            }
            //3.2、删除的有两颗子树的结点
            else if (targetNode.left!=null&&targetNode.right!=null) {

            }
            //3.1、删除的只有一颗子树的结点
            else {
                //如果要删除的结点有左子结点
                if (targetNode.left!=null){
                    if (parent.left.value==value){  //targetNode 是 parent 的左子结点
                        parent.left=targetNode.left;
                    }else { //targetNode 是 parent 的右子结点
                        parent.right=targetNode.left;
                    }
                }
            }
        }
    }

    //添加结点
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    //中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空，不能遍历！！！！！！！");
        }
    }
}


//创建结点
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

    /**
     * 查找删除的结点
     *
     * @param value 希望删除的结点
     * @return 找到的结点
     */
    public Node search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {    //递归左子树查找
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else { //递归右子树查找
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     * 查找要删除结点的父节点
     *
     * @param value 要查找的结点的值
     * @return 返回要删除结点的父节点，没有就返回null
     */
    public Node searchParent(int value) {
        //当前结点就是要删除的结点的父节点
        if ((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)) {
            return this;
        } else {
            //查找的值小于当前结点的值。并且当前结点的左子结点不为空
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);   //向左子树递归查找
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);   //向右子树递归查找
            }else {
                return null;    //没有找到父节点
            }
        }
    }

    //添加结点
    public void add(Node node) {
        if (node == null) {
            return;
        }

        //判断传入结点的值应该放到哪里
        if (node.value < this.value) {    //判断左子结点
            //当前结点的左子结点为空
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
}