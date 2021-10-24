package NonlinearStructure.Tree.huffmantree;


import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;

/**
 * @author tomable
 * @create 2021-10-19-15:17
 */
public class HuffmanCode {
    //根据赫夫曼数据 生成 赫夫曼编码 Map<Byte,String>
    static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
    //需要定义一个StringBuilder 存储某个叶子节点的路径
    static StringBuilder stringBuilder = new StringBuilder();


    public static void main(String[] args) {
//        // -----------------------------    数据压缩与解压（分步版）
//        String content = "i like like like java do you like a java";
//        byte[] contentBytes = content.getBytes();
//        System.out.println(contentBytes.length);
//
//        List<node> nodes = getNodes(contentBytes); //统计字母出现的次数
//        System.out.println(nodes);
//
//        System.out.println("赫夫曼树：");
//        node huffmanTreeRoot = creatHuffmanTree(nodes);
//        System.out.println("前序遍历");
//        huffmanTreeRoot.preOrder();
//
//        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
//        System.out.println("赫夫曼编码表:" + huffmanCodes);
//
//        byte[] huffmanCodeBytes = zip(contentBytes,huffmanCodes);
//        System.out.println("赫夫曼编码后的数组" + Arrays.toString(huffmanCodeBytes));

//        // -----------------------------    数据压缩与解压
//        String content = "i like like like java do you like a java";
//        byte[] contentBytes = content.getBytes();
//        byte[] huffmanCodeBytes = huffmanzip(contentBytes);
//        System.out.println("赫夫曼编码后的数组" + Arrays.toString(huffmanCodeBytes));
//
//        byte[] sourceBytes = decode(huffmanCodes, huffmanCodeBytes);
//        System.out.println("原来的字符串" + new String(sourceBytes));

        // -----------------------------    数据压缩与解压
//        String srcFile = "E:\\sql.sql";
//        String destFile = "E:\\sql.zip";
//
//        zipFile(srcFile, destFile);
//        System.out.println("压缩完成！！！");

        String zipFile = "E:\\sql.zip";
        String dstFile = "E:\\sql2.sql";

        unZipFile(zipFile,dstFile);
        System.out.println("解压完成！！！");
    }

