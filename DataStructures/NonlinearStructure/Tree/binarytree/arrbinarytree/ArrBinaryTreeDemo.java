package NonlinearStructure.Tree.binarytree.arrbinarytree;

/**
 * @author tomable 顺序存储二叉树
 * @create 2021-09-29-16:23
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree=new ArrBinaryTree(arr);
        arrBinaryTree.preOrder();
    }
}

//编写ArrBinaryTree，实现顺序存储二叉树遍历
class ArrBinaryTree {
    private int[] arr;  //存储数据节点的数组

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }
    public void preOrder(){
        this.preOrder(0);
    }

    //顺序存储二叉树的前序遍历
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能遍历");
        }
        //输出当前元素
        System.out.println(arr[index]);

        if ((index * 2 + 1) < arr.length) { //向左递归
            preOrder(2 * index + 1);
        }
        if ((index * 2 + 2) < arr.length) { //向右递归
            preOrder(2 * index + 2);
        }
    }
}