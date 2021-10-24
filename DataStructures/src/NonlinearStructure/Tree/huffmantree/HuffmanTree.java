package NonlinearStructure.Tree.huffmantree;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author tomable
 * @create 2021-10-18-19:24
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node root = createHuffmanTree(arr);
        preOrder(root);
    }

    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("是空树，不能遍历！！！！！！");
        }
    }

    /**
     * 创建赫夫曼树的方法
     * @param arr   需要创建成赫夫曼树的数组
     * @return      创建好后的赫夫曼树的root节点
     */
    public static Node createHuffmanTree(int[] arr) {
        //1.遍历arr数组
        //2.将arr的每一个元素构成一个Node
        //3.将Node放入到ArrayList中
        List<Node> nodes = new ArrayList<Node>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }

        while (nodes.size() > 1) {
            //排序 从小到大
            Collections.sort(nodes);

            //取出根两个节点构建权值最小的二叉树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            //从ArrayList删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            //将parent加入到nodes
            nodes.add(parent);
        }
        return nodes.get(0);
    }
}

//创建结点类
//为了让Node 对象持续排序 Collections 集合排序
//让 Node 实现 Comparable 接口
class Node implements Comparable<Node> {
    int value;  //结点权值
    Node left;  //指向左子结点
    Node right; //指向右子结点

    public Node(int value) {
        this.value = value;
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
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
    public int compareTo(@NotNull Node o) {
        //表示从小到大排序
        return this.value - o.value;
    }
}