    //前序遍历的方法
    private static void preOrder(node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("赫夫曼树为空，不可遍历！！！！");
        }
    }

    //-------------------    数据编码    ----------------------------

    /**
     * 生成List数组
     *
     * @param bytes 接收字节数组
     * @return 返回就是 List 形式
     */
    private static List<node> getNodes(byte[] bytes) {
        //创建一个ArrayList
        ArrayList<node> nodes = new ArrayList<node>();

        //遍历bytes，统计每一个byte出现的次数 -> map[key,value]
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {   //Map还没有这个字符数据
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }

        //把每一个键值对转换成node对象，并加入到nodes集合
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    //通过List创建对应的赫夫曼树
    private static node creatHuffmanTree(List<node> nodes) {
        while (nodes.size() > 1) {
            //排序，从小到大
            Collections.sort(nodes);

            //取出最小的节点，并生成二叉树
            node leftNode = nodes.get(0);
            node rightNode = nodes.get(1);
            node parent = new node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            nodes.remove(leftNode); //将已经处理的节点移除
            nodes.remove(rightNode);//将已经处理的节点移除
            nodes.add(parent);
        }
        //返回最后的节点
        return nodes.get(0);
    }

    /**
     * 将传入的 node 结点的所有叶子结点的赫夫曼编码得到，并放入到 huffmanCodes 集合
     *
     * @param node          传入结点
     * @param code          路径：左子节点是0，右子节点是1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //将 code 加入到 stringBuilder2
        stringBuilder2.append(code);
        if (node != null) {   //node为空不处理
            if (node.data == null) {   //非叶子结点
                //递归处理
                getCodes(node.left, "0", stringBuilder2);
                getCodes(node.right, "1", stringBuilder2);
            } else { //叶子结点
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    //重载getCodes
    private static Map<Byte, String> getCodes(node root) {
        if (root == null) {
            return null;
        }
        //处理root左子树
        getCodes(root.left, "0", stringBuilder);
        //处理root右子树
        getCodes(root.right, "1", stringBuilder);

        return huffmanCodes;
    }

    /**
     * 字符串对应的byte[] 数组，通过生成的赫夫曼编码表
     * 返回一个由赫夫曼编码组成数组
     *
     * @param bytes        原始的字符串对应的byte[]
     * @param huffmanCodes 生成的赫夫曼编码map
     * @return 赫夫曼编码处理后的byte[]
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //利用 huffmanCodes 将 bytes 转换成 赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
//        System.out.println(stringBuilder.toString());

        //统计返回byte[] huffmanCodes 长度

        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }

        //创建存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;    //记录是第几个byte
        for (int i = 0; i < stringBuilder.length(); i += 8) { //步长为8,8位对应一个byte
            String strByte;
            if (i + 8 > stringBuilder.length()) {   //不够8位
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }

            //将strByte 转换成一个 byte ，放入到huffmanCodeBytes
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }

        return huffmanCodeBytes;
    }

    /**
     * 将前面的方法封装起来，便于调用
     *
     * @param bytes 原始的字符串对应的字节数组
     * @return 经过赫夫曼编码处理后的字节数组（压缩后的数组）
     */
    private static byte[] huffmanzip(byte[] bytes) {
        //根据原始的字符串数组，统计字母出现的次数的集合
        List<node> nodes = getNodes(bytes);
        //根据nodes 创建赫夫曼树
        node huffmanTreeRoot = creatHuffmanTree(nodes);
        //根据赫夫曼树，生成赫夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        //根据赫夫曼编码，得到压缩后的赫夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }


    //-------------------    数据解码    ----------------------------

    /**
     * 将一个byte 转换为一个赫夫曼编码对应的二进制字符串
     *
     * @param flag 表示是否需要补高位。true需要补，false不补,最后一个字节不需要补高位
     * @param b    传入的byte
     * @return 是 b 对应的二进制字符串(按补码返回)
     */
    private static String byteToString(boolean flag, byte b) {
        int temp = b;   //将 b 转换成 int

        if (flag) {
            temp |= 256;  //按位与256
        }

        String str = Integer.toBinaryString(temp);

        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    /**
     * 对压缩数据的解码
     *
     * @param huffmanCodes 赫夫曼编码表
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 源字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        StringBuilder stringBuilder = new StringBuilder();

        //将byte数组 转换成赫夫曼编码表对应的二进制字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //判断是否为最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToString(!flag, b));
        }

        //根据赫夫曼编码表，把对应的二进制字符串 进行解码
        Map<String, Byte> map = new HashMap<String, Byte>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
//        System.out.println(map);

        //创建一个集合，存放byte
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;

            while (flag) {
                //取出一个数
                String key = stringBuilder.substring(i, i + count);
                b = map.get(key);
                if (b == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }

        //将list中的数据放入到byte[] 并返回
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }


//////////////////////////////////////////////////////////////////////////////////////

    //-------------------    文件压缩    ----------------------------

    /**
     * @param srcFile 传入希望压缩的文件的全路径
     * @param dstFile 压缩后的文件存放地址
     */
    public static void zipFile(String srcFile, String dstFile) {
        //创建文件的输出流
        OutputStream os = null;
        ObjectOutputStream oos = null;
        //创建文件的输入流
        FileInputStream is = null;
        try {
            //创建文件的输入流
            is = new FileInputStream(srcFile);
            //创建一个和源文件大小一样的byte[]   (复制)
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);

            //压缩源文件
            byte[] huffmanBytes = huffmanzip(b);

            //创建文件的输出流，存放压缩文件
            os = new FileOutputStream(dstFile);

            //创建一个和文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);

            //把赫夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);

            //把赫夫曼编码表写入压缩文件
            oos.writeObject(huffmanCodes);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                is.close();
                oos.close();
                os.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //-------------------    文件解压    ----------------------------

    /**
     * @param zipFile 传入希望解压的文件的全路径
     * @param dstFile 解压后的文件存放地址
     */
    public static void unZipFile(String zipFile, String dstFile) {
        //定义文件输入流
        InputStream is = null;
        //定义一个对象输入流
        ObjectInputStream ois = null;
        //定义文件的输出流
        OutputStream os = null;
        try {
            //创建文件输入流
            is = new FileInputStream(zipFile);
            //创建一个和is 关联的对象输入流
            ois = new ObjectInputStream(is);

            //读取 赫夫曼byte 数组
            byte[] huffmanBytes = (byte[]) ois.readObject();
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();

            //解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);

            //将bytes数组写入到目标文件
            os = new FileOutputStream(dstFile);

            //写入数据到dstFile文件
            os.write(bytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

//创建Node，带数据和权值
class node implements Comparable<node> {
    Byte data;  //存放数据本身
    int weight; //权值
    node left;
    node right;

    public node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(@NotNull node o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}






